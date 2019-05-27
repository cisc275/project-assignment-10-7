import java.util.ArrayList;
import java.util.Iterator;



public class Model{


	int SCOREINC = 100; //amount to increase score
	int SCOREDCR = 25; //amount to decrease score
	static boolean correctQuiz =  false; //lets view know if answer is right
	int foxMax = 4; //max amount of foxes on screen
	Direction direction;
	static ArrayList<Movers> charArr;
	Bird player; 
	static int score = 0; //score for the game
	static int foxLimit = 1; //current limit for foxes
	static int preyLimit = 15; //current limit for preys
	static int pollLimit = 5; //current limit for poll
	static int woodLimit = 20; //current limit for woodlimit
	
	boolean game=false; //whether first game or second
	
	//Variables for Eat
	boolean eatFlag = false; //whether bird is going down
	boolean helpFlag = false; //increase amount of prey 
	
	int [] ansArr = {2,1,4,3,2,1,3};
	int question=0;
	
	boolean answered = false;
	
	

	
	
	public Model(int w, int h) {
		//creates model
		charArr=new ArrayList<>();
		player = new Bird(100,0);		
		score = 0;
	}
	
	/**
	 * will determine if the user is in a menu or in a moment of gameplay
	 *  During gameplay, this will change increment of the player model speed
	 *   using switch cases based on the direction enumeration. When in a menu, this 
	 *   will stop updating the player model
	 *   
	 * @param boolean -- true if in gameplay, false if in menu
	 * @return nothing
	 * 
	 */
	public void updateLocationDirection(boolean run) {
		if(run)
		{
			Pollution.pFactory(); //create pollution
			if(game) { //set the prey and predators in the second game
				pollLimit = 10;
				Prey.preyFactory(View.Fish);
				Plane.planeFactory();
				Pollution.pFactory();
			}
			else
			{ //set the prey and predators in the first game
				Prey.preyFactory(View.Mouse);
				Fox.addFox();
				Wood.wFactory();
			}
			 
			Iterator<Movers> i = charArr.iterator(); //go through auto characters
			while(i.hasNext())
			{
				Movers c;
				c = i.next();
				c.move();
				if (checkCollision((Character)c)) //check to see if they hit the bird
				{ 
					i.remove(); //make the prey disappear
					Prey.preyCount--;
					Pollution.pCount--;
				
					}
				}
			
			if(score % 400 == 0 && score != 0) { //if the player is doing well
				//Will increase foxes up to the max; 
				if(foxLimit != foxMax) {
					// System.out.println("1 Fox was added");
					foxLimit++;
				}
				else {
					// System.out.println("Maximum foxes reached");
				}
			}
			
			// If player health falls below half, Foxes' speed is reduced
			if(player.getHealth() <= 500) {
				helpFlag = true;
				//System.out.println("Helping Player");
				Fox.setSpeed(8);
				Prey.setSpeed(5);
				preyLimit += 3;
				woodLimit += 2;
				}
			else {
				//Will reset the limits
				if(helpFlag) {
					preyLimit = 15;
					woodLimit = 20;
					helpFlag = false; 
					//System.out.println("Limits reset");
				}
				//Will reset the speed 
				Fox.setSpeed(15);
				Prey.setSpeed(10);
				//System.out.println("Speeds reset");
			}
			
			}
			player.move(); //move the bird
			
			if(eatFlag)  //move the bird down
			{
				player.eat();
			}
			player.updateHealth(-1);
		}
	
	int pause = 0;
	
