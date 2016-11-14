
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * StreetFighterMenu
 */
 
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
/**
 *Home screen allows user to play or view instructions
 */
public class StreetFighterMenu extends JFrame implements ActionListener, MouseListener
{
	private JButton[] buttons; 
	private JPanel out;
	private JFrame homeFrame,characterSelectionMenu,takeAQuizFrame;
	private	ImagePanel backGround,interPad;
	
	public StreetFighterMenu()
	{	
		backGround = new ImagePanel(new ImageIcon(this.getClass().getResource("background.jpg")).getImage());
		backGround.setLayout(new GridBagLayout()); 
			
		interPad = new ImagePanel(new ImageIcon(this.getClass().getResource("backgrund.jpg")).getImage());
		interPad.setLayout(new GridLayout(4,4,20,20));
	
		buttons = new JButton[2];
		buttons[0] = new JButton("Play");
		buttons[1] = new JButton("Instructions");
	
		for (int i = 0; i<=1; i++)
		{		
			 buttons[i].addMouseListener(this);
			 buttons[i].addActionListener(this);
			 buttons[i].setFont(new Font("Papyrus",Font.BOLD,50));
			 buttons[i].setBackground(Color.RED.darker().darker());
			 buttons[i].setForeground(Color.GRAY);
			 interPad.add(buttons[i],BorderLayout.SOUTH);
		}
		
		backGround.add(interPad);
		
		setTitle("Street Fighter Menu");		
		setSize(1100,600);
		
		add(backGround);
				
		setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 *Takes player to select characters for 2 players or gives instructions
	 *@param e the action event
	 */
	public void actionPerformed(ActionEvent e)
	{
		Player1CharacterSelectionMenu c = new Player1CharacterSelectionMenu();
		
		if (e.getSource()==buttons[0])
		{
			setVisible(false);
			c.setVisible(true);
		}
		else if (e.getSource()==buttons[1])
		{
			setVisible(false);
			new Instructions(new ImagePanel(new ImageIcon(this.getClass().getResource("Instructions1.png")).getImage())).setVisible(true);
		}
	}
	/**
	 *not used
	 *@param e the mouse event
	 */
	public void mouseClicked(MouseEvent e)
	{
		
	}
	/**
	 *not used
	 *@param e the mouse event
	 */
	public void mouseReleased(MouseEvent e){}
	/**
	 *not used
	 *@param e the mouse event
	 */
	public void mousePressed(MouseEvent e){}
	/**
	 *changes color of background of button
	 *@param e the mouse event
	 */
	public void mouseEntered(MouseEvent e)
	{
		((JButton)e.getSource()).setBackground(Color.RED);
		((JButton)e.getSource()).setForeground(Color.BLACK);
	}
	/**
	 *changes color of background of button
	 *@param e the mouse event
	 */
	public void mouseExited(MouseEvent e)
	{
		((JButton)e.getSource()).setBackground(Color.RED.darker().darker());
		((JButton)e.getSource()).setForeground(Color.GRAY);
	}
	
	
}