package files;

import java.util.*;

/** Class corresponding to the event emitted when a file is added or removed
 * 
 * @author Leane Texier
 *
 */
public class FileEvent extends EventObject{
	/* Attributs */
	private static final long serialVersionUID = 1L;
	protected String fileName;
	
	/** Creates a FileEvent
	 * @param name the name of the file concerned 
	 */
	public FileEvent(String name){
		super(name);
		this.fileName = name;
	}
	
	/** Return the name of the file concerned by this FileEvent
	 * @return the name of the file concerned 
	 */
	public String getFileName(){
		return this.fileName;
	}
}
