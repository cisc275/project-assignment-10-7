import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ModelTest {

	Model mod = new Model(500, 300, 165, 165);
	
	@Test
	void locDirAndCollTest() {	//checks both updateLocationandDirection() and checkCollision() 
								//because updateLoc calls checkColl
		ArrayList<Character> l = new ArrayList<>();
		
		Bird b = new Bird(5,5);
		b.touch = true;
		l.add(b);
		Bird b2 = new Bird(1,5);
		b2.touch = false;
		l.add(b2);
		
		Fox f = new Fox(8,9);
		Plane p = new Plane(9,9);
		Plane p2 = new Plane(10,1);
		Prey prey = new Prey(true, 10,10);
		Prey prey2 = new Prey(false, 1, 1);
		
		l.add(f);
		l.add(p);
		l.add(p2);
		l.add(prey);
		l.add(prey2);
		
		mod.updateLocationDirection(true, l);
		int healthTest = b.getHealth();
		mod.checkCollision(f);
		mod.checkCollision(prey);
		mod.checkCollision(p);
		mod.checkCollision(prey2);
		
		assertEquals(true, mod.charArr==l);
		assertEquals(false, mod.charArr!=l);
		assertEquals(true, b.touch==false);
		assertEquals(true, b2.touch==true);
		mod.updateLocationDirection(false, l);
		assertEquals(false, mod.charArr==l);
		assertEquals(false, b.getHealth()==healthTest);
		
		mod.checkCollision(p2);
		assertEquals(true, b.touch==false);
	}
	

	
	@Test
	void setDirTest() {
		mod.setX(0);//sets bird position to collide with edge of screen
		mod.setY(0);
		mod.setDirection();//should push the bird off of the edge (at least by 1 pixel)
		assertEquals(true, mod.getX() == 0);//player bird's x position should not be on edge
		assertEquals(false, mod.getX() == 2);
		assertEquals(false, mod.getY() == 2);
		assertEquals(true, mod.getY() == 0);//player bird's y position should not be on edge
	}
	

}

