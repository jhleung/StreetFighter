
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *the main class where everything is drawn in the game
 *repaints the scene according to actions (key pressed or key released in DemoScene)
 *allows DemoScene access to character1 and character2's coordinates so that they can be changed in order to repaint
 */
public class OuterScene extends JPanel implements ActionListener
{
	private HitpointsBar hp;
	private ActionScene as;
	public static int hpx,hpy,hpx2,hp1width,hp2width,hpRedWidth1,hpRedx1,hpRedx2,hpRedWidth2;
	public static int char1x,char1y,char2x,char2y;
	public static int abilityNum1,abilityNum2;
	public static Image char1;
	public static Image char2;
	private Image background;
	public static ElementalCharacter elementalCharacter1, elementalCharacter2;
	private Timer timer;
	public static int charges1P1,charges2P1,charges3P1,charges1P2,charges2P2,charges3P2;
	public static boolean shootIt,shootIt2,healIt, iceIt, snowIt;
	public static int blast1X,blast2X,tornadoX,tornadoX2,snow1x,snow2x;
	public static int fireFloorX1,fireFloorX2,fireFloorX3;
	public boolean tornado,tornado2, lightning,wind, wind2;
	public static Image explosion,firework;
	public static int playerLightning,playerExplosion,playerWind,playerTornado,playerSnow,playerHeal;
	private int roundNum;
	/**
	 *Assigns values to all instance fields, default or given
	 *@param a the ActionScene
	 *@param xHp the xcoordinate of the hp bar
	 *@param yHp the ycoordinate of the hp bar
	 *@param c1x the x coordinate of character 1
	 *@param c1y the y coordinate of character1
	 *@param c2x the x coordinate of character 2
	 *@param c2y the y coordinate o character 2
	 *@param c1 the image of character 1
	 *@param c2 the image of character 2
	 *@param bg the backgrund image of the stage
	 */
	public OuterScene(ActionScene a, int xhp,int yhp,int c1x,int c1y,int c2x,int c2y,Image c1,Image c2,Image bg,int roundNum)
	{
		background = bg;
		
		hp = new HitpointsBar();
		
		as = a;
		
		hpx = xhp;
		hpy = yhp;
		hpx2=hpx+520;
		hp2width = 500;
		hp1width = 500;
		hpRedWidth1 = 0;
		hpRedWidth2 = hpx2-(hp2width+hpx);
		
		char1x = c1x;
		char1y = c1y;
		char2x = c2x;
		char2y = c2y;
		
		char1 = c1; 
		char2 = c2;
		
		elementalCharacter1 = as.eleChar1;
		elementalCharacter2 = as.eleChar2;
		
		abilityNum1 = 0;
		abilityNum2 = 0;
		
		charges1P1 = 0;
		charges2P1 = 0;
		charges3P1 = 0;
		charges1P2 = 0;
		charges2P2 = 0;
		charges3P2 = 0;
		
		shootIt = shootIt2 = false;
		blast1X = 0;
		blast2X = 0;
		
		fireFloorX1 = 0;
		fireFloorX2 = 0;
		fireFloorX3 = 0;
		
		tornado = tornado2 = false;
		tornadoX =tornadoX2= 0;
		
		explosion = new ImageIcon("nothing.png").getImage();
		firework = new ImageIcon("nothing.png").getImage();
		
		lightning = false;
		wind = wind2 = false;
		playerLightning = playerExplosion =playerHeal= playerTornado = playerSnow = playerWind = 0;
		
		healIt = false;
		iceIt = false;
		snowIt = false;
		
		snow1x = 0;
		snow2x = 0;
		
		roundNum = 0;
	}
	/**
	 *Repaints the scene based on actions
	 */
	public void paintComponent(Graphics g)
 	{  	 	
 		hpRedx2 = (hpx2+500)-(500-hp2width); 		
 		hpRedWidth2 = 500-hp2width; 
 		hpRedx1 = 35;
 		drawBackground(g);
 		drawExplosion(g,explosion);	
 		drawFirework(g,firework);
 		drawCharacter1(g,char1x,char1y,char1);
 		drawCharacter2(g,char2x,char2y,char2);
 		drawHP(g,hpx,hpy,hp1width,Color.GREEN);
 		drawHP(g,hpx2,hpy,hp2width,Color.GREEN);
 		drawHP(g,hpRedx2,hpy,hpRedWidth2,Color.RED);
 		drawHP(g,hpRedx1,hpy,hpRedWidth1,Color.RED);
 		drawChargesIcon(g,charges1P1,1,1);
 		drawChargesIcon(g,charges2P1,2,1);	
 		drawChargesIcon(g,charges3P1,3,1);
 		drawChargesIcon(g,charges1P2,1,2);
 		drawChargesIcon(g,charges2P2,2,2);	
 		drawChargesIcon(g,charges3P2,3,2);
 		drawBlastSpecial1(g,shootIt);
 		drawBlastSpecial2(g,shootIt2);
 		drawTornado(g,tornado);
 		drawTornado(g,tornado2);
 		drawLightning(g,lightning);
 		drawWind(g,wind);
 		drawWind(g,wind2);
 		drawHeal1(g,healIt);
 		drawIce1(g,iceIt);
 		drawSnow1(g,snowIt);
 	
  	}
  	/**
  	 *Draws the heal image
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawHeal1(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			if (playerHeal==1)
  				g.drawImage(elementalCharacter1.getHeal(),char1x + 15,350,this);
  			else
  				g.drawImage(elementalCharacter1.getHeal(),char2x + 15,350,this);
  		}	
  	}
  	/**
  	 *Draws the ice image
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawIce1(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			g.drawImage(elementalCharacter1.getIce(),char1x,400,this);
  		}	
  	}
  	/**
  	 *Draws the snow image
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawSnow1(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			if (playerSnow==1)
  				g.drawImage(elementalCharacter1.getSnow(),snow1x+char1x,400,this);
  			else
  				g.drawImage(elementalCharacter2.getSnow(),char2x+snow2x,400,this);
  		}	
  	}
  	/**
  	 *Draws the wind image according to player 1 or player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawWind(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			if (playerWind==1)
  				g.drawImage(new ImageIcon(this.getClass().getResource("Wind.png")).getImage(),800,300,this);
  			else
  				g.drawImage(new ImageIcon(this.getClass().getResource("Wind2.png")).getImage(),0,300,this);
  		}
  			
  	}
  	/**
  	 *Draws the lightning image according to player 1 or player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawLightning(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			if (playerLightning==1)
  				g.drawImage(new ImageIcon(this.getClass().getResource("Lightning.png")).getImage(),char1x+10,char1y+10,this);
  			else
  				g.drawImage(new ImageIcon(this.getClass().getResource("Lightning2.png")).getImage(),char2x-320,char2y+10,this);
  		}
  		
  	}
  	/**
  	 *Draws the explosion image according to player 1 or player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawExplosion(Graphics g, Image e)
  	{
  		if (playerExplosion==1)
  			g.drawImage(explosion,char1x-60,char1y+50,this);
  		else
  			g.drawImage(explosion,char2x-60,char2y+50,this);
  	}
  	/**
  	 *Draws the firework image according to player 1 or player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawFirework(Graphics g, Image e)
  	{
  		if (playerExplosion==1)
  			g.drawImage(firework,char1x-60,char1y,this);
  		else
  			g.drawImage(firework,char2x-60,char2y,this);	
  	}
  	/**
  	 *Draws the tornado image according to player 1 or player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawTornado(Graphics g, boolean b)
  	{
  		if (b)
  		{	
  			if (playerTornado == 1)
  				g.drawImage(new ImageIcon(this.getClass().getResource("Tornado.png")).getImage(),char1x+tornadoX,200,this);
  			else
  				g.drawImage(new ImageIcon(this.getClass().getResource("Tornado.png")).getImage(),char2x-tornadoX2,200,this);
  		}  		
  	}
  	/**
  	 *Draws the blast image according to player 1
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawBlastSpecial1(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			g.drawImage(elementalCharacter1.getBlast(),blast1X+char1x,400,this);
  		}	
  	}
  	/**
  	 *Draws the blast image according to player 2
  	 *@param graphics the graphics to use
  	 *@param b whether the image should be drawn
  	 */
  	public void drawBlastSpecial2(Graphics g, boolean b)
  	{
  		if (b)
  		{
  			g.drawImage(elementalCharacter2.getBlast2(),char2x+blast2X+20,400,this);
  		}	
  	}
  	/**
  	 *Draws the charges icons for both players
  	 *@param graphics the graphics to use
  	 *@param charges the number of charges
  	 *@param abilityNum the ability number
  	 *@param player player1 or player2
  	 */
  	public void drawChargesIcon(Graphics g, int charges,int abilityNum, int player)
  	{
  		if (player==1)
  		{
  			if (abilityNum==1)
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),35,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),35,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),35,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),35,100,this);
  			}
  			else if (abilityNum==2)
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),75,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),75,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),75,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),75,100,this);
  			}
  			else 
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),115,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),115,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),115,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),115,100,this);
  			}
  			
  		}
  		else
  		{
  			if (abilityNum==1)
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),920,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),920,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),920,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),920,100,this);
  			}
  			else if (abilityNum==2)
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),960,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),960,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),960,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),960,100,this);
  			}
  			else 
  			{
  				if (charges==0)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon0.png")).getImage(),1000,100,this);
  				else if (charges==1)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon1.png")).getImage(),1000,100,this);
  				else if (charges==2)
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon2.png")).getImage(),1000,100,this);
  				else 
  					g.drawImage(new ImageIcon(this.getClass().getResource("CooldownIcon3.png")).getImage(),1000,100,this);
  			}
  		}
  		
  	}
  	/**
  	 *Draws the background image
  	 *@param graphics the graphics to use
  	 */
  	public void drawBackground(Graphics g)
  	{
  		as.drawBackground(g,0,0,background);
  	}
  	/**
  	 *Draws the character image according to player 1
  	 *@param graphics the graphics to use
  	 *@param x the x coordinate
  	 *@param y the y coordinate
  	 *@param character the image of the character to be drawn
  	 */  	 
  	public void drawCharacter1(Graphics g, int x, int y, Image character)
  	{
  		elementalCharacter1.drawCharacter(g,x,y,character);  		
  	}
  	/**
  	 *Draws the character image according to player 2
  	 *@param graphics the graphics to use
  	 *@param x the x coordinate
  	 *@param y the y coordinate
  	 *@param character the image of the character to be drawn
  	 */ 
  	public void drawCharacter2(Graphics g, int x, int y, Image character)
  	{
  		elementalCharacter2.drawCharacter(g,x,y,character);
  	}
  	/**
  	 *Draws the hipoints bars a
  	 *@param graphics the graphics to use
  	 *@param x the x coordinate
  	 *@param y the y coordinate
  	 *@param width the width of the hp bar
  	 *@param c the color of the hp bar
  	 */ 
  	public void drawHP(Graphics g,int x ,int y,int width,Color c)
  	{
  		hp.drawRect(g,x,y,width,c);
  	}
  	/**
  	 *doesnt do much 
  	 *@param e action event
  	 */
  	public void actionPerformed(ActionEvent e)
	{
	}
}