import java.awt.Color;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Plane extends Character{
	static Color color = new Color(0, 0, 0);
	

	Plane(int x, int y) {
		
		super(x, y, color);
		// TODO Auto-generated constructor stub
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