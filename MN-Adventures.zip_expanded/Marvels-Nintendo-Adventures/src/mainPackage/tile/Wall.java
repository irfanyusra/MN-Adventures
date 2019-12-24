/**
*The "Wall" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.tile;

import java.awt.Graphics;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.graphics.Sprite;

public class Wall extends Tile {
//declaring variables
	Sprite image;//Sprite

	/**
	 * Wall Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param solid
	 * @param id
	 * @param image
	 * @param map
	 */
	public Wall(int x, int y, int width, int height,  Id id,
			Sprite image, Map map) {
		super(x, y, width, height, id, map);
		//initializing variables 
		this.image=image;
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		if (getId()==Id.wall)//id equals wall
			g.drawImage(image.getBufferedImage(), getxTile(), getyTile(), getWidth(), getHeight(), null);
	}

	/**
	 * Update Method
	 */
	public void update() {
	}

}
