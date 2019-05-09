import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

/**
 * 
 * Fox character that is not controlled by the user but attacks the bird character
 *
 */

public class Fox extends AutoCharacters implements Movers{

	static int foxCount=0;
	int flip =1;
	private int imgInd = 7;
	static int width = 130;
	static int height = 45;
	
	Fox(int x, int y, int w, int h) {
		super(x, y, w, h);
		super.setImgInd(imgInd);
		// TODO Auto-generated constructor stub
	}
	

	/**
	*This function changes the position of the Fox to jump after
	*@param none
	*@return nothing
	*/
	public void attack(){

	}
	
	// This will flip the class back/forth between borders 
	public void move() {
		
		checkBorder();
		switch(flip) {
		case 1:
			xPos -=15;
			super.setImgInd(View.FoxFwd);
			break;
		case 2:
			super.setImgInd(View.FoxBck);
			xPos +=15;
			break;
		}
	}
		
	public static void addFox() {
		Random rand = new Random();
	     if(foxCount<Model.foxLimit && rand.nextInt(100)==5) {
	    	 Fox f = new Fox(View.frameWidth,rand.nextInt(View.frameHeight - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3), width, height);
	 		Model.charArr.add(f);
	 		foxCount++; 
	     }
	}
	
	public void checkBorder() {
		if (xPos <=10)
		{
			flip=2;
		}
		if(xPos>=View.frameWidth-10)
		{
			flip=1;
		}
	}
			
}