package plugin;

import files.*;

/** Class who implements the interface FileListener.
 * The action is to print a sentence who indicates which plugin is added or removed
 * 
 * @author Leane Texier
 *
 */
public class SimplePluginObserver implements FileListener{
	/** @see FileListener#fileAdded(FileEvent e)
	 * Print a sentence who indicates which plugin was added
	 */
	public void fileAdded(FileEvent e) {
		System.out.println("Nouveau plugin : "+ e.getFileName() +" detecté");
	}

	/** @see FileListener#fileRemoved(FileEvent e)
	 * Print a sentence who indicates which plugin was removed
	 */
	public void fileRemoved(FileEvent e) {
		System.out.println("Suppression plugin : "+ e.getFileName() +" detectée");
	}

}
