/** Image Credits:
 * 	 Plane: https://www.kisspng.com/png-vector-night-sky-flying-cartoon-white-plane-24841/
 *   Northern Harrier: https://www.shutterstock.com/image-vector/vector-illustration-cartoon-flying-bird-animation-325506773?src=HJDXMRFmR8I9_tDY3f3fsQ-1-3&drawer=open
 *   Osprey: https://www.shutterstock.com/image-vector/vector-illustration-cartoon-flying-house-sparrow-1136982263?src=xRdlkBFtay_XNEfQPLy4CA-1-16
 *   Mouse: https://www.freepik.com/free-vector/cartoon-mice-collection_1588305.htm
 *   Fish: Own illustration
 *   Pollution:
 *   Marsh background: https://www.vecteezy.com/vector-art/175365-seagrass-marsh-illustration
 *   Grass background: https://www.shutterstock.com/video/clip-12615866-animated-green-grass-blue-sky-clouds
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JTextField;


public class View extends JPanel{
	
	int imageWidth = 75;
	int imageHeight = 75;
	int smallWidth = 50;
	int smallHeight = 50;
	int foxHeight = 150;
	int foxWidth = 150;
	int movebg = 0;
	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final static int frameWidth = screenSize.width;//original size was 500
	final static int frameHeight = screenSize.height;//original size was 300
	final static int frameCount = 8;
	int frameNum = 0;
	Direction d;
	BufferedImage[][] pics;
	
	final static int NonMigFwd=0;
	final static int NonMigBck=1;
	final static int MigFwd=2;
	final static int MigBck=3;
	final static int PlaneImg=4;
	final static int Mouse = 5;
	final static int Fish = 6;
	final static int FoxFwd=7;
	final static int Trash = 8;
	final static int FoxBck=9;
	
	
	Color sky = new Color(100,149,237);
	Color grass = new Color(76,153, 0);
	
	BufferedImage grassImg;
	BufferedImage marshImg;
	BufferedImage marshFlipImg;
	
	JFrame frame;
	JFrame frameEnd;
	JFrame frame2;
	JFrame frame3;
	JPanel panel;
	boolean frameSwitch; 
	boolean run=false;

	JButton b1;
	JButton b3;
	Bird player;

	JButton qb1;
	JButton qb2;
	JButton qb3;
	JButton qb4;
	JLabel quizLabel;
	JLabel quizLabel2;
	
	JPanel cards;

	
	String[][] questArr = {{"What is the bird in the first game?", "Osprey", "Northern Harrier", "Eagle", "Hawk"},
			{"What is the bird in the second game?", "Osprey", "Northern Harrier", "Eagle", "Hawk"},
			{"What is the food of the Osprey?", "Mice", "Seeds", "Fish", "Bread"}
	};
	
	/**
	 * This is the view constructor. It will load up
	 * all the image files and set up the JFrame
	 * @param nothing
	 * @return nothing
	 */

	View(){
    	pics = new BufferedImage[10][frameCount];
    	BufferedImage img = createImage("bird_forward_75.png");
    	BufferedImage img2 = createImage("bird_backward_75.png");
    	BufferedImage b2img = createImage("bird2_forward_75.png");
    	BufferedImage b2img2 = createImage("bird2_backward_75.png");
    	BufferedImage planeImg = createImage("plane.png");
    	BufferedImage mouseImg = createImage("mouse.png");
    	BufferedImage fishImg = createImage("fish.png");
    	BufferedImage foxImg = createImage("fox.png");
    	BufferedImage fox2Img = createImage("fox_backward.png");
    	BufferedImage grass  = createImage("grass.png");
    	BufferedImage marsh  = createImage("marsh.png");
    	BufferedImage marsh2  = createImage("marsh_flip.png");
    	marshImg = resize(marsh, frameHeight, frameWidth);
    	marshFlipImg = resize(marsh2, frameHeight, frameWidth);
    	grassImg = resize(grass, frameHeight, frameWidth);
    	BufferedImage trashImg = createImage("trash.png");
    	
    	for(int i = 0; i < frameCount; i++) {
    		pics[NonMigFwd][i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[NonMigBck][i] = img2.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[MigFwd][i] = b2img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		pics[MigBck][i] = b2img2.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);	
    		pics[Mouse][i] = mouseImg.getSubimage(smallWidth*i, 0, smallWidth, smallHeight);
    		pics[Fish][i] = fishImg.getSubimage(smallWidth*i, 0, smallWidth, smallHeight);
    		pics[FoxFwd][i] = foxImg.getSubimage(foxWidth*i, 0, foxWidth, foxHeight);
    		pics[FoxBck][i] = fox2Img.getSubimage(foxWidth*i, 0, foxWidth, foxHeight);
    		pics[Trash][i] = trashImg;
    		pics[PlaneImg][i] = planeImg;
    	}
    	
    	
    	b1 = new JButton("Deserialize");
    	b1.setBounds(frameWidth-200,frameHeight-100,100,50);
    	
    	b3 = new JButton("Serialize");
    	b3.setBounds(frameWidth-300,frameHeight-100,100,50);
		
    	frame = new JFrame();
    	frame.add(b1);
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
	
	public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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
		
		frame.repaint();
		
		int sleepTime = 30;
		if(!flag)
			sleepTime=1000;
		else
			sleepTime=30;
		
		try {
			Thread.sleep(sleepTime);//changed to 0 for smooth frames
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		run = flag;
		
	}
	
	/**
	 * This will read the filename and return a buffered image for animation
	 * @param filename of the image to be accessed 
	 * @return a buffered image when implemented
	 */
	private BufferedImage createImage(String filename){
		BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("src/"+filename));
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
			movebg-=2;
			if (movebg % frameWidth == 0) {
				movebg = 0;
			}
			g.drawImage(marshImg, (movebg % frameWidth), 0, null);
			g.drawImage(marshFlipImg, ((movebg % frameWidth)+frameWidth), 0, null);
		}
		// Given the graphic, this method will place the images on the user screen
		else{
			g.drawImage(grassImg, 0, 0, null, this);
		}
		
		g.setColor(Color.black);
		g.drawRect(frameWidth-(frameWidth/5+frameWidth/20), 0+frameHeight/10, frameWidth/5, frameHeight/30);
		
		
		if(run) {
			frameNum = (frameNum + 1) % frameCount;
			for(Movers c: Model.charArr)
			{	
				Character curChar=(Character)c;
				g.drawImage(pics[curChar.imgArrNum][frameNum], curChar.xPos, curChar.yPos, null, this);
			}	

			
	    	g.setColor(Color.red);
			g.fillRect(frameWidth-(frameWidth/5+frameWidth/20)+1, 1+frameHeight/10, 
					((frameWidth/5-1)*(player.getHealth()))/1000, frameHeight/30-1);
			
			g.drawImage(pics[player.imgArrNum][frameNum], player.xPos, player.yPos, null, this);

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
		
		frameEnd= new JFrame();
		JLabel label1 = new JLabel("Game Over!", JLabel.CENTER);
		label1.setOpaque(true);
        label1.setBackground(sky);
        label1.setFont(new Font("Calibri", Font.BOLD, 100));
		frameEnd.add(label1);
		frameEnd.setBackground(Color.gray);
		frameEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEnd.setSize(frameWidth, frameHeight);
		frameEnd.setFocusable(true);
		frameEnd.requestFocus();
		frameEnd.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frameEnd.setUndecorated(true);
		frameEnd.setVisible(true);
		JFrame temp = frame;
		frame = frameEnd;
		temp.dispose();
    	
	}
	
	/**
	 * Creates new frame and sets frame logic for level 2.
	 * @param Nothing
	 * @return Nothing
	 */
	public void lvl2Frame() {
		frameNum=0;
		frameSwitch = true;
		frame2 = new JFrame();
		
		frame2.setBackground(Color.gray);
    	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame2.setSize(frameWidth, frameHeight);
    	frame2.setFocusable(true);
    	frame2.requestFocus();
    	frame2.getContentPane().add(this);
    	frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	frame2.setUndecorated(true);
		frame2.setVisible(true);
		JFrame temp = frame;
		frame = frame2;
		temp.dispose();
	}
	
	/**
	 * Creates new frame for end of game screen.
	 * @param Nothing 
	 */
	public void lvl2startFrame() {
	
		frame3 = new JFrame();
		JLabel label1 = new JLabel("Level 2", JLabel.CENTER);
		label1.setOpaque(true);
        label1.setBackground(sky);
        label1.setFont(new Font("Calibri", Font.BOLD, 100));
		frame3.add(label1);
		//frame3.setBackground(Color.gray);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setSize(frameWidth, frameHeight);
		frame3.setFocusable(true);
		frame3.requestFocus();
		frame3.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame3.setUndecorated(true);
		frame3.setVisible(true);
		JFrame temp = frame;
		frame = frame3;
		temp.dispose();
    	
	}
	
	public void quizView(){
		
		frame2 = new JFrame();
		
		cards = new JPanel(new CardLayout());
		JPanel card1 = new JPanel(null);
		
		
		cards.add(card1);
		
		JTextField TextField1 = new JTextField("TextField1", 20);
		 
		
		//quizLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		quizLabel = new JLabel("Big");
		quizLabel.setOpaque(true);
		card1.add(quizLabel);
		  
		qb1 = new JButton();
    	qb1.setBounds(frameWidth/6,2*frameHeight/3,frameWidth/5, frameHeight/10);
    	qb1.setActionCommand("b1");
    	
    	qb2 = new JButton();
    	qb2.setBounds(2*frameWidth/3,2*frameHeight/3,frameWidth/5, frameHeight/10);
    	qb2.setActionCommand("b2");
    	
    	qb3 = new JButton();
    	qb3.setBounds(frameWidth/6,5*frameHeight/6,frameWidth/5, frameHeight/10);
    	qb3.setActionCommand("b3");
    	
    	qb4 = new JButton();
    	qb4.setBounds(2*frameWidth/3, 5*frameHeight/6,frameWidth/5, frameHeight/10);
    	qb4.setActionCommand("b4");
    	
    	
        card1.add(qb1);
        card1.add(qb2);
        card1.add(qb3);
        card1.add(qb4);
        
        
      
        
        frame2.getContentPane().add(cards);
		frame2.setBackground(Color.gray);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(frameWidth, frameHeight);
		frame2.setFocusable(true);
		frame2.requestFocus();
		frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		frame2.setUndecorated(true);
		frame2.setVisible(true);
		frame=frame2;
        
		
//		frame2 = new JFrame();
//		
//		
//		qb1 = new JButton();
//    	qb1.setBounds(frameWidth/6,2*frameHeight/3,frameWidth/5, frameHeight/10);
//    	qb1.setActionCommand("b1");
//    	frame2.add(qb1);
//    	qb2 = new JButton();
//    	qb2.setBounds(2*frameWidth/3,2*frameHeight/3,frameWidth/5, frameHeight/10);
//    	qb2.setActionCommand("b2");
//    	frame2.add(qb2);
//    	qb3 = new JButton();
//    	qb3.setBounds(frameWidth/6,5*frameHeight/6,frameWidth/5, frameHeight/10);
//    	qb3.setActionCommand("b3");
//    	frame2.add(qb3);
//    	qb4 = new JButton();
//    	qb4.setBounds(2*frameWidth/3, 5*frameHeight/6,frameWidth/5, frameHeight/10);
//    	qb4.setActionCommand("b4");
//    	frame2.add(qb4);
//    	
//		quizLabel = new JLabel("Big", JLabel.CENTER);
//		quizLabel.setOpaque(true);
//		quizLabel.setFont(new Font("Calibri", Font.BOLD, 50));
//		frame2.add(quizLabel);
//		quizLabel2 = new JLabel("hey", JLabel.CENTER);
//		quizLabel2.setOpaque(true);
//		quizLabel2.setFont(new Font("Calibri", Font.BOLD, 25));
//		frame2.add(quizLabel2);
//		frame2.setBackground(Color.gray);
//		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame2.setSize(frameWidth, frameHeight);
//		frame2.setFocusable(true);
//		frame2.requestFocus();
//		frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		frame2.setUndecorated(true);
//		frame2.setVisible(true);
//		JFrame temp = frame;
//		frame = frame2;
//		temp.dispose();

	}
	
	public void setText(int q) {
		//quizLabel.setText(questArr[0][0]);
		qb1.setText(questArr[q][1]);
		qb2.setText(questArr[q][2]);
		qb3.setText(questArr[q][3]);
		qb4.setText(questArr[q][4]);
		
	}

	
}
