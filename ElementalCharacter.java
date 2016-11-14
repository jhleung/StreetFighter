
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

//superclass for characters, providing ability methods and the drawCharacter method
public class ElementalCharacter extends JComponent
{

	public static Image characterImg;
	public static int x,y;
	public static ElementalCharacter ec;
	
	public ElementalCharacter(int xc, int yc,ElementalCharacter e, Image img)
	{
		x= xc;
		y = yc;
		characterImg = img;
		ec=e;
	}
	/**
	 *Gets heal image for both players
	 *@return the heal image
	 */
	public Image getHeal() { return null; }
	/**
	 *Gets ice image for both players
	 *@return the ice image
	 */
	public Image getIce() { return null; }
	/**
	 *Gets snow image for both players
	 *@return the snow image
	 */
	public Image getSnow() { return null;  }
	/**
	 *Sets the opposing player's element
	 *@param c the opposing player's element
	 */
	public void setPlayer2(ElementalCharacter c)
	{
		
	}
	/**
	 *Gets blast image for player2
	 *@return the blast image
	 */
	public Image getBlast2() { return null; 	}	
	/**
	 *Gets blast image for player1
	 *@return the blast image
	 */
	public Image getBlast() {return null; 	}
	/**
	 *Gets jump image for both players
	 *@return the jump image
	 */
	public Image getJump(int player, boolean ep)	{return null; }
	/**
	 *Gets duck image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the duck image
	 */
	public Image getDuck(int player, boolean ep)  {return null; }
	/**
	 *Gets first punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first punch image
	 */
	public Image getPunch1(int player, boolean ep)  {return null; }
	/**
	 *Gets 2nd punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd punch image
	 */
	public Image getPunch2(int player, boolean ep)  {return null; }	
	/**
	 *Gets first walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first walk image
	 */
	public Image getWalkB(int player, boolean ep)  {return null; }
	/**
	 *Gets 2nd walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd walk image
	 */
	public Image getWalkF(int player, boolean ep)  {return null; }
	/**
	 *Gets default image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the default image
	 */
	public Image getDefault(int player, boolean ep)  {return null; }
	/**
	 *Gets kick image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the kickt image
	 */
	public Image getKick(int player, boolean ep) {return null; }
	/**
	 *Gets fall image for both players
	 *@param player the player number
	 *@param fallNum the 1st 2nd or 3rd fall image
	 *@return the default image
	 */
	public Image getFall(int player, int fallNum, boolean ep)
	{
		return null; 
	}
	//draws the character with a given image (normal stance, jump, duck and walk stances, and ability stances)
	//will be called when character is needed to be drawn due to pressing a key a thus performing an action
	public void drawCharacter(Graphics g,int charx, int chary, Image img)
    {
       x=charx; //update character's coordinates whenever it moves 
       y=chary;
       characterImg = img;
   	   Graphics2D g2 = (Graphics2D) g;
   	   g2.drawImage(characterImg,charx,chary,this);  	
     
    }    
}