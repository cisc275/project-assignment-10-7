
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */





public class Bird extends Character{


	final static int maxHealth = 1000;
	private int health;
	private Direction direction;
	private boolean migrate;
	int xVector;
	int yVector;
	
	
	// Variables to help eat method 
		int risefall = 1; 	
	
	Bird(){
		super();
	}
	
	Bird(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		health=1000;
		direction = Direction.EAST;
		xVector=0;
		yVector=0;
		migrate = false;
		width = 75;
		height = 75;
		
	}

	/** 
	*This function handles the movement and health of the bird when it dives to eat
	*@param Nothing
	*@return Nothing
	*/
	public void eat()
	{
		switch(risefall) {
		case 1:
			//This will cause the bird to fall
			setYVec(10);
			//move(Direction.SOUTH);
			break;
		case 2:
			//This will cause the bird to rise 
			setYVec(-10);
			break;
//		case 3:
//			// This will cause the bird to stay still (when hitting boundaries)
//			yPos+=0;
//			break;
		
		}


	}

	/**
	*This function updates the position of the bird
	*@param d
	*@return Nothing 
	*/
	public void move()
	{
		checkImage();
		xPos+=xVector;
		yPos+=yVector;
	}
	
	public void keyToDirec(int d) {
		switch(d)
		{
		case 0:
			setXVec(0);
			break;
		case 37:
			setXVec(-10);
			direction = Direction.WEST;
			break;
			
		case 39:
			setXVec(10);
			direction = Direction.EAST;
			break;
		
		default:
			break;
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
		else if (health >=maxHealth)
		{
			health =maxHealth;
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
	
	public void setMigrate(boolean m) {
		migrate=m;
	}
	
	public boolean checkLeftBorder() {
		if(xPos <= 0)
			return false;
		else
			return true;
	}
	
	public boolean checkRightBorder() {
		if(xPos >= View.frameWidth - 100)
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
		if(yPos < -10)
			return false;
		else
			return true;
	}
	
	public void checkImage() {
		int imgInd=0;
		if (migrate)
		{
			if(direction.equals(Direction.EAST))
				imgInd=View.MigFwd;
			else
				imgInd=View.MigBck;
		}
		else {
			if(direction.equals(Direction.EAST))
				imgInd=View.NonMigFwd;
			else
				imgInd=View.NonMigBck;
		}
		super.setImgInd(imgInd);
	}
	
	public void setXVec(int x) {
		if(x>0)
		{
			if(checkRightBorder())
				xVector=x;
			else
				xVector=0;
		}
		else
		{
			if(checkLeftBorder())
				xVector=x;
			else
				xVector=0;
		}
	}
	
	public void setYVec(int y) {
		if(checkTopBorder() && checkBottomBorder()|| y>0) 
			yVector = y;
		else {
			yVector=0;
		}

	}

	
}




