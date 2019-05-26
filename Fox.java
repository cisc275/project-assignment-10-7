import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

/**
 * Non-controllable Fox character, predator for bird player.
 */

public class Fox extends Character implements Movers{

	static int foxCount=0;
	int flip =1;
	private int imgInd = 7;
	static int foxSpeed = 15;
	static int width = 130;
	static int height = 45;
	
	/**
	 * Fox constructor
	 */
	Fox(int x, int y, int w, int h) {
		super(x, y, w, h);
		super.setImgInd(View.FoxFwd);
	}
	
	/** 
	 * This will flip the fox back/forth between borders, 
	 * changing its direction if it hits a border.
	 * @param Nothing
	 * @return Nothing
	 */
	public void move() {
		
		checkBorder();
		switch(flip) {
		case 1:
			xPos -=foxSpeed;
			super.setImgInd(View.FoxFwd);
			break;
		case 2:
			super.setImgInd(View.FoxBck);
			xPos +=foxSpeed;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Randomly creates new foxes
	 * @param Nothing
	 * @return Nothing
	 */
	public static void addFox() {
		Random rand = new Random();
	     if(foxCount<Model.foxLimit && rand.nextInt(100)==5) {
	    	Fox f = new Fox(View.frameWidth,rand.nextInt((View.frameHeight - View.frameHeight/10) - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3) - height, width, height) ;
	 		Model.charArr.add(f);
	 		foxCount++; 
	     }
	}
	
	/**
	 * Checks to see if the Fox's position is currently at a border
	 * and sets a flag depending on the fox's position. 
	 * @param Nothing
	 * @return Nothing
	 */
	public void checkBorder() {
		if (xPos <= 0+super.width/2)
		{
			flip=2;
		}
		if(xPos>=View.frameWidth-super.width)
		{
			flip=1;
		}
	}
	
	/**
	 * Sets speed of fox based on given x for dynamic game play.
	 * @param x New speed of Fox
	 * @return Nothing
	 */
	public static void setSpeed(int x) {
		foxSpeed = x;
	}
	
	/**
	 * Returns current Fox speed.
	 * @return foxSpeed
	 */
	public static int getSpeed() {
		return foxSpeed;
	}
			
}