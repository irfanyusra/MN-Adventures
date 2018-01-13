/**
*The "KeyInput" class.
*@Author Yusra Irfan 
*@Version 1.4 
*Date: 16th June 2016
 */

package mainPackage.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mainPackage.*;
import mainPackage.entity.Attack;
import mainPackage.entity.Entity;
import mainPackage.tile.Tile;

public class KeyInput implements KeyListener {
	//declaring and initializing variables
	Game game = new Game(); //
	private static boolean pipe=false;//player in the pipe

	/**
	 * Key Pressed Method 
	 */
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();

		for (int i=0;i<Game.getMap().getEntity().size();i++){//goes through entity linked list 
			Entity en = Game.getMap().getEntity().get(i);//declaring and initializing Entity
			if (en.getId()==Id.player){
				if (key== KeyEvent.VK_SPACE){
					if (en.isCanAttack()){//player can attack
						en.setAttacking(true);//player attacking is set to true 

						if 	(en.getNumOfattack()>=5)//num of attacks more than 5
							en.setCanAttack(false);//player cannot attack
						else 
							en.setNumOfattack(en.getNumOfattack()+1);//increasing num of attacks 

						if (en.getFacing()==1)	//left
							Game.getMap().addEntity(new Attack((int)en.getxEntity(),(int)en.getyEntity()+10,45,45,-5, Id.attack,en,Game.getMap()));	

						else if (en.getFacing()==3)//right
							Game.getMap().addEntity(new Attack(en.getxEntity(),en.getyEntity()+10,32,32,5, Id.attack,en, Game.getMap()));

					}
					if (!(en.isCanAttack())&&en.getNumOfSecAttack()>=70){//cannot attack or number of seconds is not greater than 70
						en.setCanAttack(true);//player can attack
						en.setNumOfSecAttack(0);//number of seconds is set to 0
						en.setNumOfattack(0);//number of attacks set to 0
					}
					setPipe(false);
				}
				if (key== KeyEvent.VK_UP){
					if(!en.isJumping()){//player not jumping 
						en.setJumping(true);//set jumping to true
						en.setGravity(8.0);//set gravity 
						setPipe(false);
					}
				}
				if (key== KeyEvent.VK_DOWN)
					setPipe(true);//going down pipe set to true 

				if (key== KeyEvent.VK_LEFT){
					for (int j=0; j<Game.getMap().getTile().size();j++){//goes through tile linked list
						Tile t=Game.getMap().getTile().get(j);//declaring and initializing tile 
						if (t.getId()!=Id.pipe){//tile is not pipe
							en.setVelX(-5);
							en.setFacing(1);//left 
							en.setAttacking(false);
						}
					}
					setPipe(false);
				}

				if (key== KeyEvent.VK_RIGHT){
					for (int j=0; j<Game.getMap().getTile().size();j++){//goes through tile linked list
						Tile t=Game.getMap().getTile().get(j);//declaring and initializing tile 
						if (t.getId()!=Id.pipe){//tile is not pipe
							en.setVelX(5);
							en.setFacing(3);//right 
							en.setAttacking(false);
						}
					}
					setPipe(false);
				}

				if (key== KeyEvent.VK_P){
					if(Game.isPause())//game paused 
						Game.setPause(false);//pause set to false
					else if(!Game.isPause())//game not paused 
						Game.setPause(true);//pause set to true

				}
			}
		}
	}

	/**
	 * Key Released 
	 */
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();

		for (int i=0;i<Game.getMap().getEntity().size();i++)//goes through entity linked list
		{
			Entity en = Game.getMap().getEntity().get(i);//declaring and initializing Entity
			if (en.getId()==Id.player){//id is player 
				if (key== KeyEvent.VK_UP){
					en.setVelY(0);
				}
				
				if (key== KeyEvent.VK_LEFT){
					en.setVelX(0);
				}
				
				if (key== KeyEvent.VK_RIGHT){
					en.setVelX(0);
				}
			}
		}
	}

	/**
	 * Key Typed Method
	 */
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * Gets Pipe
	 * @return returns boolean pipe
	 */
	public static boolean isPipe() {
		return pipe;
	}

	/**
	 * Sets Pipe 
	 * @param pipe
	 */
	public static void setPipe(boolean pipe) {
		KeyInput.pipe = pipe;
	}

}
