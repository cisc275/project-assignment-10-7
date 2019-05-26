
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */





public class Bird extends Character{

	
	final static int maxHealth = 1000;
	private int health;
	private Direction direction;
	private boolean migrate;
	int yIncr=3;
	int xIncr=3;
	int xVector;
	int yVector;
	static int width = 75;
	static int height = 66;
	int hurt;
	int powerup;
	int imgInd;
	
	
	// Variables to help eat method 
		int risefall = 1; 	
	
	Bird(){
		super();
	}
	
	Bird(int x, int y) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		health=1000;
		direction = Direction.EAST;
		xVector=0;
		yVector=0;
		migrate = false;
		hurt = 0;
		powerup = 0;
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
			setYVec(View.frameHeight/80);
			//move(Direction.SOUTH);
			break;
		case 2:
			//This will cause the bird to rise 
			setYVec(-(View.frameHeight/80));
			break;
		
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
		if(!((!checkRightBorder() && xVector>0) || (!checkLeftBorder() && xVector<0))) {
			xPos+=xVector;
		}
		
		yPos+=yVector;
	}
	
	
	public void keyToDirec(int d) {
		switch(d)
		{
		case 0:
			setXVec(0);
			break;
		case 37:
			setXVec(-(View.frameWidth/128));
			direction = Direction.WEST;
			break;
			
		case 39:
			setXVec(View.frameWidth/128);
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
	
	public void setDirec(Direction d) {
		 direction=d;
	}
	
	public void setMigrate(boolean m) {
		migrate=m;
	}
	
	public boolean getMigrate() {
		return migrate;
	}
	
	public boolean checkLeftBorder() {
		if(xPos <= 0)
			return false;
		else
			return true;
	}
	
	public boolean checkRightBorder() {
		if(xPos  >= View.frameWidth - width)
			return false;
		else
			return true;
	}
	
	public boolean checkBottomBorder() {
		if(yPos >= 7*View.frameHeight/8 - height)
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
		imgInd=0;
		if(hurt > 0) {
			if (migrate) {
				if(direction.equals(Direction.EAST)) {
					imgInd=View.MigFwdRed;
				}
				else {
					imgInd=View.MigBckRed;
				}
			}
			else {
				if(direction.equals(Direction.EAST))
					imgInd=View.NonMigFwdRed;
				else
					imgInd=View.NonMigBckRed;
			}
			hurt--;
		}
		else if(hurt < 0) {
			if (migrate) {
				if(direction.equals(Direction.EAST)) {
					imgInd=View.MigFwdGreen;
				}
				else {
					imgInd=View.MigBckGreen;
				}
			}
			else {
				if(direction.equals(Direction.EAST))
					imgInd=View.NonMigFwdGreen;
				else
					imgInd=View.NonMigBckGreen;
			}
			hurt++;
		}
		
		else {
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
			
		}
		super.setImgInd(imgInd);
	}
	
	public void setXVec(int x) {
		xVector=x;
	}
	
	public void setYVec(int y) {
		if(y>0)
		{
			if(checkBottomBorder())
				yVector=y;
			else
				yVector=0;
		}
		else
		{
			if(checkTopBorder())
				yVector=y;
			else
				yVector=0;
		}

	}
	
	public void moveAnimate(boolean dead)
	{
		if(dead)
		{
			xVector=5;
			yVector=View.frameHeight/100;
		}
		else
		{
			xVector=View.frameWidth/60;
			yVector=-3;
		}
		checkImage();
		xPos+=xVector;
		yPos+=yVector;
	}

	
}




