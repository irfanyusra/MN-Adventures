/**
*The "MouseInput" class.
*Instructor: Mr. Sayed
*Assignment Name: Game - Final Project 
*Class: ICS 4U0-B
*@Author Yusra Irfan 
*@Version 1.4 
*Date of Submission: 16th June 2016
 */

package mainPackage.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mainPackage.Game;


public class MouseInput extends MouseAdapter {

	/**
	 *Mouse Pressed Method
	 */
	public void mousePressed(MouseEvent e){
		//declaring variables
		int mx=e.getX();//gets x
		int my=e.getY();//gets y

		if (Game.isMenuS()){//menu screen is true 
			if (mx>450&&mx<650&&my>250&&my<320){//play button 
				Game.setPlay(true);	//play is true 
				Game.setLevel(1);//level set to 1
			}

			if (mx>450&&mx<650&&my>350&&my<420){//help button 
				Game.setHelp(true);	//help is true
				Game.setMenuS(false); //menu screen set to false
				Game.setPlay(false);//play set to false
			}

			if (mx>450&&mx<650&&my>450&&my<520){//quit button 
				System.exit(1);//canvas exit 
			}
		}

		if (Game.isPause()){//pause is true 
			if (mx>450&&mx<650&&my>250&&my<320){//play button 
				Game.setPlay(true);	//play is set to true 
				Game.setPause(false); //pause is set to false
			}

			if (mx>450&&mx<650&&my>350&&my<420){//help button 
				Game.setHelp(true);	//help is set to true
				Game.setPause(true);//pause is set to true 
			}

			if (mx>450&&mx<650&&my>450&&my<520){//quit button 
				Game.setPlay(false);//play is set to false	
				Game.setMenuS(true); //menu screen is set to true 
			}
		}

		if (Game.isHelp()){//help is true
			if (mx>150&&mx<290&&my>550&&my<620){//back button 
				Game.setHelp(false);//help is set to false
				if (!Game.isPlay())//play is false
					Game.setMenuS(true);//menu screen is set to true 
				else 
					Game.setPause(true);//pause is set to true 
			}
		}
	}
	
	/**
	 * Mouse Released Method
	 */
	public void mouseReleased(MouseEvent e){

	}
}
