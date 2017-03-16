/**
*The "SprtieSheet" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	//declaring variables
	private BufferedImage sheet; 

	/**
	 * Constructor
	 * @param path
	 */
	public SpriteSheet (String path){
		try {
			sheet= ImageIO.read(getClass().getResource(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the Sprite
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getSprite(int x, int y){
		return sheet.getSubimage(x*32-32, y*32-32, 32, 32);
	}
	
	/**
	 * Gets the Sprite
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getSpriteE(int x, int y){
		return sheet.getSubimage(x*100-100, y*100-100, 100, 100);
	}
}
