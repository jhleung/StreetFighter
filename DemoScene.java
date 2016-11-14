
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *Where the game functions mostly occur
 */
public class DemoScene extends JFrame implements ActionListener, KeyListener 
{
	private int earthDamage1, earthDamage2, earthDamageReduced1, earthDamageReduced2;
	private int damageTaken1, damageTaken2;
	private boolean earthDefense1, earthDefense2;
	
	private OuterScene os;
	private ActionScene as;
	private int x1, y1,x2,y2;
	private Image p1Char,p2Char; 
	private boolean [] disabledMousePress1, disabledMousePress2; //makes it so that abilities and jumps,ducks, etc wont be use infinitely if key is held down
	private boolean [] disabledMouseRelease1, disabledMouseRelease2;
	private boolean punchChanger, punchChanger2;
	private ElementalCharacter elementalCharacter1, elementalCharacter2;
	private int walkBackCount, walkForwardCount, walkBackCountPlayer2, walkForwardCountPlayer2;
	private Timer showRound,jumpTimer1,jumpTimer2,abilityTimer1,abilityTimer2,abilityTimer3,abilityTimer1P2,abilityTimer2P2,abilityTimer3P2;
	private int yPos, xPos; 
	private Timer determineWinner;
		
	private ArrayList<Timer> abilityCooldowns;	
	private Map <String, Integer> abilities;
	Queue<Integer> rounds;
	private int roundNum;
	private boolean canWalk,canWalk2;
	
	private String p1Name,p2Name;
	private Player1 player1;
	private Player2 player2;
	
