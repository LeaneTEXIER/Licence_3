package plugin.editor;

import javax.swing.Timer;;

/** Class for the plugin interface
 * 
 * Exercice 5
 * 
 * @author Leane Texier
 *
 */
public class MainExercice5 {
	/** Display the plugin interface and observer it every second thanks to the fileChecker in the Editor
	 * @param args noting
	 */
	public static void main(String[] args){
		Editor editor = new Editor();
		Timer t = new Timer(1000, editor.getFileChecker());
		t.start();
		while(true);
	}
}
