import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Plane extends AutoCharacters{
	static int planeCount =0;

	Plane(int x, int y, int w, int h) {
		
		super(x, y,  w, h, Color.black);
		// TODO Auto-generated constructor stub
	}

	/**
	*This function changes the position of the Plane to cross the view to attack
	*@param none
	*@return nothing
	*/
	
//	public void move(){
//		xPos -=3;
//		if (xPos<=0)
//		{
//			xPos=500;
//		}
//
//	}
	
	public static void planeFactory() {
		
		Random rand = new Random();
		if (planeCount<10 && rand.nextInt(100)==5) {
			Plane p = new Plane(View.frameWidth,rand.nextInt((2*View.frameHeight)/3), 50, 50);
			Model.charArr.add(p);
			planeCount++;
		}
	}
}