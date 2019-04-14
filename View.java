import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.Test;
import javax.swing.JButton;


public class View {
	
	int imageWidth;
	int imageHeight;
	int frameWidth;
	int frameHeight;
	int frameCount;
	int frameNum;
	Direction d;
	BufferedImage[] pics;
	
	
	/**
	 * This is the view constructor. It will load up
	 * all the image files and set up the JFrame
	 * @param nothing
	 * @return nothing
	 */
	View(){
		
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
	public void update(int x, int y, Direction d, boolean flag){
	
		
	}
	
	/**
	 * This will read the filename and return a buffered image for animation
	 * @param filename of the image to be accessed 
	 * @return a buffered image when implemented
	 */
	private BufferedImage createImage(String filename){
		return pics;
	}
	
	
	/**
	 * JPanel to cycle through the picture array and draw the image on the user screen
	 * @param g graphic 
	 * @return nothing
	 */
	public void paint(Graphics g){
		// Given the graphic, this method will place the images on the user screen
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

class ViewTest {
	
	View test = new View();

	@Test
	public void testUpdate() {
		
		Direction d = Direction.EAST;
		test.update(10, 10, d, true);
		
		
	}


}
