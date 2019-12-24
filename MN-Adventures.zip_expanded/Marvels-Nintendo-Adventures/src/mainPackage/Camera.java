package mainPackage;

import mainPackage.entity.Entity;

public class Camera {

	public int x;//x position of camera
	public int y;//y position of camera
	
	public void update (Entity player){
		
		setX(-player.getxEntity()+Game.getFrameWidth()/4);//setting x position
		setY((int)(-player.getyEntity()+Game.getFrameHeight()/1.5));//setting y position
	}

	/**
	 * Set x position
	 * @param x
	 */
	public void setX(int x) {
		this.x=x;
	}

	/**
	 * Get x position
	 * @return returns x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get y position
	 * @return returns y position
	 */
	public int getY() {
		return y;
	}
		
	/**
	 * Set y position
	 * @param y
	 */
	public void setY(int y) {
		this.y=y;
	}
}
