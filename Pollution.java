
import java.awt.Color;
import java.util.Random;

public class Pollution extends AutoCharacters implements Movers{
	
	static int pCount=0;
	static int width = 25;
	static int height = 25;
	
	Pollution(int x, int y, int w, int h) {
		
		super(x, y,  w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		xPos-= 5 ;
	}
	public static void pFactory() {
		Random rand = new Random();
	     if(pCount<Model.pollLimit && rand.nextInt(100)==5) {
	    	 Pollution p = new Pollution(View.frameWidth, rand.nextInt((View.frameHeight - View.frameHeight/10) - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3) - height, width, height);
	    	 p.setImgInd(View.Trash);
	 		Model.charArr.add(p);
	 		pCount++; 
	     }
		
	}
}
