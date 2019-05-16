package plugin;

import java.io.File;
import javax.swing.Timer;
import files.*;

/** Class who check every second the new files and directories in the directory 
 * and print them(SimplePluginObserver) if they are accepted by the filter (if they're a plugin)
 * 
 * Exercice 4
 * 
 * @author Leane Texier
 *
 */
public class MainExercice4 {
	/** Check every second the new files and directories in the directory 'repository'
	 * and print them(SimplePluginObserver) if they are accepted by the filter (ie is a plugin)
	 * @param args noting
	 */
	public static void main(String[] args){
		FileChecker fc = new FileChecker(new PluginFilter(), new File("repository"));
		fc.addFileListener(new SimplePluginObserver());
		Timer t = new Timer(1000, fc);
		t.start();
		while(true);
	}	
}
