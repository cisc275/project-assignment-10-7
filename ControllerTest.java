import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class ControllerTest {

	Controller c = new Controller();
	
	@Test
	void testController() {
		assertEquals(0, c.tutorial);
	}
	
	@Test
	void testStart() {
		c.run=true;
		c.tutorial=-1;
		c.start();
		assertEquals(true, c.timerStop);
	}
	
	@Test
	void testSwitchStates()
	{
		c.switchStates();
		assertEquals(1, Controller.gameStage);
	}
	
	@Test
	void testSwitchStates1()
	{
		Controller.gameStage=1;
		c.switchStates();
		assertEquals(2, Controller.gameStage);
	}
	
	@Test
	void testSwitchStates2()
	{
		Controller.gameStage=2;
		c.switchStates();
		assertEquals(3, Controller.gameStage);
	}
	
	@Test
	void testSwitchStates3()
	{
		Controller.gameStage=3;
		c.switchStates();
		assertEquals(4, Controller.gameStage);
	}
	
	@Test
	void testSwitchStates4()
	{
		Controller.gameStage=4;
		c.switchStates();
		assertEquals(4, Controller.gameStage);
	}
	
	@Test
	void testSwitchStates5()
	{
		Controller.gameStage=4;
		c.model.answered=true;
		c.model.question=6;
		c.switchStates();
		assertEquals(5, Controller.gameStage);
	}
	
	@Test 
	void testdeserialize() {
		c.deserialize();
		assertEquals(true, c.playerArr.size()>0);
	}
	

}
