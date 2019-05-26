import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;


public class FoxTest {
	Fox testFox = new Fox(0,0,0,0);
	
	@Test
	void testinit() {
		assertEquals(testFox.imgArrNum, View.FoxFwd);
	}
	
	@Test
	void testMove() {
		testFox.xPos = 1280;
		int temp = testFox.xPos;
		testFox.flip = 1;
		testFox.move();
		assertEquals(testFox.xPos, temp-Fox.foxSpeed);
		assertEquals(testFox.imgArrNum, View.FoxFwd);
	}
	
	@Test
	void testMove2() {
		int temp = testFox.xPos;
		testFox.flip = 2;
		testFox.move();
		assertEquals(testFox.xPos, temp+Fox.foxSpeed);
		assertEquals(testFox.imgArrNum, View.FoxBck);
	}
	
	@Test
	void testMoveDefault() {
		int temp = testFox.xPos;
		testFox.flip = 0;
		testFox.move();
		assertEquals(testFox.xPos, temp+Fox.foxSpeed);
		assertEquals(testFox.imgArrNum, View.FoxBck);
	}
	
	@Test
	void testBorder() {
		testFox.xPos = -100;
		testFox.checkBorder();
		assertEquals(testFox.flip, 2);
	}
	
	@Test
	void testBorder2() {
		testFox.xPos = 1280;
		testFox.checkBorder();
		assertEquals(testFox.flip, 1);
	}
	
	@Test
	void testSetSpeed() {
		Fox.setSpeed(10);
		assertEquals(Fox.foxSpeed, 10);
	}
	
	@Test
	void testGetSpeed() {
		Fox.setSpeed(10);
		int temp = Fox.getSpeed();
		assertEquals(temp, 10);
	}
	
	@Test
	void testAddFox() {
		Fox.addFox();
		assertEquals(Fox.foxCount, 0);
	}
	
}
