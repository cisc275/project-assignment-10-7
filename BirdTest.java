import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BirdTest {
	Bird testBird = new Bird(0,0, 10, 10);
	
	@Test
	void testUpdateHealth()
	{
		testBird.updateHealth(-5);
		assertEquals(95, testBird.getHealth());
		testBird.updateHealth(-100);
		assertEquals(0, testBird.getHealth());
	}
	
	@Test 
	void testMoveEast()
	{
		testBird.move(Direction.EAST);
		assertEquals(5, testBird.getX());	
	}	
	
	@Test
	void testMoveWest() {
		testBird.move(Direction.WEST);
		assertEquals(0, testBird.getX());
	}
	
	@Test 
	void testMoveSouth()
	{
		testBird.move(Direction.SOUTH);
		assertEquals(5, testBird.getY());
	}
	
	@Test 
	void testMoveNorth() {
		testBird.move(Direction.NORTH);
		assertEquals(0, testBird.getY());
	}
	
	@Test
	void testMoveSE()
	{
		testBird.move(Direction.SOUTHEAST);
		assertEquals(5, testBird.getX());
		assertEquals(5, testBird.getY());
	}
	
	@Test
	void testMoveSW()
	{
		testBird.move(Direction.SOUTHWEST);
		assertEquals(0, testBird.getX());
		assertEquals(10, testBird.getY());
	}
	
	@Test 
	void testMoveNE()
	{
		testBird.move(Direction.NORTHEAST);
		assertEquals(5, testBird.getX());
		assertEquals(5, testBird.getY());
	}
	
	@Test 
	void testMoveNW()
	{
		testBird.move(Direction.NORTHWEST);
		assertEquals(0, testBird.getX());
		assertEquals(0, testBird.getY());
	}
	
	@Test 
	void testEat()
	{
		testBird.eat();
		assertEquals(10, testBird.getY());
	}	
	
	@Test 
	void testGetDirec()
	{
		assertEquals(Direction.EAST, testBird.getDirec());
	}
	
	@Test
	void testKDW()
	{
		assertEquals(Direction.WEST, testBird.keyToDirec(37));
	}
	@Test
	void testKDS()
	{
		assertEquals(Direction.SOUTH, testBird.keyToDirec(40));
	}
	@Test
	void testKDE()
	{
		assertEquals(Direction.EAST, testBird.keyToDirec(39));
	}
	@Test
	void testKDN()
	{
		assertEquals(Direction.NORTH, testBird.keyToDirec(38));
	}
	
}