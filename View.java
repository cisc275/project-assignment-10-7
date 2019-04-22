import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.jupiter.api.*;
import javax.swing.JButton;


public class View extends JPanel{
	
	int imageWidth = 50;
	int imageHeight = 50;
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int frameWidth = screenSize.width;//original size was 500
	final static int frameHeight = screenSize.height;//original size was 300
	int frameCount = 8;
	int frameNum = 0;
	Direction d;
	int xPos=0;
	int yPos=0;
	BufferedImage[][] pics;
	ArrayList<Character> charArr;
	
	Color sky = new Color(100,149,237);
	Color grass = new Color(76,153, 0);
	
	JFrame frame;
	JPanel panel;
	
	/**
	 * This is the view constructor. It will load up
	 * all the image files and set up the JFrame
	 * @param nothing
	 * @return nothing
	 */
	View(){
		
		charArr=new ArrayList<>();
		BufferedImage img = createImage("bird_forward.png");
    	pics = new BufferedImage[2][frameCount];
    	for(int i = 0; i < frameCount; i++)
    		pics[0][i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    	
    	BufferedImage img2 = createImage("bird_backward.png");
    	for(int i = 0; i < frameCount; i++)
    		pics[1][i] = img2.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
		
		
		frame = new JFrame();
		frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setFocusable(true);
    	frame.requestFocus();
    	
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	frame.setUndecorated(true);
    	
    	frame.setVisible(true);	
    	
	}
	
	/**
	 * This will update the frame of the image 
	 * @param x position of the image to be animated 
	 * @param y position of the image to be animated
	 * @param d string of the direction
	 * @param flag changed to true/false based on 
	 *        the key/button press
	 * @return nothing
	 */
	public void update(int x, int y, Direction d, boolean flag, ArrayList<Character> cA){
	
		charArr = cA;
		xPos=x;
		yPos=y;
		frame.repaint();
		try {
			Thread.sleep(0);//changed to 0 for smooth frames
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This will read the filename and return a buffered image for animation
	 * @param filename of the image to be accessed 
	 * @return a buffered image when implemented
	 */
	private BufferedImage createImage(String filename){
		BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
	
	
	/**
	 * JPanel to cycle through the picture array and draw the image on the user screen
	 * @param g graphic 
	 * @return nothing
	 */
	public void paint(Graphics g){
			
		
		// Given the graphic, this method will place the images on the user screen
		//g.drawRect(xPos, yPos, 25, 25);
		g.setColor(grass);
		g.fillRect(0, 2 * frameHeight/3, frameWidth, frameHeight);
		
		g.setColor(sky);
		g.fillRect(0, 0, frameWidth, 2 * frameHeight/3);
		
		g.setColor(Color.black);
		g.drawRect(375, 25, 101, 10);
		
		
		

		for(Character c: charArr)
		{
			if (c.getClass()!=Bird.class) {
				g.setColor(c.color);
				g.fillRect(c.xPos, c.yPos, 25, 25);
			}
			else
			{
				int hb= ((Bird)c).getHealth();
				frameNum = (frameNum + 1) % frameCount;
		    	g.setColor(Color.red);
				g.fillRect(376, 26, hb, 9);
				
				if(((Bird)c).getDirec()==Direction.WEST)
					g.drawImage(pics[1][frameNum], xPos, yPos, null, this);
				else
					g.drawImage(pics[0][frameNum], xPos, yPos, null, this);
			}
		}
		

		
		//g.drawImage((idlePicMap.get(direction.getName()))[frameNum], xPos, yPos, Color.gray, this);
	}
	
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}	

}
/*
 class ViewTest {
  
	 View test = new View();
	 @Test 
	 public void testUpdate() {
		 Direction d = Direction.EAST; test.update(10, 10, d, true);
	}
}*/
 
 