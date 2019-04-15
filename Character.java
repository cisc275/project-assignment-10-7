import java.awt.Color;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Character{
	protected int xPos;
	protected int yPos;
	Color color;
	boolean touch;

	/**
	*This constructor sets the initial position of the object as passed
	*@param x an int to represent starting x position 
	 @param y an int to represent starting y position
	 @return nothing 
	*/
	Character(int x, int y, Color c)
	{
		xPos = x;
		yPos=y;
		color = c;
		touch = false;

	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	

}