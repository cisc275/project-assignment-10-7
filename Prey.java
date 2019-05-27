import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends Character implements Movers{
	static int preyCount=0;
	static int width = 50;
	static int height = 30;
	static int preySpeed = 10;

	/**
	*This constructor sets the object to be either edible or not resembling food or pollution
	*@param type a boolean to be set to edible
	*@return nothing
	*/
	
	public Prey(int x, int y, int w, int h){
		super(x, y, w, h);

	
	}
	
	/**
	 * This function creates prey but limits when the the constructor can be called
	 * @param preyInd
	 * @return nothing
	 */
	public static void preyFactory(int preyInd) {
		
		Random rand = new Random();
		if (preyCount<Model.preyLimit && rand.nextInt(100)==5) {
			Prey cre = new Prey(View.frameWidth, rand.nextInt((View.frameHeight - View.frameHeight/10) - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3) - height, width, height);
			cre.setImgInd(preyInd);
			Model.charArr.add(cre);
			preyCount++;
		}
	}

	/**
	*This function changes the position of the Plane to cross the view to attack
	*@param none
	*@return nothing
	*/
	public void move(){
		xPos -=(View.frameWidth/128);
		if (xPos<=0 - super.width)
		{
			xPos=View.frameWidth;
		}

	}
	
	/**
	 * This function sets the speed that the prey move
	 * @param x
	 * @return nothing
	 */
	public static void setSpeed(int x) {
		preySpeed = x;
	}
	/**
	 * This function returns the speed the prey are moving at
	 * @param nothing
	 * @return preySpeed
	 */
	
	public static int getSpeed() {
		return preySpeed;
	}
	
}