import java.awt.Color;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

public class Character implements Serializable{
	protected int xPos;
	protected int yPos;
	protected static int width;
	protected static int height;
	boolean touch;
	
	static final long serialVersionUID=12L;
	
	int imgArrNum;

	/**
	*This constructor sets the initial position of the object as passed
	*@param x an int to represent starting x position 
	 @param y an int to represent starting y position
	 @return nothing 
	*/
	Character(){}
	Character(int x, int y, int w, int h)
	{
		xPos = x;
		yPos= y;
		touch = false;
		width = w;
		height = h;

	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setImgInd(int i) {
		imgArrNum = i;
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}

}