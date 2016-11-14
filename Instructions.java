import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Instructions extends JFrame implements ActionListener
{
	private ImagePanel imgP;
	private JButton home;
	public Instructions(ImagePanel ip)
	{
		imgP = ip;
		home = new JButton("Home");
		home.addActionListener(this);
		setTitle("Street Fighter Menu");		
		setSize(1100,600);
		
		add(imgP,BorderLayout.CENTER);
		add(home,BorderLayout.SOUTH);
		
		setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==home)
		{
			System.out.println("joe");
			setVisible(false);
			new StreetFighterMenu().setVisible(true);
		}
	}
}