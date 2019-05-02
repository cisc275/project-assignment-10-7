/**
 * @author Yasser Abdelaal, Kate Bagshaw, Evan DeAngelis, David Olaoye, Jessica Schwartz
 */

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
<<<<<<< HEAD

=======
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
import java.util.*;
<<<<<<< HEAD


/**
 * Controls actions of Model and View as well as controls game play actions. 
=======
import java.io.*;


/**
 * Controls actions of model and View as well as controls game play actions. 
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
 * Methods for user input key actions and starting gameplay. 
 */
public class Controller implements ActionListener, KeyListener{
	
	Model model;
	View view;
<<<<<<< HEAD
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
=======
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
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
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
<<<<<<< HEAD
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
=======
		
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
						
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
						try {
							Thread.sleep(500);//changed to 0 for smooth frames
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						if(count == 0) {
							timerStop = true;
<<<<<<< HEAD
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
=======
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
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
	}
	
	
	/**
	 * Defines action for user events: button press or key input.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
	 * @param a from user key input or button press
	 * @return nothing
	 */
<<<<<<< HEAD
	public void actionPerformed(ActionEvent a) {}
=======
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
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
	
	
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
<<<<<<< HEAD
		
=======
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
		dirKey=e.getKeyCode();
		if(e.getKeyCode() == 27) {
			view.frame.dispose();
			System.exit(0);
		}
<<<<<<< HEAD
		else {
			player.move(player.keyToDirec(e.getKeyCode()));
		}
=======
		else
			model.getPlayer().move(model.getPlayer().keyToDirec(dirKey));
>>>>>>> f79898b89181bfd06ad7dd824b3bfe2cc68b0289
		
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