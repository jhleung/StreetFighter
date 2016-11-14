
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;

public class Water extends ElementalCharacter
{
	public final Image defaultImg1 = new ImageIcon(this.getClass().getResource("Default_Water.png")).getImage();
	public final Image defaultImg2 = new ImageIcon(this.getClass().getResource("Default_Water_Player2.png")).getImage();
	public final Image jump1 = new ImageIcon(this.getClass().getResource("Jump_Water.png")).getImage();
	public final Image jump2 = new ImageIcon(this.getClass().getResource("Jump_Water_Player2.png")).getImage();
	public final Image duck1 = new ImageIcon(this.getClass().getResource("Duck_Water.png")).getImage();
	public final Image duck2 = new ImageIcon(this.getClass().getResource("Duck_Water_Player2.png")).getImage();
	public final Image punchP11 = new ImageIcon(this.getClass().getResource("Punch1_Water.png")).getImage();
	public final Image punchP12 = new ImageIcon(this.getClass().getResource("Punch2_Water.png")).getImage();
	public final Image punchP21 = new ImageIcon(this.getClass().getResource("Punch1_Water_Player2.png")).getImage();
	public final Image punchP22 = new ImageIcon(this.getClass().getResource("Punch2_Water_Player2.png")).getImage();
	public final Image walkB1 = new ImageIcon(this.getClass().getResource("Walk1_Water.png")).getImage();
	public final Image walkF1 = new ImageIcon(this.getClass().getResource("Walk2_Water.png")).getImage();
	public final Image walkB2 = new ImageIcon(this.getClass().getResource("Walk1_Water_Player2.png")).getImage();
	public final Image walkF2 = new ImageIcon(this.getClass().getResource("Walk2_Water_Player2.png")).getImage();
	public final Image fall1 = new ImageIcon(this.getClass().getResource("Fall1_Water.png")).getImage();
	public final Image fall1P2 = new ImageIcon(this.getClass().getResource("Fall1_Water_Player2.png")).getImage();
	public final Image fall2 = new ImageIcon(this.getClass().getResource("Fall2_Water.png")).getImage();
	public final Image fall2P2 = new ImageIcon(this.getClass().getResource("Fall2_Water_Player2.png")).getImage();
	public final Image fall3 = new ImageIcon(this.getClass().getResource("Fall3_Water.png")).getImage();
	public final Image fall3P2 = new ImageIcon(this.getClass().getResource("Fall3_Water_Player2.png")).getImage();
	public final Image kick1 = new ImageIcon(this.getClass().getResource("Kick_Water.png")).getImage();
	public final Image kick2 = new ImageIcon(this.getClass().getResource("Kick_Water_Player2.png")).getImage();
	public final Image blast = new ImageIcon(this.getClass().getResource("Water_Blast.png")).getImage();
	public final Image blast2 = new ImageIcon(this.getClass().getResource("Water_Blast.png")).getImage();
	public final Image tornado = new ImageIcon(this.getClass().getResource("Tornado.png")).getImage();
	public final Image heal = new ImageIcon(this.getClass().getResource("Healing_Bubbles.png")).getImage();
	public final Image ice = new ImageIcon(this.getClass().getResource("Frozen_Terrain.png")).getImage();
	public final Image snow = new ImageIcon(this.getClass().getResource("Snowball.png")).getImage();
	
	public static Image characterImg;
	public static int x,y;
	public static ElementalCharacter ec;
	/**
	 *draws character facing left or right based on what player it is, 1 or 2
	 *player1 faces right, player2 faces left, o be instantiated in demoscene 
	 *@param xCoord the xcoordinate
	 *@param yCoord the ycoordinate
	 *@param img the image of the character
	 *@param e the other player's elemental character
	 *@param orientationLeft false whether it is facing left,true if facing right
     */
	public Water(int xCoord, int yCoord,Image img,ElementalCharacter e, boolean orientationLeft)
	{		
		super(xCoord,yCoord,e,img);
		if (orientationLeft)
		{
			characterImg = defaultImg1;
		}
		else
			characterImg = defaultImg2;
		ec = e;
		
	}
	/**
	 *Gets heal image for both players
	 *@return the heal image
	 */
	public Image getHeal() { return heal; }
	/**
	 *Gets ice image for both players
	 *@return the ice image
	 */
	public Image getIce() { return ice; }
	/**
	 *Gets snow image for both players
	 *@return the snow image
	 */
	public Image getSnow() { return snow; }
	/**
	 *Sets the opposing player's element
	 *@param c the opposing player's element
	 */
	public void setPlayer2(ElementalCharacter c)
	{
		ec=c;
	}
	/**
	 *Gets blast image for player2
	 *@return the blast image
	 */
	public Image getBlast2() { return blast2;	}	
	/**
	 *Gets blast image for player1
	 *@return the blast image
	 */
	public Image getBlast() { return blast;	}
	/**
	 *Gets jump image for both players
	 *@return the jump image
	 */
	public Image getJump(int player, boolean ep)	{if (player==1) return jump1; else return jump2;}
	/**
	 *Gets duck image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the duck image
	 */
	public Image getDuck(int player, boolean ep)  {if (player==1) return duck1; else return duck2;}
	/**
	 *Gets first punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first punch image
	 */
	public Image getPunch1(int player, boolean ep)  {if (player==1) return punchP11; else return punchP21;}
	/**
	 *Gets 2nd punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd punch image
	 */
	public Image getPunch2(int player, boolean ep)  {if (player==1) return punchP12; else return punchP22;}	
	/**
	 *Gets first walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first walk image
	 */
	public Image getWalkB(int player, boolean ep)  {if (player==1) return walkB1; else return walkB2;}
	/**
	 *Gets 2nd walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd walk image
	 */
	public Image getWalkF(int player, boolean ep)  {if (player==1) return walkF1; else return walkF2;}
	/**
	 *Gets default image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the default image
	 */
	public Image getDefault(int player, boolean ep)  {if (player==1) return defaultImg1; else return defaultImg2;}
	/**
	 *Gets kick image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the kickt image
	 */
	public Image getKick(int player, boolean ep) {if (player==1) return kick1; else return kick2;}
	/**
	 *Gets fall image for both players
	 *@param player the player number
	 *@param fallNum the 1st 2nd or 3rd fall image
	 *@return the default image
	 */
	public Image getFall(int player, int fallNum, boolean ep)
	{
		if (player==1)
		{
			if (fallNum==1) return fall1;
			else if (fallNum==2) return fall2;
			else return fall3;
		}
		else 
		{
			if (fallNum==1) return fall1P2;
			else if (fallNum==2) return fall2P2;
			else return fall3P2;
		}
	}
//	public void drawCharacter(Graphics g,int charx, int chary, Image img)
//    {
//    	super.drawCharacter(characterImg);
//    	x=charx; //update character's coordinates whenever it moves 
//    	y=chary;
//    	characterImg = img;
//   	   Graphics2D g2 = (Graphics2D) g;
//   	   g2.drawImage(characterImg,charx,chary,this);  	
     
//    }
    public void jump()
    {
    	
    }
    //spread 3 fire rectangles randomly on the floor
//    public void ability1(Graphics g) 
//    {
//      	Graphics2D g2 = (Graphics2D) g;
//      	for (int i = 1; i<=3; i++)
//      		g2.drawImage(new ImageIcon("fireSpikes.png").getImage(),(int)(Math.random()*1000)+1,500,this);
//      		
//    }
    
    public void ability2(Graphics g)
    {
    	
    }
    
    public void special(Graphics g)
    {
    	
    }
}