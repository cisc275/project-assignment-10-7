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
	Plane p1;
	static int timer=0;
	
	//Variables for Eat
		boolean eatFlag = false;
		int storeY;
		int bd; // the boundary 
		boolean bdReached = false;

	
	
	public Model(int w, int h, int imgW, int imgH) {
		//creates model
		xBound = w-imgW;
		yBound = h-imgH;
		colBound=imgW;
		charArr=new ArrayList<>();
		player = new Bird(100,0, imgW, imgH);
		p1= new Plane(View.frameWidth, 100, 200, 80);
		charArr.add(p1);
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
			Plane.planeFactory();
			Fox.addFox();
			Pollution.pFactory();
			Iterator <AutoCharacters> i = charArr.iterator();
			while(i.hasNext())
			{
				AutoCharacters c;
				c = i.next();
				if (!c.collided) {
				c.move();
				flipFox(c);
				checkCollision(c);
				timer++;}
				else {
					i.remove();
			}
			}
			
			if(eatFlag) {
				player.eat();
				if(player.yPos == 560 ) { //533 is where the prey are spawning
										// can't hit it for some reason 
					System.out.println("Penalty!");
					bdReached = true; 
					
					}
				if(player.yPos == 0) {
					System.out.println("Penalty has been reset");
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
				if(c.getClass()==Plane.class)
				{
					player.updateHealth(-100);
				}
				else if(c.getClass()==Fox.class) {
					player.updateHealth(-100);
				}
				else if(c.getClass()==Pollution.class) {
					player.updateHealth(-100);
					c.collided = true;
				}
				else
				{
					player.updateHealth(100);
					c.collided=true;
					
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
	
	// This will help the fox to flip when it reaches the border 
		public void flipFox(AutoCharacters c) {
			if(c.getClass()==Fox.class && c.xPos <= 0) {
				c.flip = 2;
			}
			else if(c.getClass()==Fox.class && c.xPos >= View.frameWidth) {
				c.flip = 1;
			}
			else {
				// c.flip = 1;
				System.out.println("Not a fox");
			}
		}

	
}
		