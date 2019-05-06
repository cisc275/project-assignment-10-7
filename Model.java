import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;



public class Model{
	private int x;
	private int y;
	int xBound;
	int yBound;
	boolean forward;
	boolean up;
	boolean backward;
	boolean down;
	int yIncr=1;
	int xIncr=1;
	Direction direction;
	static ArrayList<AutoCharacters> charArr;
	int colBound;
	Bird player;
	static int timer=0;
	
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
		player = new Bird(100,0, imgW, imgH);
	
		
		
		
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
			Prey.preyFactory();
			if(game)
				Plane.planeFactory();
			else
				Fox.addFox();
			Iterator <AutoCharacters> i = charArr.iterator();
			
			while(i.hasNext())
			{
				AutoCharacters c;
				c = i.next();
				if (!c.touch) {
					
					if (c.getClass()==Fox.class)
					{
						Fox f = (Fox)c;
						f.move();
					}
					else {
						c.move();
					}
					checkCollision(c);
					timer++;
				}
				else {
					i.remove();
					Prey.preyCount--;
				}
				
			}
			
			if(eatFlag) 
			{
				player.eat();
				if(player.yPos >= 560 ) { //533 is where the prey are spawning
										// can't hit it for some reason 
					System.out.println("Penalty!");
					bdReached = true; 
					
				}
				if(player.yPos <= 100) {
					bdReached = false; 
				}
			}
			
			if (player.touch) {
				player.updateHealth(-1);
				player.touch=false;
			}
			else
			{
				player.touch=true;
			}
		} 
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
	public void checkCollision(AutoCharacters c) 
	{

		if (c.getBounds().intersects(player.getBounds()))
		{
			if (!c.touch)
			{
				if(c.getClass()==Prey.class)
				{
					player.updateHealth(100);
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
		