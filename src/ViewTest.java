import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class ViewTest {
	
	

	@Test
	public void update() {
		
		
		String x = "north";
		String y = "south";
		
		View test = new View();
		test.update(10, 10, y, true);
		assertEquals (x, y);
		
	}


}
