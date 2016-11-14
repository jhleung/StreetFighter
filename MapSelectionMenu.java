/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * MapSelectionMenu
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;


public class MapSelectionMenu extends JFrame implements ActionListener
{
	private JFrame mapSelectionMenu;
	private JPanel selectElement , displayLeft, displayRight;
	private DisplayCharacterPanel displayChar;
	private JButton click;
	private Player2 p2;
	private JLabel titleLeft, titleRight;
	private ObjectInputStream in;
	private Map[] maps; 
	private Map map;
	
	//On the left panel, put both the players and their pictures
	public MapSelectionMenu()
	{	
		selectElement = new JPanel();	
		selectElement.setLayout(new GridLayout(2,2,30,30));
		
		maps = new Map[4];
		try
		{
			maps[0] = new Map(ImageIO.read(this.getClass().getResource("stage1.jpg")),"MAP1");
			maps[1] = new Map(ImageIO.read(this.getClass().getResource("stage2.jpg")),"MAP2");
			maps[2] = new Map(ImageIO.read(this.getClass().getResource("stage3.jpg")),"MAP3");
			maps[3] = new Map(ImageIO.read(this.getClass().getResource("stage4.jpg")),"MAP4");
		}
		catch(IOException io){}
//		buttons[4]=new JButton("Choosing...");
	
		for (Map m : maps)
		{
			m.addActionListener(this);
			m.setFont(new Font("Papyrus",Font.BOLD,100));
			m.setForeground(Color.BLACK);
			selectElement.add(m);
		}
			
		setSize(1100,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);

		displayChar = new DisplayCharacterPanel();
		titleLeft = new JLabel("StreetFighter");//filler title
		titleLeft.setFont(new Font("Papyrus", Font.BOLD, 50));
		
		displayLeft = new JPanel();
		displayLeft.setLayout(new BorderLayout());
		displayLeft.add(titleLeft,BorderLayout.NORTH);
		displayLeft.add(displayChar,BorderLayout.CENTER);	
//		displayLeft.add(buttons[4],BorderLayout.SOUTH);
		
		titleRight = new JLabel("Select your map!");
		titleRight.setFont(new Font("Papyrus", Font.BOLD, 50));
		
		displayRight = new JPanel();
		displayRight.setLayout(new BorderLayout());
		displayRight.add(titleRight,BorderLayout.NORTH);
		displayRight.add(selectElement,BorderLayout.EAST);
		
		add(displayLeft,BorderLayout.CENTER);
		add(displayRight,BorderLayout.EAST);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==maps[0])
		{
			titleRight.setText("Map1 Name!");
		}
		else if (e.getSource()==maps[1])
		{
			titleRight.setText("Map2 Name!");
		}
		else if (e.getSource()==maps[2])
		{
			titleRight.setText("Map3 Name!");
		}
		else if (e.getSource()==maps[3])
		{
			titleRight.setText("Map4 Name!");
		}
		
	}
}
