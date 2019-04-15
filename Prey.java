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
	public Prey(boolean type, int x, int y){
		super(x, y, color);
		edible = type; 
		touch = false;
	}

	/**
	*This function changes the position of the object
	*@param none
	*@return nothing
	*/
	public void move(){
		xPos-=3;
	}
	
	public boolean getEdible()
	{
		return edible;
	}
}