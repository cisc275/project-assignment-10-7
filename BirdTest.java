import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class BirdTest { 
	Bird testBird = new Bird(0,0);
	Bird blankTestBird = new Bird();
	
	@Test
	void testUpdateHealth()
	{
		testBird.updateHealth(-5);
		assertEquals(995, testBird.getHealth());
	}
	
	@Test
	void testHealth0() {
		testBird.updateHealth(0);
		assertEquals(1000, testBird.getHealth());
	}
	
	@Test
	void testHealthLow() {
		testBird.updateHealth(-10000);
		assertEquals(0, testBird.getHealth());
	}
	
	@Test
	void testHealthHigh() {
		testBird.updateHealth(10000);
		assertEquals(Bird.maxHealth, testBird.getHealth());
	}
	
	
	@Test 
	void testMovec1()
	{
		testBird.xVector = 1;
		testBird.xPos = 100000;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos, testBird.getX());
	}	
	
	@Test 
	void testMovec2()
	{
		testBird.xVector = -1;
		testBird.xPos = 100000;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos+testBird.xVector, testBird.getX());
	}
	
	@Test 
	void testMovec3()
	{
		testBird.xVector = 1;
		testBird.xPos = 50;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos+testBird.xVector, testBird.getX());
	}
	
	@Test 
	void testMovec4()
	{
		testBird.xVector = -1;
		testBird.xPos = 50;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos+testBird.xVector, testBird.getX());
	}
	
	@Test 
	void testMovec5()
	{
		testBird.xVector = 1;
		testBird.xPos = -50;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos+testBird.xVector, testBird.getX());
	}
	
	@Test 
	void testMovec6()
	{
		testBird.xVector = -1;
		testBird.xPos = -50;
		int tempXPos = testBird.xPos;
		testBird.move();
		assertEquals(tempXPos, testBird.getX());
	}
	
	@Test
	void testSetWest() {
		testBird.setDirec(Direction.WEST);
		assertEquals(Direction.WEST, testBird.getDirec());
	}
	
	@Test
	void testSetMig() {
		testBird.setMigrate(true);
		assertEquals(true, testBird.getMigrate());
	}
	
	@Test 
	void testEatc1()
	{
		testBird.risefall = 1;
		testBird.eat();
		assertEquals(View.frameHeight/80, testBird.yVector);
		
	}	
	
	@Test 
	void testEatc2()
	{
		testBird.risefall = 2;
		testBird.eat();
		assertEquals(-(View.frameHeight/80), testBird.yVector);
		
	}
	
	@Test 
	void testEatDefault()
	{
		int tempY = testBird.yVector;
		testBird.risefall = 3;
		testBird.eat();
		assertEquals(tempY, testBird.yVector);
		
	}
	
	@Test 
	void testGetDirec()
	{
		assertEquals(Direction.EAST, testBird.getDirec());
	}
	
	@Test
	void testKDW()
	{
		testBird.keyToDirec(37);
		assertEquals(Direction.WEST, testBird.getDirec());
	}
	
	@Test
	void testKDE()
	{
		testBird.keyToDirec(39);
		assertEquals(Direction.EAST, testBird.getDirec());
	}
	
	@Test
	void testKD0()
	{
		testBird.keyToDirec(0);
		assertEquals(0, testBird.xVector);
	}
	
	@Test
	void testLeftBorder() {
		testBird.xPos = -2;
		boolean temp = testBird.checkLeftBorder();
		assertEquals(false, temp);
	}
	
	@Test
	void testRightBorder() {
		testBird.xPos = 100000;
		boolean temp = testBird.checkRightBorder();
		assertEquals(false, temp);
	}
	@Test
	void testRightBorder2() {
		testBird.xPos = 10;
		boolean temp = testBird.checkRightBorder();
		assertEquals(true, temp);
	}
	
	@Test
	void testBottomBorder() {
		testBird.yPos = 1000000;
		boolean temp = testBird.checkBottomBorder();
		assertEquals(false, temp);
	}
	@Test
	void testBottomBorder2() {
		testBird.yPos = 10;
		boolean temp = testBird.checkBottomBorder();
		assertEquals(true, temp);
	}
	
	@Test
	void testTopBorder() {
		testBird.yPos = -11;
		boolean temp = testBird.checkTopBorder();
		assertEquals(false, temp);
	}
	

	@Test
	void testImg() {
		testBird.hurt = 1;
		int tempHurt = 1;
		testBird.setMigrate(true);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigFwdRed);	
		assertEquals(testBird.hurt, tempHurt-1);
	}
	
	@Test
	void testImgW(){
		testBird.hurt = 1;
		int tempHurt = 1;
		testBird.setMigrate(true);
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigBckRed);	
		assertEquals(testBird.hurt, tempHurt-1);
	}
	
	//hurt > 0, nonmig, east, == View.NonMigFwdRed & hurt--
	@Test
	void testImgNM() {
		testBird.hurt = 1;
		int tempHurt = 1;
		testBird.setMigrate(false);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigFwdRed);	
		assertEquals(testBird.hurt, tempHurt-1);
	}
	
	@Test 
	void testImgNMW() {
		testBird.hurt = 1;
		int tempHurt = 1;
		testBird.setMigrate(false);
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigBckRed);	
		assertEquals(testBird.hurt, tempHurt-1);
	}
	

	@Test
	void testImgc2() {
		testBird.hurt = -1;
		int tempHurt = -1;
		testBird.setMigrate(true);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigFwdGreen);	
		assertEquals(testBird.hurt, tempHurt+1);
	}
	
	@Test 
	void testImgc2W() {
		testBird.hurt = -1;
		int tempHurt = -1;
		testBird.setMigrate(true);
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigBckGreen);	
		assertEquals(testBird.hurt, tempHurt+1);
	}
	

	@Test
	void testImgc2NM() {
		testBird.hurt = -1;
		int tempHurt = -1;
		testBird.setMigrate(false);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigFwdGreen);	
		assertEquals(testBird.hurt, tempHurt+1);
		
	}
	
	@Test
	void testImgc2NMW() {
		testBird.hurt = -1;
		int tempHurt = -1;
		testBird.setMigrate(false);
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigBckGreen);	
		assertEquals(testBird.hurt, tempHurt+1);
	}
	
	@Test
	void testImgc3() {
		testBird.hurt = 0;
		int tempHurt = 0;
		testBird.setMigrate(true);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigFwd);	
		assertEquals(testBird.hurt, tempHurt);
		
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.MigBck);	
		assertEquals(testBird.hurt, tempHurt);
	}
	

	@Test
	void testImgc3NM() {
		testBird.hurt = 0;
		int tempHurt = 0;
		testBird.setMigrate(false);
		testBird.setDirec(Direction.EAST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigFwd);	
		assertEquals(testBird.hurt, tempHurt);
		
		testBird.setDirec(Direction.WEST);
		testBird.checkImage();
		assertEquals(testBird.imgInd, View.NonMigBck);	
		assertEquals(testBird.hurt, tempHurt);
	}
	
	
	
	@Test
	void testYVec() {
		testBird.yPos = 100000;
		testBird.setYVec(1);
		assertEquals(0, testBird.yVector);
	}
	
	@Test
	void testYVec2() {
		testBird.yPos = -20;
		testBird.setYVec(-1);
		assertEquals(0, testBird.yVector);
	}
	
	@Test
	void testMoveAnimate() {
		testBird.moveAnimate(true);
		assertEquals(5, testBird.xVector);
		assertEquals(View.frameHeight/100, testBird.yVector);
	}
	
	@Test
	void testMoveAnimate2() {
		testBird.moveAnimate(false);
		assertEquals(-3, testBird.yVector);
		assertEquals(View.frameWidth/60, testBird.xVector);
	}
}

