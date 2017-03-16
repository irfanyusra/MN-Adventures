/**
*The "PowerUpBlock" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.tile;

import java.awt.Graphics;

import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.entity.powerup.Mushrooms;
import mainPackage.graphics.Sprite;

public class PowerUpBlock extends Tile  {
	
	//declaring variables
	private Sprite powerUp;
	private boolean used=false;
	private int spriteY= getyTile();//y position of the tile 

	/**
	 * PowerUpBlock Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param solid
	 * @param id
	 * @param map
	 * @param powerUp
	 */
	public PowerUpBlock(int x, int y, int width, int height,
			Id id, Map map, Sprite powerUp) {
		super(x, y, width, height, id, map);
		this.powerUp=powerUp;
	}

	/**
	 * Paint Method 
	 */
	public void paint(Graphics g) {
		if (!used)//power up not used 
			g.drawImage(powerUp.getBufferedImage(), getxTile(), spriteY, getWidth(), getHeight(),null);
		if (!isActivated())//power up not activated 
			g.drawImage(Game.getPowerUp().getBufferedImage(), getxTile(), getyTile(), getWidth(), getHeight(),null);
		else 
			g.drawImage(Game.getUsedPowerUp().getBufferedImage(), getxTile(), getyTile(), getWidth(), getHeight(),null);

	}

	/**
	 * Update Method
	 */
	public void update() {
		if (isActivated() &&!used){//power up activated but not used
			spriteY--;
			if (spriteY<=getyTile()-getHeight()){
				if (getId()==Id.powerUpM)//id is power up mushroom 
				getMap().addEntity(new Mushrooms(getxTile(),spriteY,getWidth(),getHeight(),Id.mashroom,getMap()));
				if (getId()==Id.powerUpC)//id is power up coin
					getMap().addTile(new Coin(getxTile(),spriteY,getWidth(),getHeight(), Id.coin,getMap()));
					
				used=true;//used is true 
			}
		}
	}
}
