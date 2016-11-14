
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;
/**
 *Provides getters for all of Air's images so that other classes have access to them
 */
public class Earth extends ElementalCharacter
{
	public final Image defaultImg1 = new ImageIcon(this.getClass().getResource("Default_Earth.png")).getImage();
	public final Image defaultImg2 = new ImageIcon(this.getClass().getResource("Default_Earth_Player2.png")).getImage();
	public final Image jump1 = new ImageIcon(this.getClass().getResource("Jump_Earth.png")).getImage();
	public final Image jump2 = new ImageIcon(this.getClass().getResource("Jump_Earth_Player2.png")).getImage();
	public final Image duck1 = new ImageIcon(this.getClass().getResource("Duck_Earth.png")).getImage();
	public final Image duck2 = new ImageIcon(this.getClass().getResource("Duck_Earth_Player2.png")).getImage();
	public final Image punchP11 = new ImageIcon(this.getClass().getResource("Punch1_Earth.png")).getImage();
	public final Image punchP12 = new ImageIcon(this.getClass().getResource("Punch2_Earth.png")).getImage();
	public final Image punchP21 = new ImageIcon(this.getClass().getResource("Punch1_Earth_Player2.png")).getImage();
	public final Image punchP22 = new ImageIcon(this.getClass().getResource("Punch2_Earth_Player2.png")).getImage();
	public final Image walkB1 = new ImageIcon(this.getClass().getResource("Walk1_Earth.png")).getImage();
	public final Image walkF1 = new ImageIcon(this.getClass().getResource("Walk2_Earth.png")).getImage();
	public final Image walkB2 = new ImageIcon(this.getClass().getResource("Walk1_Earth_Player2.png")).getImage();
	public final Image walkF2 = new ImageIcon(this.getClass().getResource("Walk2_Earth_Player2.png")).getImage();
	public final Image fall1 = new ImageIcon(this.getClass().getResource("Fall1_Earth.png")).getImage();
	public final Image fall1P2 = new ImageIcon(this.getClass().getResource("Fall1_Earth_Player2.png")).getImage();
	public final Image fall2 = new ImageIcon(this.getClass().getResource("Fall2_Earth.png")).getImage();
	public final Image fall2P2 = new ImageIcon(this.getClass().getResource("Fall2_Earth_Player2.png")).getImage();
	public final Image fall3 = new ImageIcon(this.getClass().getResource("Fall3_Earth.png")).getImage();
	public final Image fall3P2 = new ImageIcon(this.getClass().getResource("Fall3_Earth_Player2.png")).getImage();
	public final Image kick1 = new ImageIcon(this.getClass().getResource("Kick_Earth.png")).getImage();
	public final Image kick2 = new ImageIcon(this.getClass().getResource("Kick_Earth_Player2.png")).getImage();
	public final Image blast = new ImageIcon(this.getClass().getResource("Earth_Blast.png")).getImage();
	public final Image blast2 = new ImageIcon(this.getClass().getResource("Earth_Blast.png")).getImage();
	public final Image tornado = new ImageIcon(this.getClass().getResource("Tornado.png")).getImage();
	
	public final Image defaultImg1Shield = new ImageIcon(this.getClass().getResource("Default_Earth_Shield.png")).getImage();
	public final Image defaultImg2Shield = new ImageIcon(this.getClass().getResource("Default_Earth_Shield_Player2.png")).getImage();
	public final Image jump1Shield = new ImageIcon(this.getClass().getResource("Jump_Earth_Shield.png")).getImage();
	public final Image jump2Shield = new ImageIcon(this.getClass().getResource("Jump_Earth_Shield_Player2.png")).getImage();
	public final Image duck1Shield = new ImageIcon(this.getClass().getResource("Duck_Earth_Shield.png")).getImage();
	public final Image duck2Shield = new ImageIcon(this.getClass().getResource("Duck_Earth_Shield_Player2.png")).getImage();
	public final Image punchP11Shield = new ImageIcon(this.getClass().getResource("Punch1_Earth_Shield.png")).getImage();
	public final Image punchP12Shield = new ImageIcon(this.getClass().getResource("Punch2_Earth_Shield.png")).getImage();
	public final Image punchP21Shield = new ImageIcon(this.getClass().getResource("Punch1_Earth_Shield_Player2.png")).getImage();
	public final Image punchP22Shield = new ImageIcon(this.getClass().getResource("Punch2_Earth_Shield_Player2.png")).getImage();
	public final Image walkB1Shield = new ImageIcon(this.getClass().getResource("Walk1_Earth_Shield.png")).getImage();
	public final Image walkF1Shield = new ImageIcon(this.getClass().getResource("Walk2_Earth_Shield.png")).getImage();
	public final Image walkB2Shield = new ImageIcon(this.getClass().getResource("Walk1_Earth_Shield_Player2.png")).getImage();
	public final Image walkF2Shield = new ImageIcon(this.getClass().getResource("Walk2_Earth_Shield_Player2.png")).getImage();
	public final Image fall1Shield = new ImageIcon(this.getClass().getResource("Fall1_Earth_Shield.png")).getImage();
	public final Image fall1P2Shield = new ImageIcon(this.getClass().getResource("Fall1_Earth_Shield_Player2.png")).getImage();
	public final Image fall2Shield = new ImageIcon(this.getClass().getResource("Fall2_Earth_Shield.png")).getImage();
	public final Image fall2P2Shield = new ImageIcon(this.getClass().getResource("Fall2_Earth_Shield_Player2.png")).getImage();
	public final Image fall3Shield = new ImageIcon(this.getClass().getResource("Fall3_Earth_Shield.png")).getImage();
	public final Image fall3P2Shield = new ImageIcon(this.getClass().getResource("Fall3_Earth_Shield_Player2.png")).getImage();
	public final Image kick1Shield = new ImageIcon(this.getClass().getResource("Kick_Earth_Shield.png")).getImage();
	public final Image kick2Shield = new ImageIcon(this.getClass().getResource("Kick_Earth_Shield_Player2.png")).getImage();
	
