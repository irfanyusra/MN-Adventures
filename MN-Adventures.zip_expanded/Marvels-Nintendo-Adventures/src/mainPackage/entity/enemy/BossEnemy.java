/**
*The "Bossenemy" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.entity.Entity;
import mainPackage.graphics.Sprite;
import mainPackage.tile.Tile;

public class BossEnemy extends Entity {
	//declaring variables
	private Sprite enemyS [];//Enemy sprite 
	private Entity player;//player 

	/**
	 * Enemy Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 * @param enemyS
	 */
	public BossEnemy(int x, int y, int width, int height, Id id,
			Map map, Sprite[] enemyS) {
		super(x, y, width, height,id, map);
		//Initializing variables	
		this.enemyS=enemyS;
		setEnemyHealth(100);

		for (int i=0;i<Game.getMap().getEntity().size();i++)//goes through the entity linked list
		{
			Entity en = Game.getMap().getEntity().get(i);//declares and initializes Entity
			if (en.getId()==Id.player){
				player=en;
			}
		}

		setVelX(-1);
		setFacing(1);//right 
	}


	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		if (getFrame()<=4){
			if (getFacing() == 3){//left
				g.drawImage(enemyS[getFrame()].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);//change to sprite 
				if (isAttacking())
					//attack image
					g.drawImage(enemyS[8].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);//change to sprite 

			}
			else if (getFacing() == 1){//right
				g.drawImage(enemyS[getFrame()+4].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);
				if (isAttacking())
					//attack image
					g.drawImage(enemyS[9].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);//change to sprite 
			}
		}

		//Enemy Health bar
		g.setColor(Color.gray);
		g.fillRect(getxEntity(), getyEntity()-100, 200, 32);

		g.setColor(Color.green);
		g.fillRect(getxEntity(), getyEntity()-100, getEnemyHealth()*2, 32);
		setNumOfSecAttack(getNumOfSecAttack()+1);
	}

/**
 * Update Method
 */
	public void update() {
		setyEntity(getyEntity() + getVelY());//sets y position
		if (player.getxEntity()<getxEntity()) {
			setAnimate(true);
			setFacing(3);//left 
			setxEntity(getxEntity()+getVelX());
			setAttacking(false);
		}

		else if (player.getxEntity()>getxEntity()){
			setxEntity(getxEntity()-getVelX());
			setFacing(1);//right
			setAnimate(true);
			setAttacking(false);
		}
		else 
			setAnimate(false);


		for (int i=0;i<getMap().getTile().size();i++){  //goes through tile linked list
			Tile t = getMap().getTile().get(i);//declares and initializes Tile 
	
				if (t.getId()==Id.wall){	
					if (getBoundsBottom().intersects(t.getBounds())){ //bottom of the enemy hits tile      
						setVelY(0);	//y speed is set to 0 	
						if (isFalling()) //if player was falling 
							setFalling(false); //player falling set to false
					}
					else if (!isFalling()){//if player is not falling and jumping						
						setGravity(0);  // gravity set to 0    
						setFalling(true); //jumping set to true  
					}
					if (getBoundsLeft().intersects(t.getBounds())){  //enemy tile wall from left
						setVelX(getVelX()*-1);
						setFacing(1);//right
					}

					if (getBoundsRight().intersects(t.getBounds())){ //enemy hits tile from right 
						setVelX(getVelX() *-1);
						setFacing(3);//left 
					}
				}
		}

		for (int i=0;i<getMap().getEntity().size();i++){ //goes through the entity linked list
			Entity en = getMap().getEntity().get(i);     //declares and initializes Entity    

			if (en.getId()==Id.attack){  //compares entity id to attack  
				if (getBounds().intersects(en.getBounds())){ //attack hits enemy 
					if (getEnemyHealth()<=0){
						die();           //enemy removed 
						Game.setLevel(0);//sets level to 0
						Game.setGScreen(true);
					}
					else 
						setEnemyHealth(getEnemyHealth()-25);//health decreased
					en.die();    //attack removed 
				}
			}
		}

		if (isFalling()){//enemy falling
			setGravity(getGravity() + 0.1); //sets gravity         
			setVelY((int)getGravity());    //y speed set to gravity
		}

		if (isAnimate()){ //if enemy is animated
			setFrameDelay(getFrameDelay() + 1);//frame delay is increased 
			if (getFrameDelay()>=7){
				setFrame(getFrame() + 1);  //frame is increased  
				if (getFrame()>=4){//change to per frame if the sides change 
					setFrame(0);  //frame is set to 0   
				}
				setFrameDelay(0);//frame delay set to 0  
			}
		}

		if (isCanAttack()){//enemy can attack
			setAttacking(true);//attacking 
			setCanAttack(false);

			if (getFacing()==1)	//right
				Game.getMap().addEntity(new EnemyAttack((int)getxEntity(),(int)getyEntity()+35,32,32,5, Id.enemyAttack,this,Game.getMap()));	

			else if (getFacing()==3)//left
				Game.getMap().addEntity(new EnemyAttack(getxEntity(),getyEntity()+35,32,32,-5, Id.enemyAttack,this, Game.getMap()));

		}
		if (!(isCanAttack())&&getNumOfSecAttack()>=150){
			setCanAttack(true);//can attack
			setNumOfSecAttack(0);//set to 0
		}
	}
}

