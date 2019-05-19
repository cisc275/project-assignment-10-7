import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Plane extends AutoCharacters implements Movers{
	static int planeCount =0;
	static int width = 200;
	static int height = 75;

	Plane(int x, int y, int w, int h) {
		
		super(x, y,  w, h);
		
		// TODO Auto-generated constructor stub
	}

	/**
	*This function changes the position of the Plane to cross the view to attack
	*@param none
	*@return nothing
	*/
	public void move(){
		xPos -=(View.frameWidth/128);
		if (xPos<=0-super.width)
		{
			xPos=View.frameWidth;
		}

	}
	
	public static void planeFactory() {
		
		Random rand = new Random();
		if (planeCount<8 && rand.nextInt(100)==5) {
			Plane p = new Plane(View.frameWidth,rand.nextInt((2*View.frameHeight)/4), width, height);
			 p.setImgInd(View.PlaneImg);
			Model.charArr.add(p);
			planeCount++;
		}
	}
}