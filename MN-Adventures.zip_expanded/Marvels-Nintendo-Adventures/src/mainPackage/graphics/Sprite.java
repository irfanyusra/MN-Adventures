/**
*The "Sprite" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.graphics;
import java.awt.image.BufferedImage;

import mainPackage.Game;

public class Sprite {
	//declaring variables
	private BufferedImage image;

	/**
	 * Constructor 
	 * @param sheet
	 * @param x
	 * @param y
	 */
	public Sprite (SpriteSheet sheet, int x, int y){
		if (sheet==Game.getSheetE())
			image = sheet.getSpriteE(x, y);//sets image 
		else 
			image = sheet.getSprite(x, y);//sets image 

	}

	/**
	 * Gets BufferedImage
	 * @return returns image 
	 */
	public BufferedImage getBufferedImage(){
		return image;
	}
	
}
