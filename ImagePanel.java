
/* Andrew Palet, Jeffrey Leung
 * 2
 * Gallatin
 * ImagePanel
 */
 
import java.awt.*;
import javax.swing.*;
/**
 *Allows usage of JPanels with background images
 */
public class ImagePanel extends JPanel {

  private Image image;
	
 /**
  *Sets the background image to a given image given by string
  *@param img the image to set
  */
  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }
/**
 *Sets the background image to a given image
 *@param img the image to set
 */ 
  public ImagePanel(Image img) {
    image = img;

    setSize(1100,800);
    setLayout(null);
  }
  /**
   *Draws the background onto the panel
   *@param g the graphics to use
   */
  public void paintComponent(Graphics g) 
  {
    g.drawImage(image, 0, 0, null);
  }
}
