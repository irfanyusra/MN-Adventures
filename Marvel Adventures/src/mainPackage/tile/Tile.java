/**
*The "Tile" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import mainPackage.Map;
import mainPackage.Id;

public abstract class Tile {

	//declaring variables
	private int xTile;
	private int yTile;
	private int width, height; 
	private Map map;
	private boolean activated =false;
	private Id id;

	/**
	 * Tile Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param solid
	 * @param id
	 * @param map
	 */
	public Tile(int x, int y, int width, int height,Id id ,Map map) {
		// TODO Auto-generated constructor stub
		xTile=x;
		yTile=y;
		this.width=width;
		this.height=height;
		this.map=map;
		this.id=id;
	}


	/**
	 * Removes Tile
	 */
	public void die(){
		map.removeTile(this);
	}

	public abstract void paint (Graphics g);
	public abstract void update();

	/**
	 * Get Bounds
	 * @return rectangle
	 */
	public Rectangle getBounds (){
		return new Rectangle(getxTile(),getyTile(),width,height);
	}

	/**
	 * Width of the tile
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Height of the tile
	 * @return height
	 */

	public int getHeight() {
		return height;
	}

	/**
	 * Gets Map 
	 * @return returns Map map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Gets Activated 
	 * @return returns boolean activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Sets Activated
	 * @param activated
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * Gets Id 
	 * @return returns Id id 
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Gets x position of the Tile 
	 * @return returns int xTile 
	 */
	public int getxTile() {
		return xTile;
	}

	/**
	 * Sets x position of Tile 
	 * @param xTile
	 */
	public void setxTile(int xTile) {
		this.xTile = xTile;
	}

	/**
	 * Gets y position of the Tile 
	 * @return returns int yTile 
	 */

	public int getyTile() {
		return yTile;
	}

	/**
	 * Sets y position of Tile 
	 * @param yTile
	 */
	public void setyTile(int yTile) {
		this.yTile = yTile;
	}

}
