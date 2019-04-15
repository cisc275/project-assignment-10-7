
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class Bird extends Character{


	private int health;
	private Direction direction;
	private boolean migrate;
	int yIncr=5;
	int xIncr=5;
	static Color color = new Color(0, 0, 255);

	
	Bird(int x, int y, int w, int h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
		health=100;
		direction = Direction.EAST;
	}

	/** 
	*This function handles the movement and health of the bird when it dives to eat
	*@param Nothing
	*@return Nothing
	*/
	public void eat()
	{
		

	}

	/**
	*This function updates the position of the bird
	*@param d
	*@return Nothing 
	*/
	public void move(int e)
	{
		//System.out.println("d");
		direction =keyToDirec(e);
		switch(direction){
		case NORTH: //north
			yPos-=yIncr;

			break;
		
		case NORTHEAST: //north east
			xPos+=xIncr; 
			yPos-=yIncr;
			break;
			
		case EAST: //east
			xPos+=xIncr;

			break;
			
		case SOUTHEAST: //south east
			xPos+=xIncr;
			yPos+=yIncr;

			break;
			
		case SOUTH: //south
			yPos+=yIncr;

			break;
	
		case SOUTHWEST: //south west
			xPos-=xIncr;
			yPos+=yIncr;

			break;
			
		case WEST: //west
			xPos-=xIncr;
			break;
			
		case NORTHWEST: //north west
			yPos-=yIncr;
			xPos-=xIncr;
			break;
	}
	

	}
	
	private Direction keyToDirec(int d) {
		if (d == 37)
		{
			return Direction.WEST;
		}
		else if (d == 38)
		{
			return Direction.NORTH;
		}
		else if(d == 39)
		{
			return Direction.EAST;
		}
		else 
		{
			return Direction.SOUTH;
		}
	}

	/**
	*This function updates the health of the bird based on what is passed
	*@param change 
	*@return Nothing
	*/
	public void updateHealth(int change)
	{
		if (health >=0)
			health +=change;
	}

	/**
	*This function returns the health value
	*@param Nothing
	*@return the amount of health the bird has  
	*/
	public int getHealth()
	{
		return health;
	}
	
	public Direction getDirec() {
		return direction;
	}

	
}




class BirdTest {
	Bird testBird = new Bird(0,0, 10, 10);
	
	@Test
	void testUpdateHealth()
	{
		testBird.updateHealth(-5);
		assertEquals(95, testBird.getHealth());
	}
	
	@Test 
	void testMove()
	{
		testBird.move(37);
		assertEquals(1, testBird.getX());
	}	
	
	@Test 
	void testEat()
	{
		testBird.eat();
		assertEquals(10, testBird.getY());
	}	

}
