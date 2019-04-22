import java.awt.Color;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends Character{
	private boolean edible;
	boolean touch;

	/**
	*This constructor sets the object to be either edible or not resembling food or pollution
	*@param type a boolean to be set to edible
	*@return nothing
	*/
	
	static Color color = new Color(210, 105, 30);
	public Prey(boolean type, int x, int y, int w, int h){
		super(x, y, color, w, h);
		edible = type; 
		touch = false;
	}

	/**
	*This function changes the position of the object
	*@param none
	*@return nothing
	*/
	public void move(){
		xPos-=10;
		if (xPos<=-10)
		{
			xPos=500;
		}
	}
	
	public boolean getEdible()
	{
		return edible;
	}
}