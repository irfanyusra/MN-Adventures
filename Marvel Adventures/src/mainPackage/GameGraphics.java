/**
*The "GameGraphics" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */
package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainPackage.entity.Entity;

public class GameGraphics  {

	Font font1 = new Font("Courier",Font.BOLD,70);
	Font font2= new Font("Courier",Font.BOLD,40);
	Font font3= new Font("Courier",Font.BOLD,50);

	/**
	 * Update Method
	 */
	public void update(){
		if (Game.isMenuS()){//Menu is true
			if (Game.isPlay()){//Play is true
				Game.getMap().switchLevel();//creates level
				Game.setMenuS(false); //set menuS to false
				Game.setCoins(0);// set Coins to 0
			}
		}
		if (!Game.isMenuS()){//Menu is false
			if (!Game.isPause()){//Game pause is false
				Game.getMap().update();//Update Method in Handler Class

				for (int i=0;i<Game.getMap().getEntity().size();i++)//Goes through the entity class
				{
					Entity e = Game.getMap().getEntity().get(i);//declares and initializes entity
					if (e.getId()==Id.player){//check entity Id
						Game.getCam().update(e);//update method in camera class
					}
				}
				if (Game.isGScreen())//if GScreen is true
					Game.setGScreenTime(Game.getGScreenTime() + 1);//increases GscreenTime by 1

				if (Game.getGScreenTime()>=150){//time for screen
					Game.setGScreen(false);//sets the screen to false
					Game.setGScreenTime(0);//sets the time to false

					if (Game.getLevel()>0)
						Game.getMap().switchLevel();//changes level

					else if (Game.getLevel()==0||Game.isGameOver()){//game completed 
						Game.setMenuS(true);//sets menu to true
						Game.setPlay(false);//sets play to false
						Game.setpHealth(100);//sets the layer health to 100
						Game.setLevel(1);//set the level to 1
						Game.setCoins(0);//set the number of coins to 0
						Game.setPlayerImage(Game.getDeadpool());//sets the player image to default image 			

						Game.getMap().clearLevel();//clear the level
					}
				}
			}
		}
	}

	/**
	 * Paint Method
	 * @param g
	 */
	public void paint(Graphics g){
		if (Game.isMenuS()){//Menu Screen is true
			g.setColor(Color.WHITE);
			g.setFont(font1);//setting Font
			g.drawString("MENU", (Game.getFrameWidth()/2)-70,200 );

			g.drawRect(450, 250, 200, 70);//for play
			g.drawRect(450, 350, 200, 70);//for help
			g.drawRect(450, 450, 200, 70);//for quit

			g.setFont(font2);//setting Font
			g.drawString("PLAY", 500,295 );
			g.drawString("HELP",500,395 );
			g.drawString("QUIT", 500,495 );
		}

		else if (Game.isHelp()){//Help Screen is true
			g.setColor(Color.WHITE);
			g.setFont(font1);//setting Font
			g.drawString("HELP", 450, 150);
			g.setFont(font2);//setting Font
			g.drawString("SPACEBAR", 520,530 );
			g.drawString("ARROW KEYS", 740,530 );
		
			
			g.setColor(Color.BLUE);
			g.drawString("ATTACK", 540,350 );
			g.drawString("MOVE", 795,270 );
			g.drawString("PAUSE", 270,300 );

			g.drawImage(Game.getHelpPic(), 290, 120, 700,400, null);
			g.setColor(Color.WHITE);
			
			g.drawRect(150, 550, 140, 70);//for back
			g.drawString("Back", 170,595 );
		}
		
		else if (Game.isPlay()){
			if (Game.isPause()){
				g.setColor(Color.WHITE);
				g.setFont(font1);//setting Font
				g.drawString("PAUSED", 430, 200);
				g.setFont(font2);//setting Font

				g.drawRect(450, 250, 200, 70);//for play
				g.drawRect(450, 350, 200, 70);//for help
				g.drawRect(450, 450, 200, 70);//for quit

				g.drawString("PLAY", 500,295 );
				g.drawString("HELP",500,395 );
				g.drawString("QUIT", 500,495 );
			}

			else if (Game.isGScreen()){//GScreen is true
				g.setFont(font3);//setting Font
				g.setColor(Color.WHITE);

				if (Game.getLevel()==0)//if Game is Completed
					g.drawString("Game Completed", 370,Game.getFrameHeight()/2-10 );
				else if (Game.getLevel()>0) 
					g.drawString("Level "+Game.getLevel(), 470,Game.getFrameHeight()/2-10 );
		
				else if (Game.isGameOver()&&Game.getLevel()==-1){//Game is over
					g.drawString("Game Over", (Game.getFrameWidth()/2)-100,Game.getFrameHeight()/2-10 );
				}
			}
		}
	}
}
