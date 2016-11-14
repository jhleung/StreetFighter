
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * DisplayCharacterPanel
 */
 
import java.awt.*;
import javax.swing.*;
/**
 *Displays the character
 */
public class DisplayCharacterPanel extends JPanel {

  private Image image;
  /**
   *sets default image to null and size to 1000x800
   */
  public DisplayCharacterPanel() {
  	image = null;
  	setSize(1000,800);
  }
  /**
   *Constructor sets image to a given image
   *@param img the image to set
   */
  public DisplayCharacterPanel(Image img)
  {
    image = img;
  }
  /**
   *sets image to a given image
   *@param img the image to set
   */
  public void setImage(Image img)
  {
  	image = img;
  }
  /**
   *Draws the character
   *@param img the character to draw
   */
  public void drawAvatar(Image img)
  {
  	Graphics g = getGraphics();
  	Graphics2D g2 = (Graphics2D) g;
  	super.paintComponent(g2);
    g2.drawImage(img,5,75,this);  	
  }
}
