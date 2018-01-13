/**
*The "Attack" class.
*@Author Yusra Irfan 
*@Version 1.4 
*Date: 16th June 2016
 */

package mainPackage.entity;

import java.awt.Graphics;
import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.tile.Tile;

public class Attack extends Entity {

	private int increaseInPos=0;//increasing the position of attack
	/**
	 * Attack Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param velX
	 * @param id
	 * @param player
	 * @param map
	 */
	public Attack(int x, int y, int width, int height,
			int velX, Id id,Entity player, Map map) {
		super(x, y, width, height, id, map);
		setVelX(velX);//setting the speed for attack
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(Game.getPlayerImage()[12].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);
	}

	/**
	 * Update Method
	 */
	public void update() {

		setxEntity(getxEntity()+getVelX());//increases player's x position 
		if (increaseInPos>=100)
			die();//removes attack
		else 
			increaseInPos++;//increases increase in position of the attack

		for (int j=0;j<getMap().getTile().size();j++){//goes through the tile linked list
			Tile t = getMap().getTile().get(j);//declares and initializes Tile
			if (t.getId()==Id.wall ){//compares tile id to wall
				if (getBounds().intersects(t.getBounds()))//attack hits tile  
					die();//attack removes 
			}
		}
	}
}
