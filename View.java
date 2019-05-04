/** Image Credits:
 * 	 Plane: https://www.kisspng.com/png-vector-night-sky-flying-cartoon-white-plane-24841/
 *   Northern Harrier: https://www.shutterstock.com/image-vector/vector-illustration-cartoon-flying-bird-animation-325506773?src=HJDXMRFmR8I9_tDY3f3fsQ-1-3&drawer=open
 *   Osprey: https://www.shutterstock.com/image-vector/vector-illustration-cartoon-flying-house-sparrow-1136982263?src=xRdlkBFtay_XNEfQPLy4CA-1-16
 *   Mouse: https://www.freepik.com/free-vector/cartoon-mice-collection_1588305.htm
 *   Fish: 
 *   Pollution:
 *   Marsh background: https://www.vecteezy.com/vector-art/175365-seagrass-marsh-illustration
 *   Grass background: https://www.shutterstock.com/video/clip-12615866-animated-green-grass-blue-sky-clouds
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class View extends JPanel{
	
	int imageWidth = 75;
	int imageHeight = 75;
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int frameWidth = screenSize.width;//original size was 500
	final static int frameHeight = screenSize.height;//original size was 300
	int frameCount = 8;
	int frameNum = 0;
	Direction d;
	BufferedImage[][] pics;
	
	Color sky = new Color(100,149,237);
	Color grass = new Color(76,153, 0);
	
	BufferedImage grassImg;
	BufferedImage marshImg;
	
	JFrame frame;
	JFrame frame1;
	JFrame frame2;
	JPanel panel;
	boolean frameSwitch; 
	boolean run=false;

	JButton b1;
	JButton b2;
	JButton b3;
	Bird player;

	
	/**
	 * This is the view constructor. It will load up
	 * all the image files and set up the JFrame
	 * @param nothing
	 * @return nothing
	 */

	View(){
    	pics = new BufferedImage[7][frameCount];
    	BufferedImage img = createImage("bird_forward_75.png");
    	BufferedImage img2 = createImage("bird_backward_75.png");
    	BufferedImage b2img = createImage("bird2_forward_75.png");
    	BufferedImage b2img2 = createImage("bird2_backward_75.png");
    	BufferedImage planeImg = createImage("plane.png");
    	BufferedImage trashImg = createImage("trash.png");
    	BufferedImage foxImg = createImage("test_fox.png");
    	grassImg  = createImage("grass.jpg");
    	marshImg  = createImage("marsh.jpg");
    	for(int i = 0; i < frameCount; i++) {
    		pics[0][i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[1][i] = img2.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[2][i] = b2img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[3][i] = b2img2.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);	
    	}
    	
    	pics[4][0] = planeImg;
    	pics[5][0] = trashImg;
    	pics[6][0] = foxImg;
    	
    	b1 = new JButton("Deserialize");
    	b1.setBounds(frameWidth-200,frameHeight-100,100,50);
    	
    	b2 = new JButton("Run");
    	b2.setBounds(frameWidth-300,frameHeight-100,100,50);
    	
    	b3 = new JButton("Serialize");
    	b3.setBounds(frameWidth-400,frameHeight-100,100,50);
		
    	frame1 = new JFrame();
    	frame = frame1;
    	frame.add(b1);
		frame.add(b2);
		frame.add(b3);
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

	public void update(Bird p, boolean flag){
		player = p;
		run = flag;
		frame.repaint();
		try {
			Thread.sleep(50);//changed to 0 for smooth frames
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
		if(frameSwitch) {
			g.drawImage(marshImg, 0, 0, null, this);
			//g.setColor(sky);
			//g.fillRect(0, 2 * frameHeight/3, frameWidth, frameHeight);
		
			//g.setColor(sky);
			//g.fillRect(0, 0, frameWidth, 2 * frameHeight/3);
		
			g.setColor(Color.black);
			g.drawRect(frameWidth-(frameWidth/5+frameWidth/20), 0+frameHeight/10, frameWidth/5, frameHeight/30);
		}

		// Given the graphic, this method will place the images on the user screen;
		else{
			g.drawImage(grassImg, 0, 0, null, this);

			//g.setColor(grass);
			//g.fillRect(0, 2 * frameHeight/3, frameWidth, frameHeight);
		
			//g.setColor(sky);
			//g.fillRect(0, 0, frameWidth, 2 * frameHeight/3);
		
			g.setColor(Color.black);
			g.drawRect(frameWidth-(frameWidth/5+frameWidth/20), 0+frameHeight/10, frameWidth/5, frameHeight/30);
		}
		
		

		for(AutoCharacters c: Model.charArr)
		{	
			if(c.color.equals(Color.BLACK)) {
				g.drawImage(pics[4][0], c.xPos, c.yPos, null, this);
			}
			else if(c.color.equals(Color.gray)){
				g.drawImage(pics[5][0], c.xPos, c.yPos, null, this);
			}
			else if(c.color.equals(Color.orange)) {
				g.drawImage(pics[6][0], c.xPos, c.yPos, null, this);
			}
			else {
				g.setColor(c.color);
				g.fillRect(c.xPos, c.yPos, 25, 25);
			}
		}		
		
		if(run) {
			int hb= player.getHealth();
			frameNum = (frameNum + 1) % frameCount;
	    	g.setColor(Color.red);
			g.fillRect(frameWidth-(frameWidth/5+frameWidth/20)+1, 1+frameHeight/10, 
					((frameWidth/5-1)*(hb))/1000, frameHeight/30-1);
			
			if(frameSwitch) {
				if(player.getDirec()==Direction.WEST)
					g.drawImage(pics[3][frameNum], player.xPos, player.yPos, null, this);
				else
					g.drawImage(pics[2][frameNum], player.xPos, player.yPos, null, this);
			}
			else{
				if(player.getDirec()==Direction.WEST)
					g.drawImage(pics[1][frameNum], player.xPos, player.yPos, null, this);
				else
					g.drawImage(pics[0][frameNum], player.xPos, player.yPos, null, this);
			}

		}

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

	
	
	/**
	 * Creates new frame for end of game screen.
	 * @param Nothing 
	 */
	public void endFrame() {
	
		frame1 = new JFrame();
		JLabel label1 = new JLabel("Game Over!", JLabel.CENTER);
		label1.setOpaque(true);
        label1.setBackground(sky);
        label1.setFont(new Font("Calibri", Font.BOLD, 100));
		frame1.add(label1);
		frame1.setBackground(Color.gray);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(frameWidth, frameHeight);
		frame1.setFocusable(true);
		frame1.requestFocus();
		frame1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame1.setUndecorated(true);
		frame1.setVisible(true);
		frame = frame1;
    	
	}
	
	/**
	 * Creates new frame and sets frame logic for level 2.
	 * @param Nothing
	 * @return Nothing
	 */
	public void lvl2Frame() {
		frameSwitch = true;
		frame2 = new JFrame();
		frame = frame2;
		frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setFocusable(true);
    	frame.requestFocus();
    	frame.getContentPane().add(this);
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	frame.setUndecorated(true);
		frame.setVisible(true);
	}

}
