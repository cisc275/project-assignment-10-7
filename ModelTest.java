import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	Model mod = new Model(500, 300);
	
	@Test
	void testGetPlayer()
	{
		assertEquals(Bird.class, mod.getPlayer().getClass());
	}
	
	@Test
	void testRestart()
	{
		Model.restart();
		assertEquals(0, Model.score);
	}
	
	@Test
	void testNextQuestion() {
		mod.answered=true;
		mod.nextQuestion();
		assertEquals(1, mod.question);
	}
	
	@Test
	void testCheckQuiz() {
		
		assertEquals(true, mod.checkQuiz(2));
	}
	
	@Test
	void testCheckQuiz1() {
		
		assertEquals(false, mod.checkQuiz(1));
	}
	
	@Test 
	void testCheckCollisionPoll() {
		Pollution p = new Pollution(100,0, Pollution.width, Pollution.height);
		assertEquals(true, mod.checkCollision(p));
	}
	
	@Test 
	void testCheckCollisionPrey() {
		Prey p = new Prey(100,0, Prey.width, Prey.height);
		assertEquals(true, mod.checkCollision(p));
	}
	
	@Test 
	void testCheckCollisionWood() {
		Wood w = new Wood(100,0, Wood.width, Wood.height);
		assertEquals(true, mod.checkCollision(w));
	}
	
	@Test 
	void testCheckCollisionPredator() {
		Fox f = new Fox(100,0, Fox.width, Fox.height);
		assertEquals(false, mod.checkCollision(f));
	}
	
	@Test 
	void testCheckCollisionPredator2() {
		Fox f = new Fox(300,0, Fox.width, Fox.height);
		assertEquals(false, mod.checkCollision(f));
	}
	
	@Test
	void testSwitchGame()
	{
		Bird newp = mod.getPlayer();
		mod.switchGame();
		assertEquals(false, mod.getPlayer().equals(newp));
	}
	
	@Test
	void testAnimation() {
		assertEquals(true, mod.animation());
	}
	
	@Test
	void testAnimation2() {
		mod.getPlayer().xPos=View.frameWidth+2*Bird.width;
		assertEquals(false, mod.animation());
	}
	
	@Test
	void testUpdateTutorial1() {
		
		assertEquals(false, mod.updateTutorial(true, 2));
	}
	
	@Test
	void testUpdateTutorial2() {
		
		assertEquals(false, mod.updateTutorial(true, 3));
	}
	
	@Test
	void testUpdateTutorial3() {
		
		assertEquals(false, mod.updateTutorial(true, 4));
	}
	
	@Test
	void testUpdateTutorial4() {
		mod.eatFlag=true;
		mod.pause=55;
		mod.getPlayer().xPos=View.frameWidth/2;
		mod.getPlayer().yPos=2*View.frameHeight/3;
		assertEquals(true, mod.updateTutorial(true, 2));
		
	}
	
	@Test
	void testUpdateLocDir() {
		mod.eatFlag=true;
		mod.game=true;
		mod.updateLocationDirection(true);
		assertEquals(1,Plane.planeCount);
	}
	
	@Test
	void testFoxLimit() {
		Model.score=400;
		mod.updateLocationDirection(true);
		assertEquals(2, Model.foxLimit);
	}
	
	@Test
	void testUpdateCollision() {
		Prey p = new Prey(100, 0, Prey.width,Prey.height);
		Prey.preyCount=1;
		Model.charArr.add(p);
		mod.updateLocationDirection(true);
		assertEquals(0, Prey.preyCount);
	}
	
	@Test
	void testUpdateHelpFlag()
	{
		mod.helpFlag=true;
		mod.updateLocationDirection(true);
		assertEquals(15, Model.preyLimit);
	}
	
	@Test
	void testUpdateHelp()
	{
		mod.getPlayer().updateHealth(-500);
		mod.updateLocationDirection(true);
		assertEquals(18, Model.preyLimit);
	}
	
	
	
	

}

