
/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.*;

public class Bird extends Character{


	private int health;
	private Direction direction;
	private boolean migrate;
	int yIncr=10;
	int xIncr=10;
	static Color color = new Color(0, 0, 255);
	
	// Variables to help eat method 
	int risefall = 1; 
	int storeY;
	
	
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
		switch(risefall) {
		case 1:
			//This will cause the bird to fall
			yPos+=5;
			break;
		case 2:
			//This will cause the bird to rise 
			yPos-=5;
			break;
		case 3:
			// This will cause the bird to stay still (when hitting boundaries)
			yPos+=0;
			break;
		
		}

	}

	/**
	*This function updates the position of the bird
	*@param d
	*@return Nothing 
	*/
	public void move(Direction direction)
	{
		//System.out.println("d");
		switch(direction){
		case NORTH: //north
			yPos-=yIncr;

			break;
		
		case NORTHEAST: //north east
			xPos+=xIncr; 
			yPos-=yIncr;
			break;
			
		case EAST: //east
			xPos+=xIncr;

			break;
			
		case SOUTHEAST: //south east
			xPos+=xIncr;
			yPos+=yIncr;

			break;
			
		case SOUTH: //south
			yPos+=yIncr;

			break;
	
		case SOUTHWEST: //south west
			xPos-=xIncr;
			yPos+=yIncr;

			break;
			
		case WEST: //west
			xPos-=xIncr;
			break;
			
		case NORTHWEST: //north west
			yPos-=yIncr;
			xPos-=xIncr;
			break;
	}
	

	}
	
	public Direction keyToDirec(int d) {
		if (d == 37)
		{
			return Direction.WEST;
		}
		else if (d == 38)
		{
			return Direction.NORTH;
		}
		else if(d == 39)
		{
			return Direction.EAST;
		}
		else
		{
			return Direction.SOUTH;
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
	
	public void setRF(int x) {
		risefall = x;
		
	}
	
	public int getRF() {
		return risefall;
	}

	
}