	/**
	 * will determine if the user is in a menu or in a moment of gameplay
	 *  During gameplay, this will change increment of the player model speed
	 *   using switch cases based on the direction enumeration. When in a menu, this 
	 *   will stop updating the player model
	 *   
	 * @param boolean -- true if in gameplay, false if in menu, int for which part of the tutorial its on
	 * @return boolean to tell whether they've completed the section
	 * 
	 **/
	public boolean updateTutorial(boolean run, int moduleStage) {

		boolean check = false; //whether they have complete the section
		if (run)
		{
			switch(moduleStage) {
			case 2: //have to hit a prey to continue
				Prey p = new Prey(View.frameWidth/2, 2*View.frameHeight/3, Prey.width, Prey.height);
				p.setImgInd(View.Mouse);
				charArr.add(p);
				Prey.preyCount++;
				break;
			case 3: //have to hit wood to continue
				Wood w =new Wood(View.frameWidth/2, 2*View.frameHeight/3, Wood.width, Wood.height);
				w.setImgInd(View.Twig);
				charArr.add(w);
				Wood.wCount++;
				break;
			case 4: //have to hit the pollution to continue
				Fox f = new Fox(View.frameWidth/4, 2*View.frameHeight/3, Fox.width, Fox.height);
				f.setImgInd(View.FoxFwd);
				charArr.add(f);
				Pollution pol = new Pollution(2*View.frameWidth/3, 2*View.frameHeight/3, Pollution.width, Pollution.height);
				pol.setImgInd(View.Trash);
				charArr.add(pol);
				Pollution.pCount++;
				break;
			}
			
		}
		
		Iterator<Movers> i = charArr.iterator();
		while(i.hasNext())
		{
			Movers c;
			c = i.next();
			//c.move();
			if(pause>50)
			{
				if (checkCollision((Character)c))
				{
					i.remove();
					Prey.preyCount--;
					check = true;
					pause = 0;
				}
			}
		}
		
		player.move();
		
		if(eatFlag) 
		{
			player.eat();
		}
		pause++;

		return check;
		
	}

	/**
	 * handles the movement of the bird at the end of the game
	 * @param - nothing
	 * @return - boolean whether it has finished the animation or not
	 */
	public boolean animation()
	{	player.setDirec(Direction.EAST);
		player.moveAnimate(player.getHealth()==0); //call the move function for the bird
		
		//check whether it is off screen to end the animation
		if(player.getX()>(View.frameWidth+Bird.width) || player.getY()>(View.frameHeight+Bird.height))
			return false;
		return true;
	}
	
	/**
	 * this function resets the fields needed to start the second game
	 * @param - nothing
	 * @return - nothing
	 */
	public void switchGame() {
		
		player=new Bird(View.frameWidth/4, View.frameHeight/3);//new player
		player.setMigrate(true); //switch the bird style
		eatFlag = false;//turn off eating
		player.risefall=2; //reset to up motion
		game = true; //turn to second game 
		Model.charArr = new ArrayList<>(); //clear all auto characters
		Prey.preyCount = 0; //reset the prey and pollution
		Pollution.pCount = 0;
		pollLimit = 15;
		
	}

	/**will check collision with the edges of the frame, predators, prey, and obstacles
	 *and will change flags accordingly 
	 * @param none
	 * @return boolean -- will return true if there is collision, false otherwise
	 */
	public boolean checkCollision(Character c) 
	{
		if (c.getBounds().intersects(player.getBounds())) //check for interection
		{
			if (!c.touch) //make sure they weren't already touching
			{
				if(c.getClass()==Prey.class)
				{
					if(player.hurt == 0) {
						player.hurt = -20;//switch to green images
					}
					player.updateHealth(100);
					return true; //returns that there was a collision
				}
				else if(c.getClass()==Wood.class) {
					if(player.hurt == 0) {
						player.hurt = -20;//switch to green images
					}
					score+=SCOREINC;
					return true; 
				}
				else if (c.getClass()==Pollution.class) {
					if(player.hurt == 0) {
						player.hurt = 20;//switch to red images
					}
					score-=SCOREDCR;
					player.updateHealth(-50);
					return true;
				}
				else //for predators
				{
					if(player.hurt == 0) {
						player.hurt = 20;//switch to red images
					}
					player.updateHealth(-100);
				}
				c.touch=true; 
			}
		}
		else 
		{
			c.touch=false; //set that they have stopped touching
		}
		return false; //no collision
	}
	
	
	/**
	 * this function returns the bird object that represents the player
	 * @param - nothing
	 * @return - Bird player
	 */
	public Bird getPlayer() {
		return player;
	}
	
	/**
	 * This function checks the user input to the answer of the quiz
	 * @param a
	 * @return
	 */
	public boolean checkQuiz(int a) 
	{
		answered=true; //allow to move to next question 
		if (ansArr[question]==a) //check the answer
		{
			score+=100; //add points for getting it right
			return true;
		}
		else
		{
			score-=100; //take away points for getting it wrong
			return false;
		}
	}
	
	/**
	 * This function progresses the next question
	 * @param - nothing
	 * @return = nothing
	 */
	public void nextQuestion() {
		if(answered) {
			question ++;
			answered = false;
		}
		
	}
	
	/**
	 * this function resets the fields for a new game play
	 * @param - nothing
	 * @return - nothing
	 */
	public static void restart()
	{
		Model.score=0;
		foxLimit = 1;
		preyLimit = 15;
		pollLimit = 5;
		woodLimit = 20;
	}
	
}
		