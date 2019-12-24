/**
*The "Enemy" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity.enemy;

import java.awt.Graphics;
import java.util.Random;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.entity.Entity;
import mainPackage.graphics.Sprite;
import mainPackage.tile.Tile;

public class Enemy extends Entity {
	//declaring variables
	private Random random =new Random ();
	private Sprite enemy [];

	/**
	 * Enemy Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 * @param enemy
	 */
	public Enemy(int x, int y, int width, int height, Id id,
			Map map, Sprite[] enemy) {
		super(x, y, width, height,id, map);
		//Initializing variables	
		setFacing(random.nextInt(2)) ;
		this.enemy=enemy;

		switch (getFacing()){
		case 0://right 
			setVelX(-3);//setting x speed
			break;

		case 1:
			setVelX(3);//setting x speed
			break;
		}
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		if (getFrame()<4){
			if (getFacing()==0)
				g.drawImage(enemy[getFrame()].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);//change to sprite 
			if (getFacing()==1)
				g.drawImage(enemy[getFrame()+4].getBufferedImage(), getxEntity(), getyEntity(), getWidth(), getHeight(), null);//change to sprite 

		}
	}

	public void update() {
		setxEntity(getxEntity() + getVelX()); //increases player's x position 
		setyEntity(getyEntity() + getVelY()); //increases player's y position 

		if (getVelX()!=0)setAnimate(true);
		else setAnimate(false);

		for (int i=0;i<getMap().getTile().size();i++){   //goes through tile linked list 
			Tile t = getMap().getTile().get(i); //declares and initializes Tile
			if (!(t.getId()==Id.outOfBounds)){
				if (getBoundsBottom().intersects(t.getBounds())){ //Bottom of the enemy hits tile
					setVelY(0);//y speed is set to 0 		
					if (isFalling()) //if player was falling      
						setFalling(false);//player falling set to false 
				}
				else if (!isFalling()){//if player is not falling and jumping 						
					setGravity(0);   // gravity set to 0  
					setFalling(true);//jumping set to true
				}
				if (getBoundsLeft().intersects(t.getBounds())) //enemy tile wall from left
				{setVelX(3);  //x speed set to 3
				setFacing(1);
				}
				if (getBoundsRight().intersects(t.getBounds())){//enemy hits tile from right
					setVelX(-3); //x speed set to -3    
					setFacing(0);
				}
			}
		}


		for (int i=0;i<getMap().getEntity().size();i++){ //goes through the entity linked list
			Entity en = getMap().getEntity().get(i);     //declares and initializes Entity    

			if (en.getId()==Id.attack){//compares entity id to attack
				if (getBounds().intersects(en.getBounds())){//attack hits enemy 
					die();   //enemy removed
					en.die();//attack removed
				}
			}
		}

		if (isFalling()){//enemy falling   
			setGravity(getGravity() + 0.1); //sets gravity 
			setVelY((int)getGravity());    //y speed set to gravity
		}

		if (isAnimate()){//if enemy is animated 
			setFrameDelay(getFrameDelay() + 1);//frame delay is increased 
			if (getFrameDelay()>=3){
				setFrame(getFrame() + 1);//frame is increased
				if (getFrame()>=4){//change to per frame if the sides change 
					setFrame(0);  //frame is set to 0
				}
				setFrameDelay(0);//frame delay set to 0
			}
		}
	}
}
