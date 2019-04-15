import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Model {
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
					Prey p=(Prey) c;
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
		




class ModelTest {

	Model mod = new Model(500, 300, 165, 165);
	//Plane p = new Plane (0,0);
	@Test
	void updateLocDirTest() {//if the menu portion of the game is active, the player bird should not move
		//mod.updateLocationDirection(false, p);
		assertEquals(false, mod.forward);
		assertEquals(false, mod.backward);
		assertEquals(false, mod.up);
		assertEquals(false, mod.down);
	}
	
	@Test
	void checkColTest() {
		//assertEquals(false, mod.checkCollision());//bird should not be colliding with anything in the beginning of the game
	}
	
	@Test
	void setDirTest() {
		mod.setX(0);//sets bird position to collide with edge of screen
		mod.setY(0);
		mod.setDirection();//should push the bird off of the edge (at least by 1 pixel)
		assertEquals(true, mod.getX() != 0);//player bird's x position should not be on edge
		assertEquals(true, mod.getY() != 0);//player bird's y position should not be on edge
	}

}