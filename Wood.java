
import java.util.Random;

public class Wood extends AutoCharacters implements Movers{
	
	static int wCount=0;
	static int width = 88;
	static int height = 22;
	
	Wood(int x, int y, int w, int h) {
		
		super(x, y,  w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		xPos+=0;
	}
	public static void wFactory() {
		Random rand = new Random();
	     if(wCount<Model.woodLimit && rand.nextInt(100)==5) {
	
	    	 Wood w = new Wood(rand.nextInt(View.frameWidth),rand.nextInt((View.frameHeight - View.frameHeight/10) - (2 * View.frameHeight/3) + 1) + (2 * View.frameHeight/3) - height, width, height);
	    	 w.setImgInd(View.Twig);
	 		Model.charArr.add(w);
	 		wCount++; 
	     }
		
	}
}
