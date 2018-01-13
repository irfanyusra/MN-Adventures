/**
 *The "Game" class.
 *@Author Yusra Irfan 
 *@Version 1.4 
 *Date: 16th June 2016
 *Purpose: This code is for a game, the game is like 'Mario' with 'Marvel' characters. The objective of the game is to collect coins, clear levels 
	and beat the boss enemy. This game has four levels, power ups, coins, enemies, secret levels and more.
 *References
 * Camera Class: https://www.youtube.com/watch?v=vdcOIwkB6dA
 * 		- Learned how to move the camera on the player
 * Sprite and SpriteSheet Class: https://www.youtube.com/watch?v=jedilHUPO7c
 * 		-  Learned how to use sprite sheets and used the methods in the video
 * Map Class - Level design - https://www.youtube.com/watch?v=1TFDOT1HiBo
 * 		- Took the idea of converting pixels into a level
 */
package mainPackage;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import mainPackage.graphics.Sprite;
import mainPackage.graphics.SpriteSheet;
import mainPackage.input.KeyInput;
import mainPackage.input.MouseInput;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	//declaring Integers and String
	private static int width=1080;//width of the canvas
	private static int height=730; //height of the canvas
	private static int level=1;//level number
	private static int coins=0;//number of coins 
	private static int gScreenTime=0;// Time of Screen
	private static int pHealth = 100; //player health

	private static String title = "Game";//name of the game

	//declaring Sprite and Sprite Sheet
	private static SpriteSheet sheet; //sprite sheet
	private static SpriteSheet sheetE; //sprite sheet

	private static Sprite deadpool [] = new Sprite [13] ; //array of sprite for deadpool
	private static Sprite spiderman [] = new Sprite [13] ;//array of sprite for spiderman
	private static Sprite playerImage[]= new Sprite [13] ;//current array of sprite for the player
	private static Sprite enemy [] = new Sprite [8] ;//array of sprite for enemy
	private static Sprite enemyBoss [] = new Sprite [13] ;//array of sprite for enemy

	private static Sprite grass; //sprite for grass
	private static Sprite mushroom; //sprite for mushroom (power up)
	private static Sprite powerUp; //sprite for Power Up Tile
	private static Sprite usedPowerUp; //sprite for used Power Up tile
	private static Sprite pipe; //sprite for pipes
	private static Sprite coin; //sprite for coins
	private static Sprite dirt; //sprite for dirt
	private static Sprite portal; //sprite for portal

	//declaring Image
	private static BufferedImage level1;//image for level 1
	private static BufferedImage level2;//image for level 2
	private static BufferedImage level3;//image for level 3
	private static BufferedImage level4;//image for level 4
	private static BufferedImage helpPic;//image for help

	private static BufferedImage clouds;//image for level 2

	//declaring objects 
	private static Camera cam; 
	private static Map map;
	private static GameGraphics gameG;
	private Thread thread;

	//declaring booleans
	private boolean running =false;//game running
	private static boolean gScreen=true;//screen
	private static boolean gameOver = false;//Game Over
	private static boolean pause=false;//Game paused
	private static boolean menuS=true;//Menu Screen
	private static boolean play=false;//Game play
	private static boolean help=false;//Game Help

	public Game() {
		Dimension size = new Dimension(width,height);//initializing Canvas Size
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}
	public void init(){
		//initializing objects 
		map= new Map();
		sheet = new SpriteSheet("/sprite.png");
		sheetE=new SpriteSheet("/enemyS.png");

		cam = new Camera ();
		gameG = new GameGraphics ();

		//Listeners 
		addKeyListener(new KeyInput());
		addMouseListener(new MouseInput());

		//initializing Sprite and Sprite Arrays
		grass = new Sprite (sheet,2,1);
		dirt = new Sprite (sheet,7,1);
		mushroom = new Sprite (sheet,3,1);
		powerUp = new Sprite (sheet,4,1);
		usedPowerUp = new Sprite (sheet,5,1);
		pipe = new Sprite (sheet,6,1);
		coin = new Sprite (sheet,8,1);
		portal = new Sprite (sheet,9,1);

		for(int i=0; i<getDeadpool().length;i++){
			getDeadpool() [i] = new Sprite (sheet,i+1,5);
		}

		for(int i=0; i<getSpiderman().length;i++){
			getSpiderman() [i] = new Sprite (sheet,i+1,2);
		}
		for(int i=0; i<getEnemyBoss().length;i++){
			getEnemyBoss() [i] = new Sprite (sheetE,i+1,1);
		}

		for(int i=0; i<getEnemyImage().length;i++){
			getEnemyImage() [i] = new Sprite (sheet,i+1,3);
		}
		playerImage=getDeadpool();

		//initializing images 
		try {
			level1 = ImageIO.read(getClass().getResource("/level1.png"));
			level2 = ImageIO.read(getClass().getResource("/level2.png"));
			level3 = ImageIO.read(getClass().getResource("/level3.png"));
			level4 = ImageIO.read(getClass().getResource("/level4.png"));
			helpPic = ImageIO.read(getClass().getResource("/help.png"));

			clouds = ImageIO.read(getClass().getResource("/clouds1.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start Method 
	 */
	private synchronized void start(){
		if (running)
			return;
		running =true;
		thread=new Thread(this,"Thread");
		thread.start();
	}

	/**
	 * Stop Method
	 */
	private synchronized void stop(){
		if (!running)
			return;
		running =false;
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	/**
	 * Run Method
	 */
	public void run() {
		init();//calling initial method
		requestFocus();//requesting focus
		long lastTime= System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0/60.0;

		while (running){
			long now = System.nanoTime();
			delta +=(now-lastTime)/ns;
			lastTime=now;
			while (delta>=1){
				update();//update method
				delta--;
			}
			paint();//paint method
		}
		stop();
	}

	/**
	 * Gets Frame Width
	 * @return returns Integer width
	 */
	public static int getFrameWidth(){
		return width;
	}

	/**
	 * Gets Frame Height
	 * @return returns Integer height
	 */
	public static int getFrameHeight(){
		return height;
	}

	/**
	 * Paint Method
	 */
	public void paint() {
		//Buffering
		BufferStrategy bs = getBufferStrategy(); 
		if (bs==null){
			createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();//declaring graphics 
		g.setColor(Color.black);//background
		g.fillRect(0, 0, getWidth(), getHeight());

		gameG.paint(g);//paint method in GameGraphics Class

		if (!gScreen &&!pause&&play)
		{	
			g.setColor(new Color (144,203,236));//background (blue)
			g.fillRect(0, 0, getWidth(), getHeight());
			g.translate(getCam().getX(),getCam().getY());//translates the Camera Class

			for (int i= 0; i< clouds.getWidth()*5; i+= clouds.getWidth()){
				g.drawImage(clouds, i, 550, this);
				g.drawImage(clouds, i, 1250, this);
			}

		}
		if (!gScreen &&!pause)
			map.paint(g);//paints the levels
		g.dispose();
		bs.show();
	}

	/**
	 * Update
	 */
	public void update() {
		gameG.update();//update method in GameGraphics Class
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Canvas setup
		Game game = new Game();
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}

	/**
	 * Gets Enemy Image
	 * @return returns enemy 
	 */
	public static Sprite[] getEnemyImage() {
		return enemy;
	}

	/**
	 * Sets Enemy Image
	 * @param enemy
	 */
	public static void setEnemyImage(Sprite enemy[]) {
		Game.enemy = enemy;
	}

	/**
	 * Gets Player Image
	 * @return returns Sprite playerImage
	 */
	public static Sprite[] getPlayerImage() {
		return playerImage;
	}

	/**
	 * Sets Player Image
	 * @param playerImage
	 */
	public static void setPlayerImage(Sprite playerImage[]) {
		Game.playerImage = playerImage;
	}

	/**
	 * Gets Deadpool's Image 
	 * @return return Sprite deadpool
	 */
	public static Sprite[] getDeadpool() {
		return deadpool;
	}

	/**
	 * Sets Deadpool's Image
	 * @param deadpool
	 */
	public static void setDeadpool(Sprite deadpool[]) {
		Game.deadpool = deadpool;
	}

	/**
	 * Gets Level 1 Image
	 * @return returns BufferedImage level1
	 */
	public static BufferedImage getLevel1() {
		return level1;
	}

	/**
	 * Gets Level 2 Image
	 * @return returns BufferedImage level2
	 */
	public static BufferedImage getLevel2() {
		return level2;
	}

	/**
	 * Gets Help 
	 * @return returns boolean help
	 */
	public static boolean isHelp() {
		return help;
	}

	/**
	 * Sets Help
	 * @param help
	 */
	public static void setHelp(boolean help) {
		Game.help = help;
	}

	/**
	 * Gets Coins
	 * @return returns Integer Coins
	 */ 
	public static int getCoins() {
		return coins;
	}

	/**
	 * Sets Coins
	 * @param coins
	 */
	public static void setCoins(int coins) {
		Game.coins = coins;
	}

	/**
	 * Gets Enemy Image
	 * @return returns Sprite Array enemy 
	 */
	public static Sprite[] getEnemy() {
		return enemy;
	}

	/**
	 * Gets Grass Image
	 * @return returns Sprite grass
	 */
	public static Sprite getGrass() {
		return grass;
	}

	/**
	 * Gets Mushroom Image
	 * @return returns Sprite mushroom
	 */
	public static Sprite getMushroom() {
		return mushroom;
	}

	/**
	 * Gets Power Up Image 
	 * @return returns Sprite powerUp
	 */
	public static Sprite getPowerUp() {
		return powerUp;
	}

	/**
	 * Gets Used Power Up Image
	 * @return returns Sprite usedPowerUp
	 */
	public static Sprite getUsedPowerUp() {
		return usedPowerUp;
	}

	/**
	 * Gets Pipe Image
	 * @return returns Sprite pipe
	 */
	public static Sprite getPipe() {
		return pipe;
	}

	/**
	 * Gets Coin Image
	 * @return returns Sprite coin
	 */
	public static Sprite getCoin() {
		return coin;
	}

	/**
	 * Gets the Level Number
	 * @return returns Integer level
	 */
	public static int getLevel() {
		return level;
	}

	/**
	 * Sets Level
	 * @param level
	 */
	public static void setLevel(int level) {
		Game.level = level;
	}

	/**
	 * Gets GScreen
	 * @return returns boolean gScreen
	 */
	public static boolean isGScreen() {
		return gScreen;
	}

	/**
	 * Sets GScreen
	 * @param GScreen
	 */
	public static void setGScreen(boolean gScreen) {
		Game.gScreen = gScreen;
	}

	/**
	 * Gets Game Over
	 * @return returns boolean gameOver
	 */
	public static boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Sets Game Over
	 * @param gameOver
	 */
	public static void setGameOver(boolean gameOver) {
		Game.gameOver = gameOver;
	}

	/**
	 * Gets Pause
	 * @return returns boolean pause
	 */
	public static boolean isPause() {
		return pause;
	}

	/**
	 * Sets Pause 
	 * @param pause
	 */
	public static void setPause(boolean pause) {
		Game.pause = pause;
	}

	/**
	 * Gets Menu Screen
	 * @return returns boolean menuS
	 */
	public static boolean isMenuS() {
		return menuS;
	}

	/**
	 * Sets Menu Screen
	 * @param menuS
	 */
	public static void setMenuS(boolean menuS) {
		Game.menuS = menuS;
	}

	/**
	 * Gets Play
	 * @return returns boolean play
	 */
	public static boolean isPlay() {
		return play;
	}

	/**
	 * Sets Play 
	 * @param play
	 */
	public static void setPlay(boolean play) {
		Game.play = play;
	}

	/**
	 * Gets Camera
	 * @return returns Camera cam
	 */
	public static Camera getCam() {
		return cam;
	}

	/**
	 * Gets Spiderman Image
	 * @return returns Sprite spiderman
	 */
	public static Sprite[] getSpiderman() {
		return spiderman;
	}



	/**
	 * Gets Screen Time
	 * @return returns Integer gScreenTime
	 */
	public static int getGScreenTime() {
		return gScreenTime;
	}

	/**
	 * Sets GScreenTime
	 * @param gScreenTime
	 */
	public static void setGScreenTime(int gScreenTime) {
		Game.gScreenTime = gScreenTime;
	}

	/**
	 * Gets Player Health
	 * @return returns Integer pHealth
	 */
	public static int getpHealth() {
		return pHealth;
	}

	/**
	 * Sets Player Health
	 * @param pHealth
	 */
	public static void setpHealth(int pHealth) {
		Game.pHealth = pHealth;
	}

	/**
	 * Gets Dirt Image
	 * @return returns Sprite dirt
	 */
	public static Sprite getDirt() {
		return dirt;
	}

	/**
	 * Gets Level 3 Image
	 * @return returns level3
	 */
	public static BufferedImage getLevel3() {
		return level3;
	}


	/**
	 * Gets Enemy SpriteSheet 
	 * @return returns SpriteSheet sheetE
	 */
	public static SpriteSheet getSheetE() {
		return sheetE;
	}

	/**
	 * Get Enemy Boss Sprite 
	 * @return returns Sprite Array enemyBoss
	 */
	public static Sprite[] getEnemyBoss() {
		return enemyBoss;
	}

	/**
	 * Get Level 4 Image 
	 * @return BufferedImage level4
	 */
	public static BufferedImage getLevel4() {
		return level4;
	}

	/**
	 * Get Portal Sprite 
	 * @return returns Sprite Portal
	 */
	public static Sprite getPortal() {
		return portal;
	}

	/**
	 * Gets Map 
	 * @return Map map
	 */
	public static Map getMap() {
		return map;
	}

	/**
	 * Get Help Picture
	 * @return BufferedImage helpPic
	 */
	public static BufferedImage getHelpPic() {
		return helpPic;
	}

}

