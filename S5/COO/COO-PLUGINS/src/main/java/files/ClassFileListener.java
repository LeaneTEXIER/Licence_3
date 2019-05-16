package files;

/** Class who implements the interface FileListener.
 * The action is to print a sentence who indicates which file is added or removed
 * 
 * @author Leane Texier
 *
 */
public class ClassFileListener implements FileListener{
	/** @see FileListener#fileAdded(FileEvent e)
	 * Print a sentence who indicates which file was added
	 */
	public void fileAdded(FileEvent e) {
		System.out.println("nouveau .class : "+ e.getFileName() +" detecté");
	}

	/** @see FileListener#fileRemoved(FileEvent e)
	 * Print a sentence who indicates which file was removed
	 */
	public void fileRemoved(FileEvent e) {
		System.out.println(".class : "+ e.getFileName() +" supprimé detecté");
	}

}
