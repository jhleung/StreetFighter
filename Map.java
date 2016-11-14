
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * Player1CharacterSelectionMenu
 */
 
import java.awt.*;
import javax.swing.*;

public class Map extends JButton
{
	private Image image;
	
	public Map(Image img, String s)
	{
		super(s);
		image = img;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
//	  	super.paintComponent(g2);
	    g2.drawImage(image,0,0,this);  
	}
}