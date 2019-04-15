/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

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
	int timer;
	int drawDelay = 30;
	int dirKey;
	ArrayList<Character> charArr;
	
	
	/**
	 * Controller constructor:
	 * initializes both view and model using their constructors. 
	 * calls update() and updateLocationAndDirection() if needed based on game action.
	 * @param nothing
	 * @return nothing
	 */
	public Controller() {
		charArr=new ArrayList<>();
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), 10, 10);
		player = new Bird(100,0, view.imageWidth, view.imageHeight);
		charArr.add(player);
		Plane p1= new Plane(view.getWidth(), 100, 25, 25);
		charArr.add(p1);
		Prey f1 = new Prey(true,view.getWidth(), 175, 25, 25);
		charArr.add(f1);
		
		view.frame.addKeyListener(this);
		
		drawAction = new AbstractAction()
	    {
				public void actionPerformed(ActionEvent e)
	      {
	    			//increment the x and y coordinates, alter direction if necessary
					model.updateLocationDirection(start_stop, charArr);			
	    			//update the view
	    			//view.update(model.getX(), model.getY(), model.getDirect(), start_stop);
					view.update(player.getX(), player.getY(), Direction.EAST, true, charArr);
					//System.out.println(player.getX());
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
		player.move(player.keyToDirec(dirKey));
		
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
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
			}
		});
	}
	
}