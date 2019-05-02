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
import java.io.*;

/**
 * Controls actions of model and View as well as controls game play actions.
 * Methods for user input key actions and starting gameplay. 
 */
public class Controller implements ActionListener, KeyListener{
	
	Model model;
	View view;
	boolean start_stop=true;
	Action drawAction;
	
	Timer t;
	boolean timerStop=true;
	int drawDelay = 10;
	int dirKey;
	boolean deserial = false;
	boolean run = false;
	boolean serial = false;
	int arrInd=0;
	
	ArrayList<Bird> playerArr;
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
		
		playerArr = new ArrayList<>();
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.imageHeight, view.imageWidth);

		
		view.frame.addKeyListener(this);
		view.b1.addActionListener(this);
		view.b2.addActionListener(this);
		view.b3.addActionListener(this);
		
		drawAction = new AbstractAction()
	    {
			public void actionPerformed(ActionEvent e)
			{
				if (run) {
	    			//increment the x and y coordinates, alter direction if necessary
					model.updateLocationDirection(start_stop);			
	    			//update the view
					view.update(model.getPlayer(), run);
					
					
					if(model.getPlayer().getHealth()==0 || !timerStop)
					{
						t.stop();
						serialize();
						

						try {
							Thread.sleep(500);//changed to 0 for smooth frames
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						if(count == 0) {
							timerStop = true;
							model.getPlayer().updateHealth(1000);
							count=1;
							start();
							view.lvl2Frame();
							addKey();
								
						}
						else if(count == 1) {
							view.endFrame();
							count++;
						}

					}
					if(serial) {
						Bird splayer= new Bird();
						splayer.xPos=model.getPlayer().xPos;
						splayer.yPos=model.getPlayer().yPos;
						playerArr.add(splayer);
					}
					
					
	    		}
				if(deserial && arrInd<playerArr.size())
				{
					model.getPlayer().xPos=playerArr.get(arrInd).xPos;
					model.getPlayer().yPos=playerArr.get(arrInd).yPos;
					model.updateLocationDirection(start_stop);
					view.update(model.getPlayer(), true);
					arrInd++;
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

	public void actionPerformed(ActionEvent a) {
		if(a.getActionCommand().equals("Run")) {
			run=true;
		}
		else if(a.getActionCommand().equals("Deserialize")) {
			deserialize();
			deserial=true;
		}
		else if(a.getActionCommand().contentEquals("Serialize"))
		{
			serial=true;
		}
		
	}
	
	
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
		// Spacebar will trigger the eat method in Model
		else if(e.getKeyCode() == 32) {
					
					System.out.println("Spacebar is being pressed");
					
					// The origin the bird will return to
					model.storeY = model.getPlayer().yPos;
					System.out.println("The stored Y is: " + model.storeY);
					
					// Signal the bird to fall when eat runs;
					model.getPlayer().risefall = 1;
					
					// Signal the eat to run continuously
					model.eatFlag = true;
					
//					if(model.getBdr() == true){
//						//The bird will not go past the grass
//						model.getPlayer().risefall = 3;
//					}
//					else {
//						model.getPlayer().risefall = 1;
//					}
					
				}
		else
			model.getPlayer().move(model.getPlayer().keyToDirec(dirKey));
		
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
	
<<<<<<< HEAD
=======
	
	/**
	 * Adds key listener to current frame in view.
	 * 	Used for adding key logic to new level. 
	 * @param Nothing
	 * @return Nothing
	 */
	public void addKey() {
		view.frame.addKeyListener(this);
	}
	
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
	/** 
	 * Creates & starts timer and EventQueue, method used to begin the game.
	 * @param Nothing
	 * @return Nothing
	 */
<<<<<<< HEAD

	
	public void start() {
=======
	public void start() {
		
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
<<<<<<< HEAD
				t = new Timer(drawDelay, drawAction);
				t.start();
				gameTime = new java.util.Timer();
				gameTime.schedule(new RemindTask(), 60000);
=======
				
				t = new Timer(drawDelay, drawAction);
				t.start();
				if (run) {
				gameTime = new java.util.Timer();
				gameTime.schedule(new RemindTask(), 60000);
				}
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
			}
		});
	}
	
<<<<<<< HEAD
	 class RemindTask extends TimerTask {
=======
	public void serialize() {
		try {
            FileOutputStream fos = new FileOutputStream("bird.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(playerArr);
            oos.close();
               }
            catch (Exception e)
            {}
	}
	
	public void deserialize() {
		try {
		FileInputStream fis = new FileInputStream("bird.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("here");
        playerArr =  (ArrayList) ois.readObject();
        
        ois.close();        
        //new File("bird.ser").delete();
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	 class RemindTask extends TimerTask 
	 {
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
	        public void run() {
	        	timerStop = false;
	            System.out.println("Time's up!");
	            gameTime.cancel(); //Terminate the timer thread
<<<<<<< HEAD
=======
	            
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
	        }
	    }
	
}