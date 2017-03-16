/**
*The "ChangePlayer" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity;

import java.awt.Graphics;

import mainPackage.Map;
import mainPackage.Id;
import mainPackage.graphics.Sprite;

public class ChangePlayer extends Entity {

	/**
	 * ChangePlayer Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 * @param player
	 */
	public ChangePlayer(int x, int y, int width, int height, Id id,
			Map map, Sprite[] player) {
		super(x, y, width, height, id, map);
		setPic(player);//sets the image
	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(getPic()[4].getBufferedImage(), getxEntity(), getyEntity(), getWidth(),getHeight(), null);
	}

	/**
	 * Update Method
	 */
	public void update() {

	}

}
