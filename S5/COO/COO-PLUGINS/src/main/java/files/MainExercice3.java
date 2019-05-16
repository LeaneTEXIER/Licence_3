package files;

import java.io.File;
import javax.swing.Timer;
import exercices.FileNameFilterEndsByclass;

/** Class who check every second the new files and directories in the directory 
 * and print them if they are accepted by the filter
 * 
 * Exercice 3
 * 
 * @author Leane Texier
 *
 */
public class MainExercice3 {
	/** Check every second the new files and directories in the directory
	 * and print them if they are accepted by the filter (ie ends by '.class')
	 * @param args (0) the directory to look
	 */
	public static void main(String[] args){
		FileChecker fc = new FileChecker(new FileNameFilterEndsByclass(), new File(args[0]));
		fc.addFileListener(new ClassFileListener());
		Timer t = new Timer(1000, fc);
		t.start();
		while(true);
	}	
}
