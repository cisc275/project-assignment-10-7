public class Model {
	private int x;
	private int y;
	int xBound;
	int yBound;
	boolean forward;
	boolean up;
	boolean backward;
	boolean down;
	
	public Model(int w, int h, int imgW, int imgH) {
		//creates model
		xBound = w-imgW;
		yBound = h-imgH;
	}
	
	/* @param: boolean -- true if in gameplay, false if in menu
	 * @return: nothing
	 * will determine if the user is in a menu or in a moment of gameplay
	 *  During gameplay, this will change increment of the player model speed
	 *   using switch cases based on the direction enumeration. When in a menu, this 
	 *   will stop updating the player model
	 */
	public void updateLocationDirection(boolean run) {
		if(run) {
			
		} else {
			
		}
	}
	
	/*
	 * @param: none
	 * @return: int -- the player bird's x position
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * @param: none
	 * @return: int -- the player bird's y position
	 */
	public int getY() {
		return y;
	}
	
	/*
	 * @param: int -- this value will become the player bird's x value
	 * @return: nothing
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/*
	 * @param: int -- this value will become the player bird's y value
	 * @return: nothing
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/*@param: none
	 *@return: boolean -- will return true if there is collision, false otherwise
	 *will check collision with the edges of the frame, predators, prey, and obstacles
	 *and will change flags accordingly 
	 */
	public boolean checkCollision() {
		boolean collide = true;
		
		return collide;
		
	}
	
	/* @param: none
	 * @return: nothing
	 * this will receive keyboard information to determine which direction is
	 * stored in the direction variable of model
	 */ 
	public void setDirection() {
		
	}

}
