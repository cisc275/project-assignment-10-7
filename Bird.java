
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class Bird extends Character{
	private int health;
	private Direction direction;


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


	}
	
	public static void main(String[] args)
	{
		
	}
}




class BirdTest {
	Bird testBird = new Bird();
	
	@Test
	public void testMoveLeft() {
		testBird.setxPosition(5);
		testBird.moveLeft();
		assertEquals(4, testBird.getxPosition());
	}
	
	@Test
	public void testMoveRight() {
		testBird.setxPosition(8);
		testBird.moveRight();
		assertEquals(9, testBird.getxPosition());
	}
	
	@Test
	public void testMoveUp() {
		testBird.setYPosition(5);
		testBird.moveUp();
		assertEquals(4, testBird.getxPosition());
	}
	
	@Test
	public void testMoveDown() {
		testBird.setYPosition(5);
		testBird.moveDown();
		assertEquals(6, testBird.getxPosition());
	}
}
