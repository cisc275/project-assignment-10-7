import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;



public class Model{
	private int x;
	private int y;
	int xBound;
	int yBound;
	int yIncr=1;
	int xIncr=1;
	int scoreIncr = 1;
	Direction direction;
	static ArrayList<Movers> charArr;
	int colBound;
	Bird player;
	static int score = 0;
	static int foxLimit = 5;
	static int preyLimit = 10;
	static int pollLimit = 8;
	static int woodLimit = 20;
	
	boolean game=false;;
	
	//Variables for Eat
	boolean eatFlag = false;
	int storeY;
	int bd; // the boundary 
	boolean bdReached = false;
	
	int [] ansArr = {2,1,3};
	int question=0;
	
	

	
	
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
	
		if(run) {
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
			Iterator <Movers> i = charArr.iterator();
			
			while(i.hasNext())
			{
				Movers c;
				c = i.next();
				c.move();
				if (checkCollision((AutoCharacters)c))
				{
					i.remove();
					Prey.preyCount--;
					if(score % 5 == 0 && score != 0) {
						foxLimit += 3;
						preyLimit -= 1;
					}
				}
			}
			player.move();
			
			if(eatFlag) 
			{
				player.eat();
				if(player.yPos >= yBound-100 ) { //533 is where the prey are spawning
										// can't hit it for some reason 
					bdReached = true; 
					
				}
				if(player.yPos <= View.frameHeight/2) {
					bdReached = false; 
				}
			}
			player.updateHealth(-1);
		
		} 
	}
	
	public void switchGame() {
		player.updateHealth(Bird.maxHealth);
		game = true;
		Model.charArr = new ArrayList<>();
		Prey.preyCount = 0;
		Pollution.pCount = 0;
		player.setMigrate(true);
	}
	
	/**
	 * @param none
	 * @return int -- the player bird's x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param none
	 * @return int -- the player bird's y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param int -- this value will become the player bird's x value
	 * @return nothing
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param int -- this value will become the player bird's y value
	 * @return nothing
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**will check collision with the edges of the frame, predators, prey, and obstacles
	 *and will change flags accordingly 
	 * @param none
	 * @return boolean -- will return true if there is collision, false otherwise
	 */
	public boolean checkCollision(AutoCharacters c) 
	{
		boolean check = false;
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
					score-=scoreIncr;
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
		return check;
	}
	
	/**
	 * @param none
	 * @return nothing
	 * this will receive keyboard information to determine which direction is
	 * stored in the direction variable of model
	 */ 
	public void setDirection() {
		
	}
	
	public Bird getPlayer() {
		return player;
	}
	
	public boolean checkQuiz( int a) 
	{
		if (ansArr[question]==a)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void nextQuestion(View view) {
		question ++;
		view.setText(question);
	}
	
	
}
		