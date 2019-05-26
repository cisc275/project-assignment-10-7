import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;
 
public class CharacterTest {
	Character testChar = new Character(0,0,0,0);
	Character blankTestChar = new Character();
	
	@Test 
	void testx() {
		int temp = testChar.getX();
		assertEquals(temp, 0);
	}
	
	@Test 
	void testY() {
		int temp = testChar.getY();
		assertEquals(temp, 0);
	}
	
	@Test
	void testImgInd() {
		testChar.setImgInd(0);
		assertEquals(testChar.imgArrNum, 0);
	}
	
	@Test
	void testBounds() {
		Rectangle temp = testChar.getBounds();
		assertEquals(temp.x, 0);
	}
}
