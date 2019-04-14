
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Prey extends Character{
	private boolean edible;

	/**
	*This constructor sets the object to be either edible or not resembling food or pollution
	*@param type a boolean to be set to edible
	*@return nothing
	*/
	public Prey(boolean type, int x, int y){
		super(x, y);
		edible = type; 
	}

	/**
	*This function changes the position of the object
	*@param none
	*@return nothing
	*/
	public void move(){

	}
	
	public boolean getEdible()
	{
		return edible;
	}
}