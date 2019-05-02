
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */


import java.awt.Color;



public class Bird extends Character {


	private int health;
	private Direction direction;
	private boolean migrate;
	int yIncr=10;
	int xIncr=10;
	static Color color = new Color(0, 0, 255);
	
	
	Bird(){
		super();
	}
	
	Bird(int x, int y, int w, int h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
		health=1000;
		direction = Direction.EAST;
	}

	/** 
	*This function handles the movement and health of the bird when it dives to eat
	*@param Nothing
	*@return Nothing
	*/
	public void eat()
	{
		

	}

	/**
	*This function updates the position of the bird
	*@param d
	*@return Nothing 
	*/
	public void move(Direction direction)
	{
		switch(direction)
		{
			case NORTH: //north
				if(checkTopBorder())
				yPos-=yIncr;
				break;
			
			case NORTHEAST: //north east
				if(checkTopBorder() && checkRightBorder())
				{
				xPos+=xIncr; 
				yPos-=yIncr;
				}
				break;
				
			case EAST: //east
				if (checkRightBorder())
				xPos+=xIncr;
				break;
				
			case SOUTHEAST: //south east
				if (checkBottomBorder() && checkRightBorder())
				{
				xPos+=xIncr;
				yPos+=yIncr;
				}
				break;
				
			case SOUTH: //south
				if (checkBottomBorder())
				yPos+=yIncr;
				break;
		
			case SOUTHWEST: //south west
				if(checkBottomBorder() && checkLeftBorder())
				{
				xPos-=xIncr;
				yPos+=yIncr;
				}
				break;
				
			case WEST: //west
				if (checkLeftBorder())
				xPos-=xIncr;
				break;
				
			case NORTHWEST: //north west
				if(checkTopBorder() && checkLeftBorder()) {
				yPos-=yIncr;
				xPos-=xIncr;
				}
				break;
		}
	

	}
	
	public Direction keyToDirec(int d) {
		switch(d)
		{
		case 37:
			direction = Direction.WEST;
			return Direction.WEST;

		case 38:
			direction = Direction.NORTH;
			return Direction.NORTH;

		
		case 39:
			direction = Direction.EAST;
			return Direction.EAST;
		
		case 40:
			direction = Direction.SOUTH;
			return Direction.SOUTH;
			
		default:
			return Direction.EAST;
		}
	}

	/**
	*This function updates the health of the bird based on what is passed
	*@param change 
	*@return Nothing
	*/
	public void updateHealth(int change)
	{
		health += change;
		if (health<=0) {
			health =0;
		}
		else if (health >=1000)
		{
			health =1000;
		}
	}

	/**
	*This function returns the health value
	*@param Nothing
	*@return the amount of health the bird has  
	*/
	public int getHealth()
	{
		return health;
	}
	
	public Direction getDirec() {
		return direction;
	}
	
	public boolean checkLeftBorder() {
		if(xPos <= 0)
			return false;
		else
			return true;
	}
	
	public boolean checkRightBorder() {
		if(xPos >= View.frameWidth - width)
			return false;
		else
			return true;
	}
	
	public boolean checkBottomBorder() {
		if(yPos >= View.frameHeight - height)
			return false;
		else
			return true;
	}
	
	public boolean checkTopBorder() {
		if(yPos <= 0)
			return false;
		else
			return true;
	}

	
}




