
import java.awt.Color;
import java.util.Random;

public class Pollution extends AutoCharacters implements Movers{
	
	static int pCount=0;
	static int powidth = 25;
	static int poheight = 25;
	
	Pollution(int x, int y, int w, int h) {
		
		super(x, y,  w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		
	}
	public static void pFactory() {
		Random rand = new Random();
	     if(pCount<8 && rand.nextInt(100)==5) {
	    	 Pollution p = new Pollution(View.frameWidth-rand.nextInt(View.frameWidth),((2 * View.frameHeight)/3)+25, powidth, poheight);
	    	 p.setImgInd(View.Trash);
	 		Model.charArr.add(p);
	 		pCount++; 
	     }
		
	}
}
