import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	Model mod = new Model(500, 300, 165, 165);
	
	
//	@Test
//	void updateLocDirTest() {//if the menu portion of the game is active, the player bird should not move
//		mod.updateLocationDirection(false);
//		assertEquals(false, mod.forward);
//		assertEquals(false, mod.backward);
//		assertEquals(false, mod.up);
//		assertEquals(false, mod.down);
//	}
	
//	@Test
//	void checkColTest() {
//		assertEquals(false, mod.checkCollision());//bird should not be colliding with anything in the beginning of the game
//	}
	
	@Test
	void setDirTest() {
		//mod.setX(0);//sets bird position to collide with edge of screen
		//mod.setY(0);
		//mod.setDirection();//should push the bird off of the edge (at least by 1 pixel)
		//assertEquals(true, mod.getX() != 0);//player bird's x position should not be on edge
		//assertEquals(true, mod.getY() != 0);//player bird's y position should not be on edge
	}

}

