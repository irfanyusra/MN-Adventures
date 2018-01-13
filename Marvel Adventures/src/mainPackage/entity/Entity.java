/**
*The "Entity" class.
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.entity;
import java.awt.*;

import mainPackage.Game;
import mainPackage.Map;
import mainPackage.Id;
import mainPackage.graphics.Sprite;

public abstract class Entity {
	//declaring variables
	private int xEntity;//x position
	private int yEntity;//y position
	private int  width;//width
	private int height; //height
	private int facing = 1; //0- up, 1-left ,2- down 3- right,
	private int velX;//x speed
	private int velY;//y speed
	private int numOfattack=0;//Number of Attacks
	private int numOfSecAttack=0;//Seconds the Attacks are in air
	private int frame = 0;//player frame
	private int frameDelay =0;//Delay frame
	private int enemyHealth;//Enemy Health 
	
	private Map map;//map 
	private Id id ;//id
	private Sprite []pic;//Picture of Entity
	private double gravity= 0.0;//gravity
	
	private boolean canAttack=true; //Can Entity Attack
	private boolean attacking=false;//Is Entity Attacking 
	private boolean jumping = false; //jumping 
	private boolean falling = true;//falling
	private boolean smallP=true;// Entity Small
	private boolean animate = false;//animate player
	
	
	/**
	 * Entity Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param map
	 */
	public Entity(int x, int y, int width, int height,Id id, Map map) {
		//initializing variables 
		xEntity=x;
		yEntity=y;
		this.width=width;
		this.height=height;
		this.map=map;
		this.id=id;
		
	}

	/**
	 * Paint Method 
	 * @param g
	 */
	public abstract void paint (Graphics g);
	
	/**
	 * Update Method 
	 */
	public abstract void update();

	public void die(){
		//removes Entity
		if (this.id==Id.player){ 
			Game.setGScreen(true);//GScreen is set to true
			Game.setGameOver(true);//GameOver is set to true
		}
		map.removeEntity(this);//removeEntity Method in map class
	}

	/**
	 * Gets Bounds 
	 * @return returns Rectangle 
	 */
	public Rectangle getBounds (){
		return new Rectangle(getxEntity(),getyEntity(),width,height);
	}

	/**
	 * Gets Bounds Top
	 * @return returns Rectangle 
	 */
	public Rectangle getBoundsTop (){
		return new Rectangle(getxEntity()+10,getyEntity(),width-20,5);
	}

	/**
	 * Gets Bounds Bottom
	 * @return returns Rectangle 
	 */
	public Rectangle getBoundsBottom (){
		return new Rectangle(getxEntity()+10,getyEntity()+height-5,width-20,5);
	}

	/**
	 * Gets Bounds Left
	 * @return returns Rectangle 
	 */
	public Rectangle getBoundsLeft (){
		return new Rectangle(getxEntity(),getyEntity()+10,5,height-20);
	}

	/**
	 * Gets Bounds Right 
	 * @return returns Rectangle 
	 */
	public Rectangle getBoundsRight (){
		return new Rectangle(getxEntity()+width-5,getyEntity()+10,5,height-20);
	}

	/**
	 * Gets Entity Width 
	 * @return returns Integer width 
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets Entity Width 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets Entity Height 
	 * @return returns Integer height 
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets Entity Height
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets Jumping
	 * @return returns boolean jumping
	 */
	public boolean isJumping() {
		return jumping;
	}

	/**
	 * Sets Jumping 
	 * @param jumping
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * Gets Falling
	 * @return returns boolean
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * Sets Falling 
	 * @param falling
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	/**
	 * Gets Facing 
	 * @return returns Integer facing 
	 */
	public int getFacing() {
		return facing;
	}

	/**
	 * Sets Facing 
	 * @param facing
	 */
	public void setFacing(int facing) {
		this.facing = facing;
	}

	/**
	 * Gets x Speed
	 * @return returns Integer velX 
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * Sets x Speed
	 * @param velX
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * Gets y Speed
	 * @return returns Integer velY
	 */
	public int getVelY() {
		return velY;
	}

	/**
	 * Sets y Speed
	 * @param velY
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}

	/**
	 * Gets Map
	 * @return returns Map map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Gets Id
	 * @return
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Sets Id
	 * @param id
	 */
	public void setId(Id id) {
		this.id = id;
	}

	/**
	 * Gets Gravity
	 * @return returns double gravity 
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * Sets Gravity
	 * @param gravity
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	/**
	 * Gets x position 
	 * @return returns Integer 
	 */
	public int getxEntity() {
		return xEntity;
	}

	/**
	 * Sets x position 
	 * @param xEntity
	 */
	public void setxEntity(int xEntity) {
		this.xEntity = xEntity;
	}

	/**
	 * Gets y position 
	 * @return returns Integer 
	 */
	public int getyEntity() {
		return yEntity;
	}

	/**
	 * Sets y position 
	 * @param yEntity
	 */
	public void setyEntity(int yEntity) {
		this.yEntity = yEntity;
	}

	/**
	 * Sets Number Of Attacks
	 * @param numOfattack
	 */
	public void setNumOfattack(int numOfattack) {
		this.numOfattack = numOfattack;
	}

	/**
	 * Gets Number Of Attacks
	 * @return returns Integer 
	 */
	public int getNumOfattack() {
		return numOfattack;
	}

	/**
	 * Gets Number of Seconds of the Attack
	 * @return returns Integer numOfSecAttack
	 */
	public int getNumOfSecAttack() {
		return numOfSecAttack;
	}

	/**
	 * Sets Number of Seconds of the Attack
	 * @param numOfSecAttack
	 */
	public void setNumOfSecAttack(int numOfSecAttack) {
		this.numOfSecAttack = numOfSecAttack;
	}

	/**
	 * Gets Small Player
	 * @return returns boolean smallP
	 */
	public boolean isSmallP() {
		return smallP;
	}

	/**
	 * Sets Small Player
	 * @param smallP
	 */
	public void setSmallP(boolean smallP) {
		this.smallP = smallP;
	}

	/**
	 * Gets Attacking
	 * @return returns boolean isAttacking 
	 */
	public boolean isAttacking() {
		return attacking;
	}

	/**
	 * Sets Attacking
	 * @param attacking
	 */
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	/**
	 * Gets Can Attack
	 * @return returns boolean canAttack
	 */
	public boolean isCanAttack() {
		return canAttack;
	}

	/**
	 * Set Can Attack
	 * @param canAttack
	 */
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	/**
	 * Gets Player Image 
	 * @return returns Sprite pic
	 */
	public Sprite [] getPic() {
		return pic;
	}

	/**
	 * Sets Player Image
	 * @param pic
	 */
	public void setPic(Sprite [] pic) {
		this.pic = pic;
	}

	/**
	 * Gets Frame number
	 * @return returns integer frame
	 */
	public int getFrame() {
		return frame;
	}
	
/**
 * Sets Frame Number
 * @param frame
 */
	public void setFrame(int frame) {
		this.frame = frame;
	}

	/**
	 * Gets Frame Delay
	 * @return returns integer frameDelay
	 */
	public int getFrameDelay() {
		return frameDelay;
	}

	/**
	 * Sets Frame Delay
	 * @param frameDelay
	 */
	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}

	/**
	 * Gets Animate
	 * @return returns boolean animate 
	 */
	public boolean isAnimate() {
		return animate;
	}

	/**
	 * Sets Animate
	 * @param animate
	 */
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	/**
	 * Gets Enemy Health
	 * @return enemyHealth
	 */
	public int getEnemyHealth() {
		return enemyHealth;
	}

	/**
	 * Set Enemy Health
	 * @param enemyHealth
	 */
	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}

}
