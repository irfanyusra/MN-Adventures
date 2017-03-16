/**
*The "LevelComplete" class.
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

public class LevelComplete extends Tile {

	/**
	 * LevelComplete Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param handler
	 */
	public LevelComplete(int x, int y, int width, int height, Id id,
			Map handler) {
		super(x, y, width, height, id, handler);

	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(Game.getPortal().getBufferedImage(), getxTile(), getyTile(), getWidth(), getHeight(), null);//change to sprite 
		
	}

	/**
	 * Update Method
	 */
	public void update() {
	
	}

}
