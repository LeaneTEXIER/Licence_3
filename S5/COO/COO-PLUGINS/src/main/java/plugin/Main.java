package plugin;

import javax.swing.Timer;
import plugin.editor.Editor;

/** Class who check every second the new files and directories in the directory 
 * and print them(SimplePluginObserver) if they are accepted by the filter (if they're a plugin) and add them to the interface
 * 
 * @author Leane Texier
 *
 */
public class Main {
	/** Display the plugin interface and observer it every second thanks to the fileChecker in the Editor
	 * and print them(SimplePluginObserver) if they are accepted by the filter (ie is a plugin)
	 * @param args noting
	 */
	public static void main(String[] args){
		Editor editor = new Editor();
		editor.getFileChecker().addFileListener(new SimplePluginObserver());
		Timer t = new Timer(1000, editor.getFileChecker());
		t.start();
		while(true);
	}	
}
