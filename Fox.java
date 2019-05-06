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

public class Fox extends AutoCharacters{

	static int foxCount=0;
	int flip =1;
	static Color color = new Color(0, 0, 0);
	Fox(int x, int y, int w, int h) {
		super(x, y, w, h, color);
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
			west=7;
			xPos -=15;
			break;
		case 2:
			west=9;
			xPos +=15;
			break;
		}
	}
		
	public static void addFox() {
		Random rand = new Random();
	     if(foxCount<5 && rand.nextInt(100)==5) {
	    	 Fox f = new Fox(View.frameWidth,((2 * View.frameHeight)/3), 25, 25);
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