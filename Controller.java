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
	int arrInd=0;
	
	ArrayList<Bird> playerArr;
	java.util.Timer gameTime;
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
							if(animate) // animations after each level
							{
								start_stop=false;
								animate=model.animation();
							}
							else 
							{
								t.stop();
								serialize();
								timerStop=false;
								gameTime.cancel();
								run=false;
								
								
								if(gameStage == 0) { //level 1 finished, switch to level 2
									
									//view.update(model.getPlayer(), run);
									//gameStage=1;	
									model.switchGame();
									gameStage++;

									View.lvlStart = true;
									view.lvl2Frame(); 
									//view.lvl2Frame();
										
								}
								else if(gameStage == 1) //start level 2
								{	
									run = true;
									View.lvlStart = false;
									animate=true;
									start_stop=true;
									timerStop=true;
									gameStage++;
								}
								else if(gameStage ==2) //level 2 finished, switch to quiz
								{
									View.quiz = true;
									gameStage++;
								}
								else if (gameStage == 3) //start quiz
								{
									view.quizView();
									addQuizButton();
									view.setText(0);
									gameStage++;
								}
								
								addKey();
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
			if(View.quiz) { // switch to quiz from quiz start screen
				View.quiz = false;
			}
			if(gameStage != 1 && View.lvlStart == true) {
				View.lvlStart = !View.lvlStart;
			}
			if (!run && gameStage <=3) {
				start();
				run = true;
			}
			else if(!run && gameStage>3) {
				model.nextQuestion();
				view.setText(model.question);
				view.setAnswer();
			}
			break; 
			
		case 27: //esc
			view.frame.dispose();
			System.exit(0);
			break;
		
		case 32: //space
			model.eatFlag = true;
			model.getPlayer().risefall = 1;
//			if(model.bdReached == false) {
//				model.getPlayer().risefall = 1;
//			}
//			else {
//				model.getPlayer().risefall = 2; 
//			}
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
					gameTime = new java.util.Timer();
					gameTime.schedule(new RemindTask(), 20000);
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