
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;

/**
 *Draws the hitpoints bar for characters
 */
public class HitpointsBar extends JPanel
{

 /**
  *Constructor sets size to 100x200
  */
  public HitpointsBar()
  {
  	setSize(100,200);
  }
  /**
   *draws the hp bars
   *@param g the graphics to use
   *@param x the x coordinate
   *@param y the y coordinate
   *@param width the width o the hp bar
   *@param c the color
   */
  public void drawRect(Graphics g,int x ,int y,int width,Color c)
  {
  	g.setColor(c.darker());
  	g.fillRect(x,y,width,50);  
  }
 
}

