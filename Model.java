import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Timer;

import org.junit.jupiter.api.*;

public class Model{
	private int x;
	private int y;
	int xBound;
	int yBound;
	boolean forward;
	boolean up;
	boolean backward;
	boolean down;
	int yIncr=1;
	int xIncr=1;
	Direction direction;
	ArrayList<Character> charArr;
	int colBound;
	
	public Model(int w, int h, int imgW, int imgH) {
		//creates model
		xBound = w-imgW;
		yBound = h-imgH;
		colBound=imgW;
		//touch = false;
	}
	
	/**
	 * will determine if the user is in a menu or in a moment of gameplay
	 *  During gameplay, this will change increment of the player model speed
	 *   using switch cases based on the direction enumeration. When in a menu, this 
	 *   will stop updating the player model
	 *   
	 * @param boolean -- true if in gameplay, false if in menu
	 * @return nothing
	 * 
	 */
	public void updateLocationDirection(boolean run, ArrayList<Character> cA) {
		charArr = cA;
		Timer t = new Timer();
		
		
		if(run) {
			for(Character c : charArr)
			{
				if (c.getClass()== Plane.class)
				{
					Plane p=(Plane) c;
					p.move();
					checkCollision(p);
					
				}
				else if (c.getClass()== Prey.class)
				{
					Prey p= (Prey) c;
					p.move();
					checkCollision(p);
				}
				else if (c.getClass()==Bird.class)
				{
					if (c.touch) {
						Bird b= (Bird) c;
						b.updateHealth(-1);
						c.touch=false;
					}
					else
					{
						c.touch=true;
					}
				}
				
			}
		} 
		else {
			
		}
	}
	
	public void run() {
		
	}
	
	/**
	 * @param none
	 * @return int -- the player bird's x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param none
	 * @return int -- the player bird's y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param int -- this value will become the player bird's x value
	 * @return nothing
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param int -- this value will become the player bird's y value
	 * @return nothing
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**will check collision with the edges of the frame, predators, prey, and obstacles
	 *and will change flags accordingly 
	 * @param none
	 * @return boolean -- will return true if there is collision, false otherwise
	 */
	public void checkCollision(Character c) 
	{
		
		if (c.getBounds().intersects(charArr.get(0).getBounds()))
		{
			if (!c.touch)
			{
				if(c.getClass()==Plane.class)
				{
					System.out.println(c.touch);
					((Bird)charArr.get(0)).updateHealth(-5);
				}
				else
				{
					((Bird)charArr.get(0)).updateHealth(5);
				}
				c.touch=true;
			}
		}
		else 
		{
			c.touch=false;
		}
		
	}
	
	/**
	 * @param none
	 * @return nothing
	 * this will receive keyboard information to determine which direction is
	 * stored in the direction variable of model
	 */ 
	public void setDirection() {
		
	}
	
}
		