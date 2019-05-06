import java.awt.Color;

public class AutoCharacters extends Character{
	// Helps with removing
	boolean collided = false;
	int flip = 1;

	AutoCharacters(int x, int y, int w, int h, Color color){
		super(x, y, color, w, h);
	}
	
	/**
	*This function changes the position of the Plane to cross the view to attack
	*@param none
	*@return nothing
	*/
	public void move(){
		xPos -=10;
		if (xPos<=0)
		{
			xPos=View.frameWidth;
		}

	}
}