package files;

import java.util.*;

/** Interface associated to the FileEvent and defines the observers for this event
 * 
 * @author Leane Texier
 *
 */
public interface FileListener extends EventListener{
	/** The action do when a file is added
	 * @param e the FileEvent associated
	 */
	public void fileAdded(FileEvent e);
	
	/** The action do when a file is removed
	 * @param e the FileEvent associated
	 */
	public void fileRemoved(FileEvent e);
}
