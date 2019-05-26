import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class WoodTest {
	Wood testWood = new Wood(0,0,0,0);
	
	@Test
	void testMove() {
		int temp = testWood.xPos;
		testWood.move();
		assertEquals(testWood.xPos, temp);
	}
	
	@Test
	void testFactory() {
		int temp = Wood.wCount;
		Wood.wFactory();
		assertEquals(Wood.wCount, temp++);
	}
}