	/**
	 *Gives default values to all instance fields
	 *@param p1 player1
	 *@param p2 player2
	 */
	public DemoScene(Player1 p1,Player2 p2,int round)
	{
		player1 = p1;
		player2 = p2;
		
		earthDamage1 = 3;
		earthDamage2 = 3;
		earthDamageReduced1 = 0;
		earthDamageReduced2 = 0;
		earthDefense1 = false;
		earthDefense2 = false;
		
		damageTaken1 = 0;
		damageTaken2 = 0;
		
		setTitle("Playing...");
		setSize(1100,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		punchChanger = false;
		punchChanger2 = false;
		p1Name = p1.getName();
		
		if (p1Name.equals("Air"))
		{
			p1Char = new ImageIcon(this.getClass().getResource("Default_Air.png")).getImage();
			elementalCharacter1 = new Air(x1,y1,p1Char,elementalCharacter2,true);
		}				
		else if (p1Name.equals("Water"))
		{
			p1Char = new ImageIcon(this.getClass().getResource("Default_Water.png")).getImage();
			elementalCharacter1 = new Water(x1,y1,p1Char,elementalCharacter2,true);
		}
		else if (p1Name.equals("Earth"))
		{
			p1Char = new ImageIcon(this.getClass().getResource("Default_Earth.png")).getImage();
			elementalCharacter1 = new Earth(x1,y1,p1Char,elementalCharacter2,true);
		}
		else 
		{
			p1Char = new ImageIcon(this.getClass().getResource("Default_Fire.png")).getImage();
			elementalCharacter1 = new Fire(x1,y1,p1Char,elementalCharacter2,true);
		}
			
			
		p2Name = p2.getName();
		
		if (p2Name.equals("Air"))
		{
			elementalCharacter2 = new Air(x1,y1,p1Char,elementalCharacter1,true);
			p2Char = new ImageIcon(this.getClass().getResource("Default_Air_Player2.png")).getImage();
		}
		else if (p2Name.equals("Water"))
		{
			p2Char = new ImageIcon(this.getClass().getResource("Default_Water_Player2.png")).getImage();
			elementalCharacter2 = new Water(x1,y1,p1Char,elementalCharacter1,true);
		}		
		else if (p2Name.equals("Earth"))
		{
			p2Char = new ImageIcon(this.getClass().getResource("Default_Earth_Player2.png")).getImage();
			elementalCharacter2 = new Earth(x1,y1,p1Char,elementalCharacter1,true);
		}
		else 
		{
			p2Char = new ImageIcon(this.getClass().getResource("Default_Fire_Player2.png")).getImage();
			elementalCharacter2 = new Fire(x1,y1,p1Char,elementalCharacter1,true);
		}
			
		
		x1 = 200;
		y1 = y2 = 300;
		x2 = 600;		
			
		walkBackCount = 0;
		walkBackCountPlayer2 = 0;
		walkForwardCount = 0;
		walkForwardCountPlayer2 = 0;
		
		elementalCharacter1.setPlayer2(elementalCharacter2);
		elementalCharacter2.setPlayer2(elementalCharacter1);
		as = new ActionScene(elementalCharacter1,elementalCharacter2);
		





		5 =  new OuterScene(as,35,50,x1,y1,x2,y2,p1Char,p2Char,new ImageIcon(this.getClass().getResource("stage1.0.png")).getImage(),round);
		add(os);		
	
		
		addKeyListener(this);
		setVisible(false);
		
		disabledMousePress1 = new boolean[9]; //jump,duck,left,right,punch,kick,ability1,ability2,ability3
											 //0,    1,    2,   3,    4,   5,     6,       7      ,8
		disabledMousePress2 = new boolean[9];
		
		for (int i = 0; i<9; i++)
		{
			disabledMousePress1[i]=false;
			disabledMousePress2[i]=false;
			
		}
		disabledMouseRelease1 = new boolean[9];
		disabledMouseRelease2 = new boolean[9];
		for (int i = 0; i<9; i++)
		{
			disabledMouseRelease1[i]=true;
			disabledMouseRelease2[i]=true;
			
		}
		yPos = os.char1y; 
		xPos = os.char1x;
		
		abilities = new HashMap<String, Integer>();
		abilities.put("Air1",0);
		abilities.put("Air2",0);
		abilities.put("Air3",0);
			
		abilities.put("Water1",0);
		abilities.put("Water2",0);			
		abilities.put("Water3",0);
			
		abilities.put("Earth1",0);
		abilities.put("Earth2",0);
		abilities.put("Earth3",0);
			
		abilities.put("Fire1",0);
		abilities.put("Fire2",0);
		abilities.put("Fire3",0);
		
		abilities.put("Air1P2",0);
		abilities.put("Air2P2",0);
		abilities.put("Air3P2",0);
			
		abilities.put("Water1P2",0);
		abilities.put("Water2P2",0);			
		abilities.put("Water3P2",0);
			
		abilities.put("Earth1P2",0);
		abilities.put("Earth2P2",0);
		abilities.put("Earth3P2",0);
			
		abilities.put("Fire1P2",0);
		abilities.put("Fire2P2",0);
		abilities.put("Fire3P2",0);
		
		abilityCooldowns = new ArrayList<Timer>();
		initTimers();
		determineWinner = new Timer(50,this);
		determineWinner.start();	
			
		roundNum = round;
		
		showRound = new Timer(100,this);
		showRound.start();
	}
	/**
	 *Initializes all the cooldown timers (time until next charge for an ability)
	 */
	public void initTimers()
	{
		abilityCooldowns.add(new Timer(20000,this)); //air
		abilityCooldowns.add(new Timer(20000,this)); 
		abilityCooldowns.add(new Timer(10000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //water
		abilityCooldowns.add(new Timer(10000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //earth
		abilityCooldowns.add(new Timer(25000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //fire 
		abilityCooldowns.add(new Timer(10000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //air player 2
		abilityCooldowns.add(new Timer(20000,this)); 
		abilityCooldowns.add(new Timer(10000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //water player 2
		abilityCooldowns.add(new Timer(20000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //earth player 2
		abilityCooldowns.add(new Timer(25000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		abilityCooldowns.add(new Timer(20000,this)); //fire player 2
		abilityCooldowns.add(new Timer(10000,this));
		abilityCooldowns.add(new Timer(15000,this));
		
		if(p1Name.equals("Air"))
		{
			for (int i = 0; i<=2; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p1Name.equals("Water"))
		{
			for (int i = 3; i<=5; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p1Name.equals("Earth"))
		{
			for (int i = 6; i<=8; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p1Name.equals("Fire"))
		{
			for (int i = 9; i<=11; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		
		if(p2Name.equals("Air"))
		{
			for (int i = 12; i<=14; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p2Name.equals("Water"))
		{
			for (int i = 15; i<=17; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p2Name.equals("Earth"))
		{
			for (int i = 18; i<=20; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		else if(p2Name.equals("Fire"))
		{
			for (int i = 21; i<=23; i++)
			{
				abilityCooldowns.get(i).start();
			}
		}
		
		canWalk = canWalk2 = true;
		rounds = new LinkedList<Integer>();
		rounds.add(roundNum+1);
	}	
	/**
	 *Responds when cooldown timers are fired and adds a charge to the ability by incrementing the value of a certain key in the abilities map
	 *@param e the action
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()==determineWinner)
		{
			if (os.hpRedWidth1>=500)
			{
				int tempRound = rounds.remove();
				JOptionPane.showMessageDialog(this,"Player 2 Wins!");	
				if (tempRound<3)
				{
					setVisible(false);					
					new DemoScene(player1,player2,tempRound).setVisible(true);					
					JOptionPane.showMessageDialog(null,"End of Round" + tempRound);			
				}
				else
					new StreetFighterMenu().setVisible(true);			
			}			
			else if (os.hp2width<=0)
			{
				int tempRound = rounds.remove();
				JOptionPane.showMessageDialog(this,"Player 1 Wins!");			
				if (tempRound<3)
				{
					setVisible(false);
					new DemoScene(player1,player2,tempRound).setVisible(true);
					JOptionPane.showMessageDialog(null,"End of Round" + tempRound);				
				}
				else
					new StreetFighterMenu().setVisible(true);
				}
		
		}
		
		if (e.getSource() == abilityCooldowns.get(0) && abilities.get("Air1")<3)
		{
			os.charges1P1++;
			abilities.put("Air1",abilities.get("Air1")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(1) && abilities.get("Air2")<3)
		{
			os.charges2P1++;
			abilities.put("Air2",abilities.get("Air2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(2) && abilities.get("Air3")<3)
		{
			os.charges3P1++;
			abilities.put("Air3",abilities.get("Air3")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(3) && abilities.get("Water1")<1)
		{
			os.charges1P1++;
			abilities.put("Water1",abilities.get("Water1")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(4) && abilities.get("Water2")<1)
		{
			os.charges2P1++;
			abilities.put("Water2",abilities.get("Water2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(5) && abilities.get("Water3")<3)
		{
			os.charges3P1++;
			abilities.put("Water3",abilities.get("Water3")+1);	
		}		
		else if (e.getSource() == abilityCooldowns.get(6) && abilities.get("Earth1")<3)
		{
			os.charges1P1++;
			abilities.put("Earth1",abilities.get("Earth1")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(7) && abilities.get("Earth2")<3)
		{
			os.charges2P1++;
			abilities.put("Earth2",abilities.get("Earth2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(8) && abilities.get("Earth3")<3)
		{
			os.charges3P1++;
			abilities.put("Earth3",abilities.get("Earth3")+1);
		}	
		else if (e.getSource() == abilityCooldowns.get(9) && abilities.get("Fire1")<3)
		{
			os.charges1P1++;
			abilities.put("Fire1",abilities.get("Fire1")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(10) && abilities.get("Fire2")<3)
		{
			os.charges2P1++;
			abilities.put("Fire2",abilities.get("Fire2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(11) && abilities.get("Fire3")<3)
		{
			os.charges3P1++;
			abilities.put("Fire3",abilities.get("Fire3")+1);
		}			
		os.repaint();	
		
		if (e.getSource() == abilityCooldowns.get(12) && abilities.get("Air1P2")<3)
		{
			os.charges1P2++;
			abilities.put("Air1P2",abilities.get("Air1P2")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(13) && abilities.get("Air2P2")<3)
		{
			os.charges2P2++;
			abilities.put("Air2P2",abilities.get("Air2P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(14) && abilities.get("Air3P2")<3)
		{
			os.charges3P2++;
			abilities.put("Air3P2",abilities.get("Air3P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(15) && abilities.get("Water1P2")<1)
		{
			os.charges1P2++;
			abilities.put("Water1P2",abilities.get("Water1P2")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(16) && abilities.get("Water2P2")<1)
		{
			os.charges2P2++;
			abilities.put("Water2P2",abilities.get("Water2P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(17) && abilities.get("Water3P2")<3)
		{
			os.charges3P2++;
			abilities.put("Water3P2",abilities.get("Water3P2")+1);	
		}		
		else if (e.getSource() == abilityCooldowns.get(18) && abilities.get("Earth1P2")<3)
		{
			os.charges1P2++;
			abilities.put("Earth1P2",abilities.get("Earth1P2")+1);
		}		
		else if (e.getSource() == abilityCooldowns.get(19) && abilities.get("Earth2P2")<3)
		{
			os.charges2P2++;
			abilities.put("Earth2P2",abilities.get("Earth2P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(20) && abilities.get("Earth3P2")<3)
		{
			os.charges3P2++;
			abilities.put("Earth3P2",abilities.get("Earth3P2")+1);
		}	
		else if (e.getSource() == abilityCooldowns.get(21) && abilities.get("Fire1P2")<3)
		{
			os.charges1P2++;
			abilities.put("Fire1P2",abilities.get("Fire1P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(22) && abilities.get("Fire2P2")<3)
		{
			os.charges2P2++;
			abilities.put("Fire2P2",abilities.get("Fire2P2")+1);
		}			
		else if (e.getSource() == abilityCooldowns.get(23) && abilities.get("Fire3P2")<3)
		{
			os.charges3P2++;
			abilities.put("Fire3P2",abilities.get("Fire3P2")+1);
		}			
		os.repaint();	
		System.out.println(abilities.get("Fire3")+"ok");
	}
	/**
	 *Starts timers for abilities and animates basic moves 
	 *@param e the key event
	 */
	public void keyPressed(KeyEvent e)
	{
		Graphics g = getGraphics();
		//NEED TO ADD WALKING ANIMATION (KEYS A AND D) AND PUNCH (KEY G) AND ABILITIES (KEYS TYU)
		//access char1,char1y,char1x, and abilityNum1 of outerscene class to animate stuff
		
		if(e.getKeyCode() == KeyEvent.VK_W) // JUMP : PLAYER 1
		{
			if (os.char1y==300)
			{
				jumpTimer1 = new Timer(50, new BasicMovesTimer(os,yPos-268,xPos));			
	       		jumpTimer1.start();
	       		System.out.println ("W clicked");	
	       		disabledMousePress1[0]=true;
			}							
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) // LEFT : PLAYER 1
		{
			if (canWalk)
			{
				if(os.char1x >= 20)
				{
					if (walkBackCount==0)
					{
						os.char1=elementalCharacter1.getWalkB(1,earthDefense1);
						os.char1x-=40;
						os.repaint();
						System.out.println ("a clicked");
						walkBackCount++;	
					}
					else if (walkBackCount==1)
					{
						os.char1=elementalCharacter1.getWalkF(1,earthDefense1);
						os.char1x-=40;
						os.repaint();
						System.out.println ("a2 clicked");
						walkBackCount++;	
					}
					else if (walkBackCount==2)
					{
						os.char1=elementalCharacter1.getDefault(1,earthDefense1);
						os.char1x-=40;
						os.repaint();
						System.out.println ("a3 clicked");
						walkBackCount--;	
					}
				}
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D) // RIGHT : PLAYER 1
		{
			if (canWalk)
			{
				if(os.char1x <= 950)
				{
					if (walkForwardCount==0)
					{
						os.char1=elementalCharacter1.getWalkB(1,earthDefense1);
						os.char1x+=40;
						os.repaint();
						System.out.println ("a1 clicked");
						walkForwardCount++;	
					}
					else if (walkForwardCount==1)
					{
						os.char1=elementalCharacter1.getWalkF(1,earthDefense1);
						os.char1x+=40;
						os.repaint();
						System.out.println ("a2 clicked");
						walkForwardCount++;	
					}
					else 
					{
						os.char1=elementalCharacter1.getDefault(1,earthDefense1);
						os.char1x+=40;
						os.repaint();
						System.out.println ("a3 clicked");
						walkForwardCount--;	
					}
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_T) // ABILITY 1 : PLAYER 1
		{
			abilityTimer1 = new Timer(100, new AbilityTimer(os,yPos,xPos+200,p1Name,1));			
       		abilityTimer1.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_Y) // ABILITY 2 : PLAYER 1
		{
			abilityTimer2 = new Timer(100, new AbilityTimer(os,yPos,xPos+200,p1Name,1));			
       		abilityTimer2.start();	
		}	
		if (e.getKeyCode() == KeyEvent.VK_U)
		{
			abilityTimer3 = new Timer(10, new AbilityTimer(os,yPos,xPos+200,p1Name,1));
			abilityTimer3.start();
		}	
		if(e.getKeyCode() == KeyEvent.VK_H) // KICK : PLAYER 1
		{			
			if (!disabledMousePress1[5])
			{
				os.char1=elementalCharacter1.getKick(1,earthDefense1);
				os.repaint();	
				System.out.println ("kick");
				
				if (os.char1x+120>=os.char2x && os.char1x+120<=os.char2x+80&& os.char1y-10<os.char2y)
				{
					System.out.println("Collision!");
					damageTaken2= earthDamage1 + earthDamageReduced2;
					os.hp2width-=earthDamage1 - earthDamageReduced2;
					os.repaint();	
				}
			
				disabledMousePress1[5] = true;
				disabledMouseRelease1[5] = false;	
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_G) // PUNCH: PLAYER 1
		{
			if (!disabledMousePress1[4])
			{
				if(!punchChanger)
				{
					os.char1=elementalCharacter1.getPunch1(1,earthDefense1);
					os.repaint();
					System.out.println ("punch1");
					if (os.char1x+120>=os.char2x && os.char1x+120<=os.char2x+80 && os.char1y-10<os.char2y)
					{
						System.out.println("Collision! Punch1");
						damageTaken2+=earthDamage1 - earthDamageReduced2;
						os.hp2width-=earthDamage1 - earthDamageReduced2;
						os.repaint();	
					}
					disabledMousePress1[4] = true;
					disabledMouseRelease1[4] = false;
					punchChanger = true;
				}
				else
				{
					os.char1=elementalCharacter1.getPunch2(1,earthDefense1);
					os.repaint();
					System.out.println ("punch2");
					if (os.char1x+120>=os.char2x && os.char1x+120<=os.char2x+80 && os.char1y<os.char2y-10)
					{
						System.out.println("Collision! Punch1");
						damageTaken2+=earthDamage1 - earthDamageReduced2;
						os.hp2width-=earthDamage1 - earthDamageReduced2;
						os.repaint();	
					}
					disabledMousePress1[4] = true;
					disabledMouseRelease1[4] = false;
					punchChanger = false;
				}
					
			}
			
		}
		
		// PLAYER 2 CONTROLS
		
		if(e.getKeyCode() == KeyEvent.VK_UP) // UP : PLAYER 2
		{
			if (os.char2y==300)
			{
				jumpTimer2 = new Timer(50, new BasicMovesTimer(os,yPos-268,xPos));			
	       		jumpTimer2.start();	
	       		System.out.println ("UP clicked");
	       		disabledMousePress2[0]=true;
			}
		
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) // Left : PLAYER 2
		{
			if (canWalk2)
			{
				if(os.char2x >= 20)
				{
					if (walkBackCountPlayer2 == 0)
					{
						os.char2=elementalCharacter2.getWalkB(2,earthDefense2);
						os.char2x-=40;
						os.repaint();
						System.out.println ("Left Arrow clicked");
						walkBackCountPlayer2++;	
					}
					else if (walkBackCountPlayer2 == 1)
					{
						os.char2=elementalCharacter2.getWalkF(2,earthDefense2);
						os.char2x-=40;
						os.repaint();
						System.out.println ("Left Arrow2 clicked");
						walkBackCountPlayer2++;	
					}
					else if (walkBackCountPlayer2 == 2)
					{
						os.char2=elementalCharacter2.getDefault(2,earthDefense2);
						os.char2x-=40;
						os.repaint();
						System.out.println ("Done clicked");
						walkBackCountPlayer2--;	
					}
				}
			}
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) // RIGHT : PLAYER 2
		{
			if (canWalk2)
			{
				if(os.char2x <= 950)
				{
					if (walkForwardCount==0)
					{
						os.char2=elementalCharacter2.getWalkB(2,earthDefense2);
						os.char2x+=40;
						os.repaint();
						System.out.println ("a1 clicked");
						walkForwardCount++;	
					}
					else if (walkForwardCount==1)
					{
						os.char2=elementalCharacter2.getWalkF(2,earthDefense2);
						os.char2x+=40;
						os.repaint();
						System.out.println ("a2 clicked");
						walkForwardCount++;	
					}
					else 
					{
						os.char2=elementalCharacter2.getDefault(2,earthDefense2);
						os.char2x+=40;
						os.repaint();
						System.out.println ("a3 clicked");
						walkForwardCount--;	
					}
				}
			}
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD4) // ABILITY 2 : PLAYER 2
		{
			abilityTimer1P2 = new Timer(100, new AbilityTimer(os,yPos,xPos+200,p2Name,1));
			abilityTimer1P2.start();
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD5) // ABILITY 2 : PLAYER 2
		{
			abilityTimer2P2 = new Timer(100, new AbilityTimer(os,yPos,xPos+200,p2Name,1));
			abilityTimer2P2.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) // ABILITY 3 : PLAYER 2
		{
			abilityTimer3P2 = new Timer(100, new AbilityTimer(os,yPos,xPos+200,p2Name,1));			
       		abilityTimer3P2.start();	
		}		
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD3) // KICK : PLAYER 2
		{
			System.out.println("kick Player2");
			if (!disabledMousePress2[5])
			{
				os.char2=elementalCharacter2.getKick(2,earthDefense2);
				os.char2x-=53;
				os.repaint();	
				System.out.println ("kick");
				
				if (os.char2x-120<=os.char1x && os.char2x-120>=os.char1x-80 && os.char2y-10<os.char1y)
				{
					System.out.println("Collision! Kick2");
					damageTaken1+= earthDamage2 - earthDamageReduced1;
					os.hpRedWidth1+=earthDamage2 - earthDamageReduced1;
					os.repaint();	
				}
			
				disabledMousePress2[5] = true;
				disabledMouseRelease2[5] = false;	
			}
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD2) // PUNCH: PLAYER 2
		{
			if (!disabledMousePress2[4])
			{
				if(!punchChanger2)
				{
					os.char2=elementalCharacter2.getPunch1(2,earthDefense2);
					os.char2x-=30;
					os.repaint();
					System.out.println ("punch1");
					if (os.char2x-120<=os.char1x && os.char2x-120>=os.char1x-80 && os.char2y-10<os.char1y)
					{
						System.out.println("Collision! Kick2");
						damageTaken1+=earthDamage2 - earthDamageReduced1;
						os.hpRedWidth1+=earthDamage2 - earthDamageReduced1;
						os.repaint();	
					}
					disabledMousePress2[4] = true;
					disabledMouseRelease2[4] = false;
					punchChanger2 = true;
				}
				else
				{
					os.char2=elementalCharacter2.getPunch2(2,earthDefense2);
					os.char2x-=30;
					os.repaint();
					System.out.println ("punch2");
					if (os.char2x-120<=os.char1x && os.char2x-120>=os.char1x-80 && os.char2y-10<os.char1y)
					{
						System.out.println("Collision! Kick2");
						damageTaken1+= earthDamage2 - earthDamageReduced1;
						os.hpRedWidth1+=earthDamage2 - earthDamageReduced1;
						os.repaint();	
					}
					disabledMousePress2[4] = true;
					disabledMouseRelease2[4] = false;
					punchChanger2 = false;
				}
					
			}
			
		}
	}
	/**
	 *Sets characters back to normal if necessary and enables keys to be pressed if they were disabled somewhere when a key is released
	 *@param e the key event
	 */
	public void keyReleased(KeyEvent e)
	{
		// PLAYER 1 CONTROLS
		
		if(e.getKeyCode() == KeyEvent.VK_W) // JUMP : PLAYER 1
		{
			disabledMouseRelease1[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) // LEFT: PLAYER 1 
		{
			if (canWalk)
			{
				os.char1=elementalCharacter1.getDefault(1,earthDefense1);
				os.repaint();
			}
		
		}
		if(e.getKeyCode() == KeyEvent.VK_D) // RIGHT : PLAYER 1 
		{
			if (canWalk)
			{
				os.char1=elementalCharacter1.getDefault(1,earthDefense1);
				os.repaint();
			}
				
		}
		if(e.getKeyCode() == KeyEvent.VK_T) // ABILITY 1
		{
			if (!disabledMouseRelease1[6])
			{
				os.abilityNum1=0;
				os.repaint();
				disabledMousePress1[6] = false;
				disabledMouseRelease1[6] = true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_Y) // ABILITY 2 : PLAYER 1
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_H) // KICK : PLAYER 1
		{
			if (!disabledMouseRelease1[5])
			{
				os.char1=elementalCharacter1.getDefault(1,earthDefense1);
				os.repaint();
				disabledMousePress1[5] = false;
				disabledMouseRelease1[5] = true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_G) // PUNCH : PLAYER 1
		{
			if (!disabledMouseRelease1[4])
			{
				os.char1=elementalCharacter1.getDefault(1,earthDefense1);
				os.repaint();
				disabledMousePress1[4] = false;
				disabledMouseRelease1[4] = true;
			}
			
			
		}
		
		
		// PLAYER 2 CONTROLS
		
		if(e.getKeyCode() == KeyEvent.VK_UP) // JUMP : PLAYER 2
		{
//			if (disabledMouseRelease2[0])
//			{
				disabledMouseRelease2[0] = false;
//			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) // LEFT: PLAYER 2
		{
			if (canWalk2)
			{
				os.char2=elementalCharacter2.getDefault(2,earthDefense2);
				os.repaint();	
			}
		
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) // RIGHT : PLAYER 2
		{
			if (canWalk2)
			{
				os.char2=elementalCharacter2.getDefault(2,earthDefense2);
				os.repaint();	
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD5) // ABILITY 1 : PLAYER 2
		{
			if (!disabledMouseRelease2[6])
			{
				os.abilityNum1=0;
				os.repaint();
				disabledMousePress2[6] = false;
				disabledMouseRelease2[6] = true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD6) // ABILITY 2 : PLAYER 2
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD3) // KICK : PLAYER 2
		{
			if (!disabledMouseRelease2[5])
			{
				os.char2=elementalCharacter2.getDefault(2,earthDefense2);
				os.char2x+=53;
				os.repaint();
				disabledMousePress2[5] = false;
				disabledMouseRelease2[5] = true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD2) // PUNCH : PLAYER 2
		{
			if (!disabledMouseRelease2[4])
			{
				os.char2=elementalCharacter2.getDefault(2,earthDefense2);
				os.char2x+=30;
				os.repaint();
				disabledMousePress2[4] = false;
				disabledMouseRelease2[4] = true;
			}
			
		}
	}
	/**
	 *Does nothing in this class
	 *@param e the key event
	 */
	public void keyTyped(KeyEvent e)
	{
		
	}
	/**
	 *Animates character jumps
	 */
	class BasicMovesTimer implements ActionListener
	 {
		private OuterScene os;
		private int count,yPos,xPos;
		private float velocityX,velocityY,gravity;
		/**
		 *Gives values to instancefields, sets count to 0
		 *@param o the outerscene where characters are drawn
		 *@param yP the y position
		 *@param xP the x position
		 */
		public BasicMovesTimer(OuterScene o,int yP,int xP)
		{
			count = 0;
			os = o;
			yPos = yP;	
			xPos = xP;		
		}
		/**
		 *Invoked when a basic moves timer is started and animates jumps of player1 and player2
		 *@param e the action event
		 */
        public void actionPerformed(ActionEvent e) 
        {    	
        	if (e.getSource()==jumpTimer1)
    		{
    			gravity = 4.0f;	
    			velocityY+=gravity;
        		yPos += velocityY;
        		os.char1=elementalCharacter1.getJump(1,earthDefense1); //new ImageIcon("Jump_Fire.png").getImage();
        		os.char1y=yPos;
        		
				if (yPos>=300)
				{
					jumpTimer1.stop();
					os.char1=elementalCharacter1.getDefault(1,earthDefense1);//new ImageIcon("Default_Fire.png").getImage();
					os.char1y=300;				
				}
				System.out.println ("Jump Player 1 Action");									
    		}
    		if (e.getSource()==jumpTimer2)
    		{
    			gravity = 4.0f;	
    			velocityY+=gravity;
        		yPos += velocityY;
        		os.char2=elementalCharacter2.getJump(2,earthDefense2); //new ImageIcon("Jump_Fire_Player2.png").getImage();
        		os.char2y=yPos;
        		
				if (yPos>=300)
				{
					jumpTimer2.stop();
					os.char2=elementalCharacter2.getDefault(2,earthDefense2);//new ImageIcon("Default_Fire_Player2.png").getImage();
					os.char2y=300;			
				}
				System.out.println ("Jump Player 2 Action");									
    		}
    		
			os.repaint();     
        }
	}
	/**
	 *Allows animation of abilities
	 */
	class AbilityTimer implements ActionListener
	{
		private OuterScene os;
		private int count,yPos,xPos;
		private float velocityX,velocityY,gravity;
		private String element;
		private int abilityNum;
		private boolean hit;
		/**
		 *gives default values, sets count to 0
		 *@param o the outerscene where characters are drawn
		 *@param yP the yPos
		 *@param xP the xPos
		 *@param a the element name
		 *@param ab the ability number
		 */
		public AbilityTimer(OuterScene o,int yP,int xP,String a,int ab)
		{
			count = 0;
			os = o;
			yPos = yP;	
			xPos = xP;		
			element = a;
			abilityNum = ab;
			hit = false;
		}		
		/**
		 *Invoked when an ability timer is started, animates abilities
		 *@param e the action event
		 */
		public void actionPerformed(ActionEvent e) 
		{		
			if (element.equals("Air"))
			{
				System.out.println("Air");
				if (e.getSource()==abilityTimer1)
				{
					if (abilities.get("Air1")>0)
					{
						os.playerWind=1;
						os.char2x-=120;
						canWalk2=false;
						os.wind=true;
						os.repaint();
						if (os.char2x<=os.char1x+140)
						{
							abilityTimer1.stop();
							canWalk2=true;
							os.wind=false;
							os.charges1P1--;
							os.hp2width-=50;
							os.repaint();
							abilities.put("Air1",abilities.get("Air1")-1);
						}
					}
					else abilityTimer1.stop();
				}
				else if (e.getSource()==abilityTimer1P2)
				{
					if (abilities.get("Air1P2")>0)
					{
						os.playerWind=2;
						os.char1x+=120;
						canWalk=false;
						os.wind2=true;
						os.repaint();
						if (os.char1x+170>=os.char2x)
						{
							abilityTimer1P2.stop();
							canWalk=true;
							os.wind2=false;
							os.charges1P2--;
							os.hpRedWidth1+=50;
							os.repaint();
							abilities.put("Air1P2",abilities.get("Air1P2")-1);
						}
					}
					else abilityTimer1P2.stop();
				}
				else if (e.getSource()==abilityTimer2) // tells us if he is player1 and what ability number
				{
					if (abilities.get("Air2")>0)
					{
						if (count==0)
		        		{
		        			os.playerTornado = 1;
		        			os.char2=elementalCharacter2.getFall(2,1,earthDefense2);	  
		        			os.tornado=true; 						
		        		}
		        		else if (count==1)
		        		{
		        			os.char2=elementalCharacter2.getFall(2,2,earthDefense2);
	            			if (os.char2x+100<850)
							os.char2x+=200;
							os.char2y+=50;	
		        		}	        			
		        		else if (count==2)
		        		{
		        			os.char2=elementalCharacter2.getFall(2,3,earthDefense2);
		        			if (os.char2x+100<850)
								os.char2x+=200;
							os.char2y+=100;
		        		
		        		}
		        		
						os.tornadoX+=100;
						os.repaint();
		        		count++;				
						if (count==10)
						{
							abilityTimer2.stop();
							os.charges2P1--;
							count = 0;
							os.tornadoX=0;
							os.tornado=false;
							os.char2y=300;
							os.char2=elementalCharacter2.getDefault(2,earthDefense2);	
							os.hp2width-=50;
							os.repaint();						
							abilities.put("Air2",abilities.get("Air2")-1);	  //removes 1 charge from map  						
						}		
					}
					else abilityTimer2.stop();									
				}
				else if (e.getSource()==abilityTimer2P2) // tells us if he is player1 and what ability number
				{
					if (abilities.get("Air2P2")>0)
					{
						if (count==0)
		        		{
		        			System.out.println("tornado");
		        			os.playerTornado = 2;
		        			os.char1=elementalCharacter1.getFall(1,1,earthDefense1);	  
		        			os.tornado2=true; 						
		        		}
		        		else if (count==1)
		        		{
		        			os.char1=elementalCharacter1.getFall(1,2,earthDefense1);
	            			if (os.char1x-100>0)
								os.char1x-=200;
							os.char1y+=50;	
		        		}	        			
		        		else if (count==2)
		        		{
		        			os.char1=elementalCharacter1.getFall(1,3,earthDefense1);
		        			if (os.char1x-100>0)
								os.char1x-=200;
							os.char1y+=100;
		        		
		        		}
		        		
						os.tornadoX2+=100;
						os.repaint();
		        		count++;				
						if (count==10)
						{
							abilityTimer2P2.stop();
							os.charges2P2--;
							count = 0;
							os.tornadoX2=0;
							os.tornado2=false;
							os.char1y=300;
							os.char1=elementalCharacter1.getDefault(1,earthDefense1);	
							os.hpRedWidth1+=50;
							os.repaint();						
							abilities.put("Air2P2",abilities.get("Air2P2")-1);	  //removes 1 charge from map  						
						}		
					}
					else abilityTimer2P2.stop();									
				}								
				
				else if (e.getSource()==abilityTimer3)
				{
					if (abilities.get("Air3")>0)
					{
						if (os.blast1X+200>=os.char2x && 400<=os.char2y+100 && os.blast1X+200<=os.char2x+80) //if blast hits opponent hp will decrease
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.hp2width-=40;
							os.repaint();							
							abilities.put("Air3",abilities.get("Air3")-1);
						}
						else if (os.blast1X+200>1100)  //if blast goes out of bounds
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.repaint();
							abilities.put("Air3",abilities.get("Air3")-1);							
						}
						else
						{
							os.shootIt=true;
							os.blast1X+=10;
							os.repaint();	
						}
					
					}
					else abilityTimer3.stop();
					
				}
				else if (e.getSource()==abilityTimer3P2)
				{
					if (abilities.get("Air3P2")>0)
					{
						if ((os.blast2X+os.char2x)-120<=os.char1x && 400<=os.char1y+100 && (os.blast2X+os.char2x)>=os.char1x-80) //if blast hits opponent hp will decrease
						{
							System.out.println("this one");
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.hpRedWidth1+=40;
							os.repaint();							
							abilities.put("Air3P2",abilities.get("Air3P2")-1);
						}
						else if ((os.blast2X+os.char2x)-20<0)  //if blast goes out of bounds
						{
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.repaint();
							abilities.put("Air3P2",abilities.get("Air3P2")-1);							
						}
						else
						{
							os.shootIt2=true;
							os.blast2X-=60;
							os.repaint();	
						}
					
					}
					else abilityTimer3P2.stop();
					
				}
				
			
			}
			else if (element.equals("Fire"))
			{
				System.out.println("Fire");
				if (e.getSource()==abilityTimer1) // tells us if he is player1 and what ability number
				{
					if (abilities.get("Fire1")>0)
					{						
						if (count==0)
						{
							canWalk = false;						
							os.playerExplosion=1;
							os.explosion = new ImageIcon(this.getClass().getResource("Explosion0.png")).getImage();
							os.repaint();
						}
						else if (count==10)
						{
							os.explosion = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.char1 = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.firework = new ImageIcon(this.getClass().getResource("Fireworks1.png")).getImage();
							os.repaint();
						}
						else if (count==12)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks2.png")).getImage();
							os.repaint();
						}
						else if (count==14)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks3.png")).getImage();
							os.repaint();
						}
						else if (count==16)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks4.png")).getImage();
							os.repaint();
						}
						count++;
						
						if (count==18)
						{
							count = 0;
							canWalk=true;
							abilityTimer1.stop();
							os.charges1P1--;
							os.firework = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.char1 = elementalCharacter1.getDefault(1,earthDefense1);
							if (os.char2x-(os.char1x+80)<90)
								os.hp2width-=100;
							os.hpRedWidth1+=20;
							os.repaint();
							abilities.put("Fire1",abilities.get("Fire1")-1);	
						}
					
						
					}
					else abilityTimer1.stop();					
				}
				else if (e.getSource()==abilityTimer1P2) // tells us if he is player1 and what ability number
				{
					if (abilities.get("Fire1P2")>0)
					{						
						if (count==0)
						{
							canWalk2 = false;
							os.playerExplosion=2;
							os.explosion = new ImageIcon(this.getClass().getResource("Explosion0.png")).getImage();
							os.repaint();
						}
						else if (count==10)
						{
							os.explosion = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.char2 = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.firework = new ImageIcon(this.getClass().getResource("Fireworks1.png")).getImage();
							os.repaint();
						}
						else if (count==12)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks2.png")).getImage();
							os.repaint();
						}
						else if (count==14)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks3.png")).getImage();
							os.repaint();
						}
						else if (count==16)
						{
							os.firework=new ImageIcon(this.getClass().getResource("Fireworks4.png")).getImage();
							os.repaint();
						}
						count++;
						
						if (count==18)
						{
							count = 0;
							canWalk2=true;
							abilityTimer1P2.stop();
							os.charges1P2--;
							os.firework = new ImageIcon(this.getClass().getResource("nothing.png")).getImage();
							os.char2 = elementalCharacter2.getDefault(2,earthDefense2);
							if ((os.char2x-80)-(os.char1x)<90)
								os.hpRedWidth1+=100;
							os.hp2width-=20;
							os.repaint();
							abilities.put("Fire1P2",abilities.get("Fire1P2")-1);	
						}
					
						
					}
					else abilityTimer1P2.stop();					
				}
				else if (e.getSource()==abilityTimer2)
				{
					if (abilities.get("Fire2")>0)
					{
						if (count==0)
						{
							os.lightning=true;
							os.playerLightning=1;
							os.repaint();
						}
						count++;
						if (count==10)
						{
							count = 0;
							abilityTimer2.stop();
							os.lightning=false;
							if (os.char2x<=(os.char1x+330))
								os.hp2width-=20;
							os.charges2P1--;
							os.repaint();
							abilities.put("Fire2",abilities.get("Fire2")-1);
							
						}
					}
					
					else abilityTimer2.stop();
				}
				else if (e.getSource()==abilityTimer2P2)
				{
					if (abilities.get("Fire2P2")>0)
					{
						System.out.println("check");
						if (count==0)
						{
							os.lightning=true;
							os.playerLightning=2;
							os.repaint();
						}
						count++;
						if (count==10)
						{
							count = 0;
							abilityTimer2P2.stop();
							os.lightning=false;
							if (os.char2x-330<=(os.char1x))
								os.hpRedWidth1+=20;
							os.charges2P2--;
							os.repaint();
							abilities.put("Fire2P2",abilities.get("Fire2P2")-1);
							
						}
					}
					
					else abilityTimer2P2.stop();
				}
				else if (e.getSource()==abilityTimer3)
				{
					if (abilities.get("Fire3")>0)
					{
						if (os.blast1X+200>=os.char2x && 400<=os.char2y+100 && os.blast1X+200<=os.char2x+80) //if blast hits opponent hp will decrease
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.hp2width-=40;
							os.repaint();							
							abilities.put("Fire3",abilities.get("Fire3")-1);
						}
						else if (os.blast1X+200>1100)  //if blast goes out of bounds
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.repaint();
							abilities.put("Fire3",abilities.get("Fire3")-1);							
						}
						else
						{
							os.shootIt=true;
							os.blast1X+=10;
							os.repaint();	
						}
					
					}
					else abilityTimer3.stop();
					
				}
				else if (e.getSource()==abilityTimer3P2)
				{
					if (abilities.get("Fire3P2")>0)
					{
						if ((os.blast2X+os.char2x)-120<=os.char1x && 400<=os.char1y+100 && (os.blast2X+os.char2x)>=os.char1x-80) //if blast hits opponent hp will decrease
						{
							System.out.println("this one");
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.hpRedWidth1+=40;
							os.repaint();							
							abilities.put("Fire3P2",abilities.get("Fire3P2")-1);
						}
						else if ((os.blast2X+os.char2x)-20<0)  //if blast goes out of bounds
						{
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.repaint();
							abilities.put("Fire3P2",abilities.get("Fire3P2")-1);							
						}
						else
						{
							os.shootIt2=true;
							os.blast2X-=60;
							os.repaint();	
						}
					
					}
					else abilityTimer3P2.stop();
					
				}
			}
			else if (element.equals("Earth"))
			{
					System.out.println("Earth");
				if (e.getSource()==abilityTimer1) // ENHANCED AUTO'S : Ability 1 : PLAYER 1 
				{
					if(abilities.get("Earth1")>0)
					{
						count++;
						earthDamage1=15;				
						if (count==30)
						{
							abilityTimer1.stop();
							os.charges1P1--;
							count = 0;
							earthDamage1 = 2;							
							abilities.put("Earth1",abilities.get("Earth1")-1);	  //removes 1 charge from map  						
						}	
					}
					else
					{
						abilityTimer1.stop();
					}	
				}
				else if (e.getSource()==abilityTimer1P2) // ENHANCED AUTO'S : Ability 1 : PLAYER 2 
				{
					if(abilities.get("Earth1P2")>0)
					{
						count++;
						earthDamage2=15;				
						if (count==30)
						{
							abilityTimer1P2.stop();
							os.charges1P2--;
							count = 0;
							earthDamage2 = 2;							
							abilities.put("Earth1P2",abilities.get("Earth1P2")-1);	  //removes 1 charge from map  						
						}	
					}
					else
					{
						abilityTimer1P2.stop();
					}	
				}
				else if (e.getSource()==abilityTimer2) // ENHANCE DEFENSE : Ability 2 : PLAYER 1
				{
					if(abilities.get("Earth2")>0)
					{
						count++;
						earthDamageReduced1 = 2;
						earthDefense1 = true;				
						if (count==50)
						{
							abilityTimer2.stop();
							os.charges2P1--;
							count = 0;
							earthDamageReduced1 = 0;
							earthDefense1 = false;							
							abilities.put("Earth2",abilities.get("Earth2")-1);	  //removes 1 charge from map  						
						}	
					}
					else
					{
						abilityTimer2.stop();
					}
					
				}
				else if (e.getSource()==abilityTimer2P2) // ENHANCE DEFENSE : Ability 2 : PLAYER 2
				{
					System.out.println("ok");
					if(abilities.get("Earth2P2")>0)
					{
						count++;
						earthDamageReduced2 = 2;
						earthDefense2 = true;				
						if (count==50)
						{
							abilityTimer2P2.stop();
							os.charges2P2--;
							count = 0;
							earthDamageReduced2 = 0;
							earthDefense2 = false;							
							abilities.put("Earth2P2",abilities.get("Earth2P2")-1);	  //removes 1 charge from map  						
						}	
					}
					else
					{
						abilityTimer2P2.stop();
					}
					
				}
				else if (e.getSource()==abilityTimer3) // BLAST : Ability 3 : PLAYER 1
				{
					if (abilities.get("Earth3")>0)
					{
						if (os.blast1X+200>=os.char2x && 400<=os.char2y+100 && os.blast1X+200<=os.char2x+80) //if blast hits opponent hp will decrease
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.hp2width-=20;
							os.repaint();							
							abilities.put("Earth3",abilities.get("Earth3")-1);
						}
						else if (os.blast1X+200>1100)  //if blast goes out of bounds
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.repaint();
							abilities.put("Earth3",abilities.get("Earth3")-1);							
						}
						else
						{
							os.shootIt=true;
							os.blast1X+=10;
							os.repaint();	
						}
					
					}
					else abilityTimer3.stop();
				}
				else if (e.getSource()==abilityTimer1P2) // ENHANCE AUTO'S : Ability 1 : PLAYER 2 
				{
					if(abilities.get("Earth1P2")>0)
					{
						count++;
						earthDamage2=50;				
						if (count==15)
						{
							abilityTimer1P2.stop();
							os.charges1P2--;
							count = 0;
							earthDamage2 = 2;							
							abilities.put("Earth1P2",abilities.get("Earth1P2")-1);	  //removes 1 charge from map  						
						}	
					}
					else
					{
						abilityTimer1P2.stop();
					}
				}
				
				else if (e.getSource()==abilityTimer3)
				{
					if (abilities.get("Earth3")>0)
					{
						if (os.blast1X+200>=os.char2x && 400<=os.char2y+100 && os.blast1X+200<=os.char2x+80) //if blast hits opponent hp will decrease
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.hp2width-=40;
							os.repaint();							
							abilities.put("Earth3",abilities.get("Earth3")-1);
						}
						else if (os.blast1X+200>1100)  //if blast goes out of bounds
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.repaint();
							abilities.put("Earth3",abilities.get("Earth3")-1);							
						}
						else
						{
							os.shootIt=true;
							os.blast1X+=10;
							os.repaint();	
						}
					
					}
					else abilityTimer3.stop();
				}
				else if (e.getSource()==abilityTimer3P2)
				{
					if (abilities.get("Earth3P2")>0)
					{
						if ((os.blast2X+os.char2x)-120<=os.char1x && 400<=os.char1y+100 && (os.blast2X+os.char2x)>=os.char1x-80) //if blast hits opponent hp will decrease
						{
							System.out.println("this one");
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.hpRedWidth1+=40;
							os.repaint();							
							abilities.put("Earth3P2",abilities.get("Earth3P2")-1);
						}
						else if ((os.blast2X+os.char2x)-20<0)  //if blast goes out of bounds
						{
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.repaint();
							abilities.put("Earth3P2",abilities.get("Earth3P2")-1);							
						}
						else
						{
							os.shootIt2=true;
							os.blast2X-=60;
							os.repaint();	
						}
					
					}
					else abilityTimer3P2.stop();
					
				}
			}
			else if (element.equals("Water"))
			{
				System.out.println("Water");
				
				if (e.getSource()==abilityTimer1) // HEAL : ABILITY 1 : PLAYER 1
				{
					if (abilities.get("Water1")>0)
					{
						if(os.hpRedWidth1<=100 && count==0)
						{
							os.playerHeal=1;
							os.hpRedWidth1=0;
							os.healIt=true;
							os.repaint();						
						}
						else if(os.hpRedWidth1 >= 100 && count==0)
						{
							os.playerHeal=1;
							os.hpRedWidth1-=100;
							os.healIt=true;
							os.repaint();								
						}
						count++;
						if(count==10)
						{
							abilityTimer1.stop();
							os.charges1P1--;
							os.healIt=false;
							os.repaint();
							abilities.put("Water1",abilities.get("Water1")-1);
						}
					}
					else
					{
						abilityTimer1.stop();
					}
				}
				else if (e.getSource()==abilityTimer1P2) // HEAL : ABILITY 1 : PLAYER 2
				{
					if (abilities.get("Water1P2")>0)
					{
						if(os.hp2width>=400 && count==0)
						{
							os.playerHeal=2;
							os.hp2width=500;
							os.healIt=true;
							os.repaint();						
						}
						else if(os.hp2width >= 100 && count==0)
						{
							os.playerHeal=2;
							os.hp2width+=100;
							os.healIt=true;
							os.repaint();								
						}
						count++;
						if(count==10)
						{
							abilityTimer1P2.stop();
							os.charges1P2--;
							os.healIt=false;
							os.repaint();
							abilities.put("Water1P2",abilities.get("Water1P2")-1);
						}
					}
					else
					{
						abilityTimer1.stop();
					}
				}
				else if (e.getSource()==abilityTimer2) // FREEZE : ABILITY 2 : PLAYER 1
				{					
					if (abilities.get("Water2")>0)
					{							
						if (os.char1x+os.snow1x>=os.char2x && 400<=os.char2y+100 && !hit && os.char1x+os.snow1x<=os.char2x+50) //if blast hits opponent hp will decrease
						{	
							System.out.println("hello");
							os.hp2width-=20;
							canWalk2=false;	
							os.snowIt=false;
							os.snow1x=0;					
							//FREEZE THEM WITH THAT canMove() METHOD
							os.repaint();	
							hit = true;			
							
						}
						else if (hit)
						{
							count++;
						}
						else
						{
							os.playerSnow=1;
							os.snowIt=true;
							os.snow1x+=40;
							os.repaint();
							count++;
						}	
						if (count==50)
						{	
							abilityTimer2.stop();
							canWalk2=true;
							os.snowIt=false;
							os.snow1x=0;																
							os.charges2P1--;		
							abilities.put("Water2",abilities.get("Water2")-1);	
							os.repaint();	
															
						}
					
					}
					else 
					{
						abilityTimer2.stop();
					}
				}
				else if (e.getSource()==abilityTimer2P2) // FREEZE : ABILITY 2 : PLAYER 2
				{					
					if (abilities.get("Water2P2")>0)
					{							
						if (os.char2x+os.snow2x<=os.char1x && 400<=os.char1y+100 && !hit && os.char2x+os.snow2x<=os.char2x-50) //if blast hits opponent hp will decrease
						{	
							os.hpRedWidth1+=20;
							canWalk=false;	
							os.snowIt=false;
							os.snow2x=0;					
							//FREEZE THEM WITH THAT canMove() METHOD
							os.repaint();	
							hit = true;			
							
						}
						else if (hit)
						{
							count++;
						}
						else
						{
							os.playerSnow=2;
							os.snowIt=true;
							os.snow2x-=40;
							os.repaint();
							count++;
						}	
						if (count==50)
						{	
							abilityTimer2P2.stop();
							canWalk=true;
							os.snowIt=false;
							os.snow2x=0;																
							os.charges2P2--;		
							abilities.put("Water2P2",abilities.get("Water2P2")-1);	
							os.repaint();	
															
						}
					
					}
					else 
					{
						abilityTimer2P2.stop();
					}
				}
				else if (e.getSource()==abilityTimer3)
				{
					if (abilities.get("Water3")>0)
					{
						if (os.blast1X+200>=os.char2x && 400<=os.char2y+100 && os.blast1X+200<=os.char2x+80) //if blast hits opponent hp will decrease
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.hp2width-=40;
							os.repaint();							
							abilities.put("Water3",abilities.get("Water3")-1);
						}
						else if (os.blast1X+200>1100)  //if blast goes out of bounds
						{
							abilityTimer3.stop();
							os.shootIt=false;
							os.blast1X=0;
							os.charges3P1--;
							os.repaint();
							abilities.put("Water3",abilities.get("Water3")-1);							
						}
						else
						{
							os.shootIt=true;
							os.blast1X+=10;
							os.repaint();	
						}
					
					}
					else abilityTimer3.stop();
				}
				else if (e.getSource()==abilityTimer3P2)
				{
					if (abilities.get("Water3P2")>0)
					{
						if ((os.blast2X+os.char2x)-120<=os.char1x && 400<=os.char1y+100 && (os.blast2X+os.char2x)>=os.char1x-80) //if blast hits opponent hp will decrease
						{
							System.out.println("this one");
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.hpRedWidth1+=40;
							os.repaint();							
							abilities.put("Water3P2",abilities.get("Water3P2")-1);
						}
						else if ((os.blast2X+os.char2x)-20<0)  //if blast goes out of bounds
						{
							abilityTimer3P2.stop();
							os.shootIt2=false;
							os.blast2X=0;
							os.charges3P2--;
							os.repaint();
							abilities.put("Water3P2",abilities.get("Water3P2")-1);							
						}
						else
						{
							os.shootIt2=true;
							os.blast2X-=60;
							os.repaint();	
						}
					
					}
					else abilityTimer3P2.stop();
					
				}
			}
			
			
		}
	}
}