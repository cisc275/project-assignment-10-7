import java.awt.Color;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Plane extends Character{
	boolean touch;
	

	Plane(int x, int y) {
		
		super(x, y, Color.black);
		// TODO Auto-generated constructor stub
		touch = false;
	}

	/**
	*This function changes the position of the Plane to cross the view to attack
	*@param none
	*@return nothing
	*/
	
	public void move(){
		xPos -=3;

	}
}