import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class PollutionTest {
	Pollution testP = new Pollution(0,0,0,0);
	
	@Test
	void testMove() {
		int temp = testP.xPos;
		testP.move();
		assertEquals(testP.xPos, temp-5);
	}
	
	@Test
	void testFactory() {
		int temp = Pollution.pCount;
		Pollution.pFactory();
		assertEquals(Pollution.pCount, temp++);
	}
}
