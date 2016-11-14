
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 

import java.awt.*;
import javax.swing.*;

/**
 *contains drawbackground method which will be called in outerscene, the outer panel with the paincomponent
 *also instantiates an elementalcharacter that will be acsessed by the outerscene class so that the outerscene can use this elementalcharacter to 
 *draw ability and basic jump,duck, walk, punch, kick images
 */
public class ActionScene extends JPanel 
{	
   public static ElementalCharacter eleChar1,eleChar2;
   
   
   public ActionScene(ElementalCharacter ec1,ElementalCharacter ec2)
   {
   	  eleChar1 = ec1;
   	  eleChar2 = ec2;
   }
	 
   /**
    *Draws the background of the stage
    *@param g the Graphics to use 
    *@param x the x coordinate
    *@param y the y coordinate
    *@param bg the background image
    */
   public void drawBackground(Graphics g,int x, int y, Image bg)
   {
   	  Graphics2D g2 = (Graphics2D) g;
   	  g2.drawImage(bg,x,y,this);  	
   }
}