	public static Image characterImg;
	public static int x,y;
	public static ElementalCharacter ec;
	/*draws character facing left or right based on what player it is, 1 or 2
	 *player1 faces right, player2 faces left, o be instantiated in demoscene 
	 *@param xCoord the xcoordinate
	 *@param yCoord the ycoordinate
	 *@param img the image of the character
	 *@param e the other player's elemental character
	 *@param orientationLeft false whether it is facing left,true if facing right
     */
	public Earth(int xCoord, int yCoord,Image img,ElementalCharacter e, boolean orientationLeft)
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
	public Image getJump(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return jump1Shield;
			}
			else
			{
				return jump1;
			}
		}
		else
		{
			if(ep)
			{
				return jump2Shield;
			}
			else
			{
				return jump2;
			}	
		} 
			
	}
	/**
	 *Gets duck image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the duck image
	 */
	public Image getDuck(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return duck1Shield;
			}
			else
			{
				return duck1;
			}
		}
		else
		{
			if(ep)
			{
				return duck2Shield;
			}
			else
			{
				return duck2;
			}	
		} 
			
	}
	/**
	 *Gets first punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first punch image
	 */
	public Image getPunch1(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return punchP11Shield;
			}
			else
			{
				return punchP11;
			}
		}
		else
		{
			if(ep)
			{
				return punchP21Shield;
			}
			else
			{
				return punchP21;
			}	
		} 
			
	}
	/**
	 *Gets 2nd punch image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd punch image
	 */
	public Image getPunch2(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return punchP12Shield;
			}
			else
			{
				return punchP12;
			}
		}
		else
		{
			if(ep)
			{
				return punchP22Shield;
			}
			else
			{
				return punchP22;
			}	
		} 
			
	}
	
	/**
	 *Gets first walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the first walk image
	 */
	public Image getWalkB(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return walkB1Shield;
			}
			else
			{
				return walkB1;
			}
		}
		else
		{
			if(ep)
			{
				return walkB2Shield;
			}
			else
			{
				return walkB2;
			}	
		} 
			
	}		
	/**
	 *Gets 2nd walk image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the 2nd walk image
	 */
	public Image getWalkF(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return walkF1Shield;
			}
			else
			{
				return walkF1;
			}
		}
		else
		{
			if(ep)
			{
				return walkF2Shield;
			}
			else
			{
				return walkF2;
			}	
		} 
			
	}
	/**
	 *Gets default image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the default image
	 */
	public Image getDefault(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return defaultImg1Shield;
			}
			else
			{
				return defaultImg1;
			}
		}
		else
		{
			if(ep)
			{
				return defaultImg2Shield;
			}
			else
			{
				return defaultImg2;
			}	
		} 
			
	}
	/**
	 *Gets kick image for both players
	 *@param player the player number
	 *@param boolean ep whether character has an enhanced effect
	 *@return the kickt image
	 */
	public Image getKick(int player, boolean ep)
	{
		if (player==1)
		{
			if(ep)
			{
				return kick1Shield;
			}
			else
			{
				return kick1;
			}
		}
		else
		{
			if(ep)
			{
				return kick2Shield;
			}
			else
			{
				return kick2;
			}	
		} 
			
	}
	/**
	 *Gets fall image for both players
	 *@param player the player number
	 *@param fallNum the 1st 2nd or 3rd fall image
	 *@return the default image
	 */
	public Image getFall(int player, int fallNum, boolean ep)
	{
		if(ep)
		{
			if (player==1)
			{
				if (fallNum==1) return fall1Shield;
				else if (fallNum==2) return fall2Shield;
				else return fall3Shield;
			}
			else 
			{
				if (fallNum==1) return fall1P2Shield;
				else if (fallNum==2) return fall2P2Shield;
				else return fall3P2Shield;
			}	
		}
		else
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
    //spread 3 fire rectangles randomly on the floor
//    public void ability1(Graphics g) 
//    {
//      	Graphics2D g2 = (Graphics2D) g;
//      	for (int i = 1; i<=3; i++)
//      		g2.drawImage(new ImageIcon("fireSpikes.png").getImage(),(int)(Math.random()*1000)+1,500,this);
//      		
//    }
}