import java.util.ArrayList;
import java.util.Iterator;



public class Model{

	int xBound;
	int yBound;
	int scoreIncr = 100;
	int scoreDncr = 25;
	Direction direction;
	static ArrayList<Movers> charArr;
	int colBound;
	Bird player;
	static int score = 0;
	static int foxLimit = 3;
	static int preyLimit = 15;
	static int pollLimit = 8;
	static int woodLimit = 20;
	
	boolean game=false;;
	
	//Variables for Eat
	boolean eatFlag = false;
	boolean bdReached = false;
	boolean helpFlag = false;
	
	int [] ansArr = {2,1,3};
	int question=0;
	
	boolean answered = false;
	
	

	
	
	public Model(int w, int h, int imgW, int imgH) {
		//creates model
		xBound = w-imgW;
		yBound = h-imgH;
		colBound=imgW;
		charArr=new ArrayList<>();
		player = new Bird(100,0);		
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
			Pollution.pFactory();
			if(game) {
				Prey.preyFactory(View.Fish);
				Plane.planeFactory();
			}
			else
			{
				Prey.preyFactory(View.Mouse);
				Fox.addFox();
				Wood.wFactory();
			}
			 
			Iterator<Movers> i = charArr.iterator();
			while(i.hasNext())
			{
				Movers c;
				c = i.next();
				c.move();
				if (checkCollision((AutoCharacters)c))
				{
					i.remove();
					Prey.preyCount--;
				
					}
				}
			
			if(score % 300 == 0 && score != 0) {
				//System.out.println("Challenging Player");
				foxLimit += 1;
				// preyLimit -= 1;
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
			player.move();
			
			if(eatFlag) 
			{
				player.eat();
			}
			player.updateHealth(-1);
		}

	
	public boolean animation()
	{	player.setDirec(Direction.EAST);
		player.moveAnimate(player.getHealth()==0);
		
		if(player.getX()>(View.frameWidth+Bird.width) || player.getY()>(View.frameHeight+Bird.height))
			return false;
		return true;
	}
	
	public void switchGame() {
		
		player=new Bird(View.frameWidth/3, View.frameHeight/3);
		player.setMigrate(true);
		eatFlag = false;
		player.risefall=2;
		game = true;
		Model.charArr = new ArrayList<>();
		Prey.preyCount = 0;
		Pollution.pCount = 0;
		
	}

	/**will check collision with the edges of the frame, predators, prey, and obstacles
	 *and will change flags accordingly 
	 * @param none
	 * @return boolean -- will return true if there is collision, false otherwise
	 */
	public boolean checkCollision(AutoCharacters c) 
	{
		if (c.getBounds().intersects(player.getBounds()))
		{
			if (!c.touch)
			{
				if(c.getClass()==Prey.class)
				{
					player.updateHealth(100);
					return true;
				}
				else if(c.getClass()==Wood.class) {
					score+=scoreIncr;
					return true; 
				}
				else if (c.getClass()==Pollution.class) {
					score-=scoreDncr;
					player.updateHealth(-50);
					return true;
				}
				else
				{
					player.updateHealth(-100);
				}
				c.touch=true;
			}
		}
		else 
		{
			c.touch=false;
		}
		return false;
	}
	
	
	public Bird getPlayer() {
		return player;
	}
	
	public boolean checkQuiz( int a) 
	{
		answered=true;
		if (ansArr[question]==a)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void nextQuestion() {
		if(answered) {
			question ++;
			answered = false;
		}
		
	}
	
//	public void switchGames()
//	{
//
//		player.updateHealth(Bird.maxHealth);
//		player.setMigrate(true);
//		game=true;
//		Model.charArr=new ArrayList<>();
//		Prey.preyCount=0;
//		Pollution.pCount=0;
//	}
	
}
		