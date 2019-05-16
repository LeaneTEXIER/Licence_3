package exercices;

import java.awt.event.*;
import java.util.Calendar;
import javax.swing.Timer;

/** Class to test the timer
 * 
 * Exercice 1
 * 
 * @author Leane Texier
 *
 */
public class Timers {
	/** Starts a timer and do the action (ActionTime) every second
	 * @param args nothing
	 */
	public static void main(String[] args){
		Timer t = new Timer(1000, new ActionTime());
		t.start();
		while(true);
	}
	
	/** Class who implements an ActionListener. It's used to print the date with the hour
	 * 
	 * @author Leane Texier
	 *
	 */
	private static class ActionTime implements ActionListener{
		/** Do the action of the class. Print the date and the hour
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			System.out.println(Calendar.getInstance().getTime());
		}
	}
}
