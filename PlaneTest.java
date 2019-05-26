import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class PlaneTest {
	Plane testPlane = new Plane(0, 0, 0, 0);
	
	@Test
	void testMove() {
		testPlane.move();
		assertEquals(testPlane.xPos, View.frameWidth);
	}
	
	@Test
	void testMove2() {
		testPlane.xPos = -10000;
		testPlane.move();
		assertEquals(testPlane.xPos, View.frameWidth);
	}
	
	@Test
	void testFactory() {
		Plane.planeFactory();
		assertEquals(testPlane.planeCount, 0);
	}
	
}
