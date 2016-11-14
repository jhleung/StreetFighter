
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

/**
 *Makes the menu to select characters for player 1
 */
public class Player1CharacterSelectionMenu extends JFrame implements ActionListener
{
	private JFrame p1characterSelectionMenu, p2characterSelectionMenu;
	public static Player1 p1;
	private JPanel selectElement , displayLeft, displayRight;
	private DisplayCharacterPanel displayChar;
	private JButton click;
	private JLabel titleLeft, titleRight;
	private ObjectInputStream in;
	private JButton[] buttons; 
	
	/**
	 *Makes the frame/menu
	 */
	public Player1CharacterSelectionMenu()
	{
		
		selectElement = new JPanel();
		selectElement.setLayout(new GridLayout(2,2,30,30));
		
		buttons = new JButton[5];
		buttons[0] = new JButton("Air");
		buttons[1] = new JButton("Water");
		buttons[2] = new JButton("Earth");
		buttons[3] = new JButton("Fire");
		buttons[4]=new JButton("Choosing...");
		
		buttons[0].setBackground(Color.WHITE.darker().darker());
		buttons[1].setBackground(Color.BLUE.darker().darker());
		buttons[2].setBackground(Color.GREEN.darker().darker());
		buttons[3].setBackground(Color.RED.darker().darker());
		buttons[4].setFont(new Font("Papyrus",Font.BOLD,20));
		buttons[4].setBackground(Color.WHITE);
		buttons[4].setForeground(Color.BLACK);
		buttons[4].setEnabled(false);
		buttons[4].addActionListener(this);
		
		for (int i = 0; i<=3; i++)
		{
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("Papyrus",Font.BOLD,100));
			buttons[i].setForeground(Color.BLACK);
			selectElement.add(buttons[i]);
		}
		
		setTitle("Player 1 Selection Menu");
		setSize(1100,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayChar = new DisplayCharacterPanel();
		titleLeft = new JLabel("Player 1!");
		titleLeft.setFont(new Font("Papyrus", Font.BOLD, 50));
		
		displayLeft = new JPanel();
		displayLeft.setLayout(new BorderLayout());
		displayLeft.add(titleLeft,BorderLayout.NORTH);
		displayLeft.add(displayChar,BorderLayout.CENTER);	
		displayLeft.add(buttons[4],BorderLayout.SOUTH);
		
		titleRight = new JLabel("Select your character!");
		titleRight.setFont(new Font("Papyrus", Font.BOLD, 50));
		
		displayRight = new JPanel();
		displayRight.setLayout(new BorderLayout());
		displayRight.add(titleRight,BorderLayout.NORTH);
		displayRight.add(selectElement,BorderLayout.EAST);
		
		add(displayLeft,BorderLayout.CENTER);
		add(displayRight,BorderLayout.EAST);
		
		setVisible(false);
		
	}
	/**
	 *Saves the user input of his selection of character element, allows transition to player2selectionmenu
	 *@param e the action event
	 */
	public void actionPerformed(ActionEvent e)
	{
		Player2CharacterSelectionMenu p2 = new Player2CharacterSelectionMenu();
		
		buttons[4].setEnabled(true);
		buttons[4].setBackground(Color.BLACK);
			
		if (e.getSource()==buttons[0])
		{
			p1 = new Player1("Air");
			
			try
			{
				displayChar.drawAvatar(ImageIO.read(this.getClass().getResource("Air.png")));//new ImageIcon("air.jpg").getImage());
			}
			catch (IOException p){}	
			titleRight.setText("Air!");
			titleRight.setForeground(Color.GRAY);
			titleLeft.setForeground(Color.GRAY);
			buttons[4].setText("Player 2 >>");
			buttons[4].setForeground(Color.WHITE);
		
		}
		else if (e.getSource()==buttons[1])
		{
			p1 = new Player1("Water");
			
			try
			{
				displayChar.drawAvatar(ImageIO.read(this.getClass().getResource("Water.png")));
			}
			catch (IOException p){}	
			titleRight.setText("Water!");
			titleRight.setForeground(Color.BLUE);
			titleLeft.setForeground(Color.BLUE);
			buttons[4].setText("Player 2 >>");
			buttons[4].setForeground(Color.BLUE);
		}
		else if (e.getSource()==buttons[2])
		{
			p1 = new Player1("Earth");
			
			try
			{
				displayChar.drawAvatar(ImageIO.read(this.getClass().getResource("Earth.png")));
			}
			catch (IOException p){}
			titleLeft.setForeground(Color.GREEN);
			titleRight.setForeground(Color.GREEN);
			titleRight.setText("Earth!");
			buttons[4].setText("Player 2 >>");	
			buttons[4].setForeground(Color.GREEN);
		}
		else if (e.getSource()==buttons[3])
		{
			p1 = new Player1("Fire");
			
			try
			{
				displayChar.drawAvatar(ImageIO.read(this.getClass().getResource("Fire.png")));
			}
			catch (IOException p){}	
			titleLeft.setForeground(Color.RED);
			titleRight.setForeground(Color.RED);
			titleRight.setText("Fire!");
			buttons[4].setText("Player 2 >>");
			buttons[4].setForeground(Color.RED);
		}
		else if (e.getSource()==buttons[4])
		{
			setVisible(false);
			p2.setVisible(true);
		}
	}
}
