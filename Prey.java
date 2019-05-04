import java.awt.Color;
import java.util.Random;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends AutoCharacters{
	private boolean edible;
	static int preyCount=0;

	/**
	*This constructor sets the object to be either edible or not resembling food or pollution
	*@param type a boolean to be set to edible
	*@return nothing
	*/
	
	static Color color = new Color(210, 105, 30);
	public Prey(boolean type, int x, int y, int w, int h){
		super(x, y, w, h, color);
		edible = type; 
		//preyCount++;
		
	}
	
	public static void preyFactory() {
		
		Random rand = new Random();
		if (preyCount<10 && rand.nextInt(100)==5) {
			Prey cre = new Prey(true, View.frameWidth,((2 * View.frameHeight)/3), 25, 25);
			Model.charArr.add(cre);
			preyCount++;
		}
	}

	/**
	*This function changes the position of the object
	*@param none
	*@return nothing
	*/
//	public void move(){
//		xPos-=3;
//	}
//	
	public boolean getEdible()
	{
		return edible;
	}
	
	public static void removePrey(AutoCharacters c) {
		Model.charArr.remove(c);
}
	
}