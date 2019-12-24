/**
*The "Mushrooms" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity.powerup;
import java.awt.Graphics;
import java.util.Random;

import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.entity.Entity; 
import mainPackage.tile.Tile;

public class Mushrooms extends Entity {

	//declaring variables
	private Random random =new Random ();
	private Map map;
	/**
	 * Mushrooms Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 */
	public Mushrooms(int x, int y, int width, int height, Id id,
			Map map) {
		super(x, y, width, height,id, map);

		//initializing variables
		this.map=map;
		setFacing(random.nextInt(2));

		switch (getFacing()){
		case 0://left 
			setVelX(-2);
			break;

		case 1://right
			setVelX(2);
			break;
		}
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(Game.getMushroom().getBufferedImage(),getxEntity(),getyEntity(),getWidth(),getHeight(),null);
	}

	/**
	 * Update Method
	 */
	public void update() {
		setxEntity(getxEntity() + getVelX()); //increases mushroom's x position 
		setyEntity(getyEntity() + getVelY()); //increases mushroom's y position 

		for (int i=0;i<map.getTile().size();i++){//goes through tile linked list 
			Tile t = map.getTile().get(i);//declares and initializes Tile
			
			if (t.getId()==Id.wall){//compares tile id with wall	
				if (getBoundsBottom().intersects(t.getBounds())){ //Top of the enemy hits tile	
					setVelY(0);//y speed is set to 0 			
					if (isFalling()) //if mushroom was falling      	
						setFalling(false);//mushroom falling set to false 	
				}
				
				else if (!isFalling()){//if mushroom is not falling and jumping 							
					setGravity(0); // gravity set to 0  	
					setFalling(true);//jumping set to true	
				}	
				
				if (getBoundsLeft().intersects(t.getBounds())){ //enemy tile wall from left	
					setVelX(3);  //x speed set to 3
				}	

				if (getBoundsRight().intersects(t.getBounds())){//enemy hits tile from right
					setVelX(-3); //x speed set to -3    	
				}	
			}
		}
		
		if (isFalling()){//mushroom falling   
			setGravity(getGravity() + 0.1); //sets gravity 
			setVelY((int)getGravity());//y speed set to gravity
		}
	}
}

