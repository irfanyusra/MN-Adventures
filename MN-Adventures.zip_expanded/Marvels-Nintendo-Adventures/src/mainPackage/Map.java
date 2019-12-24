/**
*The "Map" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import mainPackage.entity.ChangePlayer;
import mainPackage.entity.Entity;
import mainPackage.entity.Player;
import mainPackage.tile.Coin;
import mainPackage.tile.LevelComplete;
import mainPackage.tile.Pipe;
import mainPackage.tile.PowerUpBlock;
import mainPackage.tile.Tile;
import mainPackage.tile.Wall;
import mainPackage.entity.enemy.BossEnemy;
import mainPackage.entity.enemy.Enemy;

public class Map  {

	private LinkedList <Entity> entity = new LinkedList <Entity> ();//Linked List for Entity 
	private LinkedList <Tile> tile = new LinkedList <Tile> ();//Linked List for Tile

	/**
	 * Paint Method
	 * @param g
	 */
	public void paint (Graphics g){

		for (int i=0;i<Game.getMap().tile.size();i++)//goes through the tile linked list 
		{
			Tile ti = Game.getMap().tile.get(i);//declares and initializes Tile
			ti.paint(g);//Paint Method in Tile
		}

		for (int i=0;i<Game.getMap().entity.size();i++)//goes through the entity linked list
		{
			Entity en = Game.getMap().entity.get(i);//declares and initializes Entity
			en.paint(g);//Paint Method in Entity
		}

	}
	/**
	 * Update Method
	 */
	public void update(){
		for (int i=0;i<Game.getMap().tile.size();i++)//goes through the tile linked list
		{
			Tile ti = Game.getMap().tile.get(i);//declares and initializes Tile
			ti.update();//Update Method in Tile
		}	

		for (int i=0;i<Game.getMap().entity.size();i++)//goes through the entity linked list
		{
			Entity en = Game.getMap().entity.get(i);//declares and initializes Entity
			en.update();//Update Method in Entity
		}
	}

	/**
	 * Add Entity
	 * @param en
	 */
	public void addEntity(Entity en){
		entity.add(en);
	}

	/**
	 * Remove Entity
	 * @param en
	 */
	public void removeEntity(Entity en){
		entity.remove(en);
	}

	/**
	 * Add Tile
	 * @param ti
	 */
	public void addTile(Tile ti){
		tile.add(ti);
	}

	/**
	 * Remove Tile
	 * @param ti
	 */
	public void removeTile(Tile ti){
		tile.remove(ti);
	}

	/**
	 * Clears Level
	 */
	public void clearLevel(){
		entity.clear();//Clears Entity Linked List
		tile.clear();//Clear Tile Linked List 
	}

	/**
	 * Switch Level
	 */
	public void switchLevel(){
		clearLevel();//clears the old level

		switch(Game.getLevel()){//get the level number
		case 1: 
			createLevel(Game.getLevel1());//creates level 1
			break;
		case 2: 
			createLevel(Game.getLevel2());//creates level 2
			break;

		case 3: 
			createLevel(Game.getLevel3());//creates level 3
			break;

		case 4: 
			createLevel(Game.getLevel4());//creates level 3
			break;


		}
	}

	/**
	 * Create Level
	 * @param level
	 */
	public void createLevel(BufferedImage level){
		int w = level.getWidth();//width of the image
		int h = level.getHeight();//height of the image
		for (int y=0; y<h;y++){
			for (int x=0; x<w;x++){
				int pixel = level.getRGB(x, y);

				int red = (pixel >> 16)&0xff;
				int green = (pixel >> 8)&0xff;
				int blue = (pixel)&0xff;

				if (red==255&&green==255&&blue==255) addTile (new Wall (x*64, y*64,64,64,Id.wall,Game.getGrass(),this));//white 
				if (red==255&&green==0&&blue==0) addEntity(new Enemy(x*64, y*64,64,64,Id.enemy , this,Game.getEnemyImage()));//red
				if (red==255&&green==0&&blue==255) addTile(new PowerUpBlock(x*64, y*64,64,64, Id.powerUpM , this, Game.getMushroom()));//Fuchsia			
				if (red==0&&green==255&&blue==0) addTile(new Pipe(x*64, y*64,64,64*2, Id.pipe , this));//green
				if (red==255&&green==255&&blue==0) addTile(new Coin(x*64, y*64,64,64, Id.coin , this));//yellow
				if (red==105&&green==0&&blue==105) addTile(new LevelComplete(x*64, y*64,64,64*2, Id.levelEnd , this));//purple
				if (red==120&&green==120&&blue==0) addTile(new PowerUpBlock(x*64, y*64,64,64, Id.powerUpC , this, Game.getCoin()));//gold	
				if (red==0&&green==0&&blue==255) addEntity(new Player(x*64, y*64,64,64,Id.player , this,Game.getPlayerImage()));//blue
				if (red==155&&green==155&&blue==155) addTile (new Wall (x*64, y*64,64,64,Id.outOfBounds,null,this));//gray 
				if (red==255&&green==155&&blue==155) addEntity (new ChangePlayer (x*64, y*64,64,64,Id.changePlayer,this,Game.getSpiderman()));//pink  
				if (red==255&&green==255&&blue==100) addTile(new Wall (x*64, y*64,64,64,Id.wall,Game.getDirt(),this));//peach
				if (red==255&&green==100&&blue==0) addTile(new Pipe(x*64, y*64,64,64*2, Id.pipeU , this));//orange
				if (red==255&&green==100&&blue==100) addEntity(new BossEnemy(x*64, y*64-30,96,96,Id.bossEnemy , this,Game.getEnemyBoss()));//peach-pink

			}
		}
	}
	
	/**
	 * Get Entity Linked List
	 * @return entity
	 */
	public LinkedList<Entity> getEntity() {
		return entity;
	}

	/**
	 * Get Tile Linked List
	 * @return tile
	 */
	public LinkedList<Tile> getTile() {
		return tile;
	}

}
