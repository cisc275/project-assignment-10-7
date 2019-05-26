import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;


public class PreyTest {
	Prey testPrey = new Prey(0,0,0,0);
	
	
	@Test
	void testMove() {
		int temp = testPrey.xPos;
		testPrey.move();
		assertEquals(testPrey.xPos, View.frameWidth);
	}
	
	
	@Test
	void testSetSpeed() {
		Prey.setSpeed(10);
		assertEquals(Prey.preySpeed, 10);
	}
	
	@Test
	void testGetSpeed() {
		Prey.setSpeed(10);
		int temp = Prey.getSpeed();
		assertEquals(temp, 10);
	}
	
	@Test
	void testPreyFactory() {
		Prey.preyFactory(2);
		assertEquals(Prey.preyCount, 0);
	}
	
}
