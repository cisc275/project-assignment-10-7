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
						timerStop=false;
						gameTime.cancel();

						if(count == 0) {
							run=false;
							view.update(model.getPlayer(), run);
							count=1;	
							model.getPlayer().updateHealth(1000);
							view.lvl2startFrame();
							addKey();
								
						}
						else if(count == 1) 
						{
							run=false;
							timerStop=true;
							view.lvl2Frame();
							addKey();
							count++;
						}
						else if(count ==2)
						{
							view.endFrame();
							addKey();
							run = false;
							count++;
						}
						else if (count == 3)
						{
							//run = false;
							view.quizView();
							addKey();
							addQuizButton();
							count++;
						}

					}
					if(serial) {
						Bird splayer= new Bird();
						splayer.xPos=model.getPlayer().xPos;
						splayer.yPos=model.getPlayer().yPos;
						playerArr.add(splayer);
					}
					
					
	    		}//if(run)
				if(deserial && arrInd<playerArr.size())
				{
					model.getPlayer().xPos=playerArr.get(arrInd).xPos;
					model.getPlayer().yPos=playerArr.get(arrInd).yPos;
					model.updateLocationDirection(start_stop);
					view.update(model.getPlayer(), true);
					arrInd++;
				}
      		}//public void actionPerformed(ActionEvent e)	
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
			view.frame.requestFocus();
		}
		else if(a.getActionCommand().equals("Deserialize")) {
			deserialize();
			deserial=true;
		}
		else if(a.getActionCommand().contentEquals("Serialize"))
		{
			serial=true;
		}
		else if(a.getActionCommand().equals("b1"))
		{
			view.qb1.setText("Pressed Bitch");
			view.quizLabel.setText("Question");
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
		switch(e.getKeyCode()) {
		case 10:
			if (!run)
				start();
			System.out.println("Done");
			run = true;
			break; 
			
		case 27:
			view.frame.dispose();
			System.exit(0);
			break;
		
		case 32:
			// Signal the bird to fall when eat runs;
			model.getPlayer().risefall = 1;
			// Signal the eat to run continuously
			model.eatFlag = true;
			break;
			
		default:
			model.getPlayer().move(model.getPlayer().keyToDirec(dirKey));
			break;
			
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
		if(e.getKeyCode() == 32) {
			//Signal eat method to switch to bird rising
			model.getPlayer().risefall = 2;
			// model.eatFlag = false;
			// System.out.println("The Bird's final yPos is: " + player.yPos);
		}
	}
	
	
	/**
	 * Adds key listener to current frame in view.
	 * 	Used for adding key logic to new level. 
	 * @param Nothing
	 * @return Nothing
	 */
	public void addKey() {
		view.frame.addKeyListener(this);
	}
	
	public void addQuizButton() {
		view.qb1.addActionListener(this);
		view.qb2.addActionListener(this);
		view.qb3.addActionListener(this);
		view.qb4.addActionListener(this);
		
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
				System.out.println("Started");
				t = new Timer(drawDelay, drawAction);
				t.start();
				if (run) {
				gameTime = new java.util.Timer();
				gameTime.schedule(new RemindTask(), 5000);
				}
			}
		});
	}
	

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

	        public void run() {
	        	timerStop = false;
	            System.out.println("Time's up!");
	            gameTime.cancel(); //Terminate the timer thread

	        }
	    }
	
}