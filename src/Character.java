
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Character{
	private int xPos;
	private int yPos;

	/**
	*This constructor sets the initial position of the object as passed
	*@param x an int to represent starting x position 
	 @param y an int to represent starting y position
	 @return nothing 
	*/
	Character(int x, int y)
	{
		xPos = x;
		yPos=y;

	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}

}