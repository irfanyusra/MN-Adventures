/**
*The "Coin" class.
*@Author Yusra Irfan 
*@Version 1.4 
*Date: 16th June 2016
 */

package mainPackage.tile;

import java.awt.Graphics;

import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;

public class Coin extends Tile {

	/**
	 * Coin Constructor 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 */
	public Coin(int x, int y, int width, int height, Id id,Map map) {
		super(x, y, width, height, id, map);
	}
	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {
		g.drawImage(Game.getCoin().getBufferedImage(), getxTile(), getyTile(), getWidth(), getHeight(), null);

	}

	/**
	 * Update 
	 */
	public void update() {

	}
}
