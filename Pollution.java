
import java.awt.Color;
import java.util.Random;

public class Pollution extends AutoCharacters{
	
	static int pCount;
	
	Pollution(int x, int y, int w, int h) {
		
		super(x, y,  w, h, Color.gray);
		// TODO Auto-generated constructor stub
	}
	
	public static void pFactory() {
		Random rand = new Random();
	     if(pCount<8 && rand.nextInt(100)==5) {
	    	 Pollution p = new Pollution(View.frameWidth,((2 * View.frameHeight)/3), 25, 25);
	 		Model.charArr.add(p);
	 		pCount++; 
	     }
		
	}
}
