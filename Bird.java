
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Bird extends Character{


	private int health;
	private Direction direction;
	private boolean migrate;

	
	Bird(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		health=100;
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
	public void move(Direction d)
	{

	}

	/**
	*This function updates the health of the bird based on what is passed
	*@param change 
	*@return Nothing
	*/
	public void updateHealth(int change)
	{


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
	
}




class BirdTest {
	Bird testBird = new Bird(0,0);
	
	@Test
	void testUpdateHealth()
	{
		testBird.updateHealth(-5);
		assertEquals(95, testBird.getHealth());
	}
	
	@Test 
	void testMove()
	{
		testBird.move(Direction.EAST);
		assertEquals(1, testBird.getX());
	}	
	
	@Test 
	void testEat()
	{
		testBird.eat();
		assertEquals(10, testBird.getY());
	}	

}
