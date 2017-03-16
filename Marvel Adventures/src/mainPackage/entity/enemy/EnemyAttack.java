/**
*The "EnemyAttack" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity.enemy;

import java.awt.Graphics;
import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.entity.Entity;
import mainPackage.tile.Tile;


public class EnemyAttack extends Entity {

	private int increaseInPos=0;//increasing the position of attack
	/**
	 * Attack Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param velX
	 * @param id
	 * @param enemy
	 * @param map
	 */
	public EnemyAttack(int x, int y, int width, int height,
			int velX, Id id,Entity enemy, Map map) {
		super(x, y, width, height, id, map);
		setVelX(velX);//setting the speed for attack
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(Game.getEnemyBoss()[10].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);

	}

	/**
	 * Update Method
	 */
	public void update() {

		setxEntity(getxEntity()+getVelX());//increases player's x position 
		if (increaseInPos>=300)
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
