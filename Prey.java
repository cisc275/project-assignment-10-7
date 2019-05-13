import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends AutoCharacters implements Movers{
	static int preyCount=0;
	static int width = 50;
	static int height = 50;
	static int preySpeed = 10;

	/**
	*This constructor sets the object to be either edible or not resembling food or pollution
	*@param type a boolean to be set to edible
	*@return nothing
	*/
	
	public Prey(int x, int y, int w, int h){
		super(x, y, w, h);

	
	}
	
	public static void preyFactory(int preyInd) {
		
		Random rand = new Random();
		if (preyCount<Model.preyLimit && rand.nextInt(100)==5) {
			Prey cre = new Prey(View.frameWidth, rand.nextInt(View.frameHeight - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3), width, height);
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
	
	public static void setSpeed(int x) {
		preySpeed = x;
	}
	
	public static int getSpeed() {
		return preySpeed;
	}
	
}