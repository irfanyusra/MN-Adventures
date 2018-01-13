/**
*The "Player" class.
*@Author Yusra Irfan 
*@Version 1.4 
*Date: 16th June 2016
 */
package mainPackage.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.graphics.Sprite;
import mainPackage.input.KeyInput;
//import mainPackage.states.PlayerState;
import mainPackage.tile.Tile;

public class Player extends Entity {

	/**
	 * Player Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 * @param player
	 */
	public Player(int x, int y, int width, int height,  Id id,
			Map map, Sprite[] player) {
		super(x, y, width, height, id, map);
	}

	public void paint(Graphics g) {
		if (getFrame()<=4){
			if (getFacing() == 3){//right
				if (isAttacking()){
					//attack image
					g.drawImage(Game.getPlayerImage()[11].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);
				}
				else
					g.drawImage(Game.getPlayerImage()[getFrame()+4].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);

			}
			if (getFacing() == 1){//left
				if (isAttacking())
					//attack image
					g.drawImage(Game.getPlayerImage()[10].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);
				else {
					g.drawImage(Game.getPlayerImage()[getFrame()].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);
				}
			}
		}

	
		//health bar 
		g.setColor(Color.gray);
		g.fillRect(getxEntity()-250, getyEntity()-340, 200, 32);

		g.setColor(Color.green);
		g.fillRect(getxEntity()-250, getyEntity()-340, Game.getpHealth()*2, 32);

		//coin 
		g.drawImage(Game.getCoin().getBufferedImage(),getxEntity()-190, getyEntity()-450, 75, 75, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier",Font.BOLD,20));
		g.drawString("x "+Game.getCoins(), getxEntity()-115, getyEntity()-395);
	}

	/**
	 * Update Method 
	 */
	public void update() {
		setxEntity(getxEntity()+getVelX());  //increases player's x position 
		setyEntity(getyEntity() + getVelY());//increases player's y position

		if (getVelX()!=0)//speed is not 0
			setAnimate(true);//animation set to true
		else 
			setAnimate(false);//animation set to false 

		if (!isCanAttack())//player cannot attack
			setNumOfSecAttack(getNumOfSecAttack()+1);//increases the number of seconds of attack

		for (int i=0;i<getMap().getTile().size();i++){//goes through tile linked list 
			Tile t = getMap().getTile().get(i);//declares and initializes Tile

			if (t.getId()!=Id.coin &&(t.getId()==Id.wall||t.getId()==Id.powerUpM||t.getId()==Id.powerUpC||t.getId()==Id.outOfBounds||t.getId()==Id.pipe||t.getId()==Id.pipeU)){//compares tile id's
				if (getBoundsTop().intersects(t.getBounds())){//Top of the player hits tile
					if (t.getId()==Id.pipe&&isSmallP()){//compares tile id to pipe and if Player is small
						setyEntity(t.getyTile()-getHeight());
					}
					else {
						setVelY(0);//y speed is set to 0

						if (isJumping()){//player was jumping 
							setJumping(false);//jumping set to false
							setGravity(0.0) ; //gravity set to 0 
							setFalling(true);// falling set to true
						}
						if (t.getId()==Id.powerUpM||t.getId()==Id.powerUpC)//compares tile id's
							t.setActivated(true);//activates the power ups
					}
				}
				if (getBoundsBottom().intersects(t.getBounds())){ //player bottom hits tile
					if (t.getId()==Id.outOfBounds ){//compares tile id
						die();//removes player
						Game.setGameOver(true);//Game over
						Game.setLevel(-1);
					}

					if (t.getId()==Id.pipe&&isSmallP()&&KeyInput.isPipe()){//compares tile id to pipe and if Player is small
						setyEntity(t.getyTile()+t.getHeight());
					}

					else {
						setVelY(0); //y speed set to 0
						if (isFalling())//if player was falling
							setFalling(false);//player falling set to false
						setyEntity(t.getyTile()-getHeight());//sets y position of the player on top of the tile
					}
				}

				else if (!isFalling()&&!isJumping())//if player is not falling and jumping
				{
					setGravity(0.0); // gravity set to 0
					setJumping(true);//jumping set to true
				}
				if (getBoundsLeft().intersects(t.getBounds())){//player tile wall from left
					setVelX(0);//x speed set to 0
					setxEntity (t.getxTile()+t.getWidth());///sets x position of the player to the right of the tile 
				}

				if (getBoundsRight().intersects(t.getBounds())){//player hits tile from right
					setVelX(0);//x speed set to 0
					setxEntity (t.getxTile()-getWidth());//sets x position of the player- left of the tile 
				}
			}

			if (getBounds().intersects(t.getBounds())&&t.getId()==Id.coin){//compares tile id to coin
				Game.setCoins(Game.getCoins() + 1);//number of coins increased 
				t.die();//coin removed
			}

			if (getBounds().intersects(t.getBounds())&&t.getId()==Id.levelEnd){//compares tile id to levelEnd
				{
					t.die();//tile removed
					if (Game.getLevel()==4)//check level number 
						Game.setLevel(0);//sets level to 0
					else {
						Game.setLevel(Game.getLevel() + 1);//increases the level
						getMap().switchLevel();//Switches the level- method in map class
					}
					Game.setGScreen(true);

				}
			}
		}


		for (int i=0;i<getMap().getEntity().size();i++){//goes through the entity linked list
			Entity e = getMap().getEntity().get(i);//declares and initializes Entity

			if (e.getId()==Id.mashroom){//compares entity id to mushroom
				if (getBounds().intersects(e.getBounds())){//player hits mushroom 
					setWidth((int)(getWidth() * 1.5));//player width changed
					setHeight((int)(getHeight() * 1.5));//player height changed 
					setSmallP(false);
					setxEntity(e.getxEntity());
					e.die();//Mushroom removed
				}
			}

			if(e.getId()==Id.changePlayer) {//compares entity id to changePlayer
				if (getBounds().intersects(e.getBounds())){//player hits another player 
					e.die();//removes change player
					Game.setPlayerImage(e.getPic());//changes the player picture 
					Game.setpHealth(100);//sets the health to 100
				}
			}

			if (getBoundsBottom().intersects(e.getBoundsTop())){//player hits top of enemy 
				if (e.getId()==Id.enemy)
					e.die();//kills enemy 
			}

			if(e.getId()==Id.bossEnemy||e.getId()==Id.enemy||e.getId()==Id.enemyAttack) {//compares entity id to enemy and bossEnemy
				if (getBounds().intersects(e.getBounds())&&!(getBoundsBottom().intersects(e.getBoundsTop()))){//player hits entity 
					if (getBounds().intersects(e.getBoundsRight()))//player hits entity  from right
						setxEntity(getxEntity() + getWidth());
					if (getBounds().intersects(e.getBoundsLeft()))//player hits entity  from left 
						setxEntity(getxEntity() - getWidth());
					if (getBoundsBottom().intersects(e.getBoundsTop()))//player hits top of enemy 
						setxEntity(getxEntity() - getWidth());
					setyEntity(getyEntity() - e.getHeight());

					if (!isSmallP()){//player is not small
						//makes player small
						setSmallP(true);
						setWidth((int)(getWidth() /1.5));
						setHeight((int)(getHeight() / 1.5));
					}

					else if (isSmallP()){//player small
						//decreases health
						Game.setpHealth(Game.getpHealth() - 25);
						if (Game.getpHealth()==0){
							die();//removes player
							Game.setLevel(-1);
							Game.setGameOver(true);//game over
						}
					}
					if (e.getId()==Id.enemyAttack)
						e.die();//removes the attack
				}
			}
		}
		if (isJumping()){//player jumping 
			setGravity(getGravity() - 0.15);//gravity is set
			setVelY((int)-getGravity());//y speed set to -gravity

			if (getGravity()<=0.0){
				setJumping(false);
				setFalling(true);
			}
		}

		if (isFalling()){//player falling 
			setGravity(getGravity() + 0.15);//sets gravity
			setVelY((int)getGravity());//y speed set to gravity
		}

		if (isAnimate()){//if player is animated 
			setFrameDelay(getFrameDelay() + 1);//frame delay is increased 
			if (getFrameDelay()>=3){
				setFrame(getFrame() + 1);//frame is increased 
				if (getFrame()>=4){//change to per frame if the sides change 
					setFrame(0);//frame is set to 0
				}
				setFrameDelay(0);//frame delay set to 0
			}
		}
	}
}
