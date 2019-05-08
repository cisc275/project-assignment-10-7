import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends AutoCharacters implements Movers{
	static int preyCount=0;
	static int width = 50;
	static int height = 50;

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
		if (preyCount<10 && rand.nextInt(100)==5) {
			Prey cre = new Prey(View.frameWidth,((2 * View.frameHeight)/3)+50, width, height);
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
		xPos -=10;
		if (xPos<=0-200)
		{
			xPos=View.frameWidth;
		}

	}
	
}