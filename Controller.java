/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
import java.util.*;


/**
 * Controls actions of Model and View as well as controls game play actions. 
 * Methods for user input key actions and starting gameplay. 
 */
public class Controller implements ActionListener, KeyListener{
	
	Model model;
	View view;
	boolean start_stop=true;;
	Action drawAction;
	Bird player;
	Character predator;
	Character prey;
	Timer t;
	boolean timerStop=true;
	int timer=0;
	int drawDelay = 10;
	int dirKey;
	ArrayList<Character> charArr;
	java.util.Timer gameTime;
	int count = 0;
	
	
	/**
	 * Controller constructor:
	 * initializes both view and model using their constructors. 
	 * calls update() and updateLocationAndDirection() if needed based on game action.
	 * @param nothing
	 * @return nothing
	 */
	public Controller() {
		charArr=new ArrayList<>();
		view = new View(false);
		model = new Model(view.getWidth(), view.getHeight(), 10, 10);
		player = new Bird(100,0, view.imageWidth, view.imageHeight);
		charArr.add(player);
		Plane p1= new Plane(view.getWidth(), 100, 25, 25);
		charArr.add(p1);
		//Prey f1 = new Prey(true,view.getWidth(), 175, 25, 25);
		//charArr.add(f1);
		
		view.frame.addKeyListener(this);
		drawAction = new AbstractAction()
	    {
				public void actionPerformed(ActionEvent e)
	      {
	    			//increment the x and y coordinates, alter direction if necessary
					model.updateLocationDirection(start_stop, charArr);			
	    			//update the view
					view.update(player.getX(), player.getY(), true, charArr);
					timer++;
					if(timer % (((int)(Math.random() * (100-50)) + 50)) == 0)
						charArr.add(new Prey(true, view.getWidth(),
								((2 * view.getHeight())/3), 25, 25));
					if(player.getHealth()==0 || !timerStop)
					{
						t.stop();
						try {
							Thread.sleep(500);//changed to 0 for smooth frames
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						if(count == 0) {
							timerStop = true;
							//player = new Bird(100,0, view.imageWidth, view.imageHeight);
							player.updateHealth(1000);
							count=1;
							start();
							view = new View(true);
								
						}
						else if(count == 1) {
							view.newFrame();
							count++;
						}
						
					}
	    		}
	    	};
	}
	
	
	/**
	 * Defines action for user events: button press or key input.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
	 * @param a from user key input or button press
	 * @return nothing
	 */
	public void actionPerformed(ActionEvent a) {}
	
	
	/**
	 * Listener for user key input.
	 * @param e key event from user key input
	 * @return nothing
	 */
	public void keyTyped(KeyEvent e) {}

	
	/**
	 * Captures & records key input code from user key input.
	 * @param e key event from user key input
	 * @return nothing
	 */
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		
		dirKey=e.getKeyCode();
		if(e.getKeyCode() == 27) {
			view.frame.dispose();
			System.exit(0);
		}
		else {
			player.move(player.keyToDirec(e.getKeyCode()));
		}
		
	}

	
	/**
	 * Using key code from keyPressed, performs/records changes 
	 * needed from user key input.
	 * @param e key event from user key input
	 * @return nothing
	 */
	public void keyReleased(KeyEvent e) {
		dirKey=0;
	}
	
	/** 
	 * Creates & starts timer and EventQueue, method used to begin the game.
	 * @param Nothing
	 * @return Nothing
	 */

	
	public void start() {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				t = new Timer(drawDelay, drawAction);
				t.start();
				gameTime = new java.util.Timer();
				gameTime.schedule(new RemindTask(), 60000);
			}
		});
	}
	
	 class RemindTask extends TimerTask {
	        public void run() {
	        	timerStop = false;
	            System.out.println("Time's up!");
	            gameTime.cancel(); //Terminate the timer thread
	        }
	    }
	
}