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
	boolean animate=true;
	boolean tutorial;
	int arrInd=0;
	
	ArrayList<Bird> playerArr;
	java.util.Timer gameTime;
	java.util.Timer drawTime;
	static int gameStage = 0;
	
	
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
		model = new Model(view.getWidth(), view.getHeight(), Bird.height, Bird.width);
		
		tutorial = true;
		
		view.frame.addKeyListener(this);
		
		drawAction = new AbstractAction()
	    {
			public void actionPerformed(ActionEvent e)
			{
				if (run) 
				{
					//increment the x and y coordinates, alter direction if necessary
					model.updateLocationDirection(start_stop);			
	    			//update the view
					view.update(model.getPlayer(), run);
					if(model.getPlayer().getHealth()==0 || !timerStop)
					{ 
						if(animate) // animations after each level
						{
							start_stop=false;
							animate=model.animation();
						}
						else 
						{
							t.stop();
							serialize();
							gameTime.cancel();
							run=false;	
							switchStates();
						}	
					}
					
					if(serial) 
					{
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
					model.updateLocationDirection(true);
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
		if(a.getActionCommand().equals("b1"))
		{
			view.setAnswer(model.question, model.checkQuiz(1));
		}
		else if(a.getActionCommand().equals("b2"))
		{
			view.setAnswer(model.question, model.checkQuiz(2));
		}
		else if(a.getActionCommand().equals("b3"))
		{
			view.setAnswer(model.question, model.checkQuiz(3));
		}
		else if(a.getActionCommand().equals("b4"))
		{
			view.setAnswer(model.question, model.checkQuiz(4));
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
		
		case 10: //enter
			if (!run) {
				switch(gameStage) {
				case -1: //start level 1 when restarting game from space input
					View.lvlStart = !View.lvlStart;
					run = true;
					timerStop=true;
					start();
					gameStage++;
					break;
				case 0:
					run = true;
					start();
					View.lvlStart = false;
					break;
				case 1:
					switchStates();
					start();
					drawTime = new java.util.Timer();
					drawTime.schedule(new ViewDrawTask(), 0,500);
					break;
				case 3:
					switchStates();
					break;
				default:
					switchStates();
					break;
					
				}
			}
			break; 
			
		case 27: //esc
			view.frame.dispose();
			System.exit(0);
			break;
		
		case 32: //space
			if(gameStage > 3) {
				playerArr = new ArrayList<>();
				view = new View();
				view.frameSwitch = false;
				run = false;
				gameStage = -1;
				start_stop=true;
				view.cropAmount=196;
				model = new Model(view.getWidth(), view.getHeight(), Bird.height, Bird.width);
				view.frame.addKeyListener(this);
			}
			else{
				model.eatFlag = true;
				model.getPlayer().risefall = 1;
			}
			break;
			
		case 68://D
			deserialize();
			deserial=true;
			break;
		
		case 83://s
			serial=true;
			break;
			
		default:
			model.getPlayer().keyToDirec(e.getKeyCode());
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
		switch(e.getKeyCode()) {
		case 32:
			//Signal eat method to switch to bird rising
			model.getPlayer().risefall = 2;
			break;
		default:
			model.getPlayer().keyToDirec(0);
			
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
					System.out.println("started timer");
					gameTime = new java.util.Timer();
					gameTime.schedule(new GameTask(), 30000);
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
	
	public void switchStates() {
		if(gameStage == 0) { //level 1 finished, switch to level 2
			model.switchGame();
			View.lvlStart = true;
			view.frameSwitch = true;

		}
		else if (gameStage ==1)
		{
			run = true;
			View.lvlStart = false;
			start_stop=true;
			timerStop=true;
			animate=true;
		}
		else if(gameStage == 2) //level 2 finished, switch to quiz
		{
			View.quiz = true;
		}
		else if (gameStage == 3) //start quiz
		{
			View.quiz = false;
			view.quizView();
			addQuizButton();
			view.setText(0);
		}
		else {

			
			model.nextQuestion();
			view.setText(model.question);
			view.setAnswer();
		}
	
		gameStage++;
		addKey();
		
	}
	

	 class GameTask extends TimerTask 
	 {

	        public void run() {
	        	 timerStop = false;
	            System.out.println("Time's up!");
	            gameTime.cancel(); //Terminate the timer thread

	        }
	 }
	 
	 class ViewDrawTask extends TimerTask 
	 {

	        public void run() {
	        	if(view.cropAmount!=0)
	        		view.cropAmount--;

	        }
	 }
	
}