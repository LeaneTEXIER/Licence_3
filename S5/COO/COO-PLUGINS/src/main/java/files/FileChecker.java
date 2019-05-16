package files;

import java.awt.event.*;
import java.io.*;
import java.util.*;

/** Class of the objects emitted by the FileEvent
 * 
 * @author Leane Texier
 *
 */
public class FileChecker implements ActionListener{
	/* Attributs */
	private ArrayList<FileListener> fileListeners;
	protected FilenameFilter filter;
	protected File path;
	protected HashSet<String> filesKnown;
	
	/** Creates a FileChecker
	 * @param f the filter 
	 * @param path the path of the directory to look
	 */
	public FileChecker(FilenameFilter f, File path){
		this.filter = f;
		this.path = path;
		this.fileListeners = new ArrayList<FileListener>();
		this.filesKnown = new HashSet<String>();
	}
	
	/** Return the FileListeners associated to this FileChecker
	 * @return the FileListeners associated
	 */
	public List<FileListener> getFileListeners(){
		return this.fileListeners;
	}
	
	/** Return the FilenameFilter associated to this FileChecker
	 * @return the FilenameFilter associated
	 */
	public FilenameFilter getFilter(){
		return this.filter;
	}
	
	/** Return the path of the directory to look
	 * @return the path of the directory
	 */
	public File getPath(){
		return this.path;
	}
	
	/** Return the set of the files already known by this FileChecker
	 * @return the files already known
	 */
	public Set<String> getFilesKnown(){
		return this.filesKnown;
	}
	
	/** Add a FileListener to the FileChecker
	 * @param l the FileListener to add
	 */
	public synchronized void addFileListener(FileListener l){
		if (!getFileListeners().contains(l)){
			fileListeners.add(l);
		}	
	}
	
	/** Remove a FileListener to the FileChecker
	 * @param l the FileListener to remove
	 */
	public synchronized void removeFileListener(FileListener l){
		fileListeners.remove(l);	
	}
	
	/** Create and spread the event to all the FileListeners when a file is added
	 * @param fileName the name of the file added
	 */
	private void fireFileAdded(String fileName){
		ArrayList<FileListener> fl = (ArrayList<FileListener>) fileListeners.clone();
		FileEvent event = new FileEvent(fileName);
		for (FileListener listener : fl){
			listener.fileAdded(event);
		}
	}
	
	/** Create and spread the event to all the FileListeners when a file is removed
	 * @param fileName the name of the file removed
	 */
	private void fireFileRemoved(String fileName){
		ArrayList<FileListener> fl = (ArrayList<FileListener>) fileListeners.clone();
		FileEvent event = new FileEvent(fileName);
		for (FileListener listener : fl){
			listener.fileRemoved(event);
		}
	}

	/** @see ActionListener#actionPerformed(ActionEvent e) 
	 * Check if some files are added or removed in the directory when the ActionEvent is launch,
	 * and do the action in consequence for each file added or removed 
	 */
	public void actionPerformed(ActionEvent e) {
		String[] stab = this.path.list(this.filter);
		List<String> filesFind = Arrays.asList(stab);
		HashSet<String> knownFiles = (HashSet<String>) filesKnown.clone();
		for (String s : filesFind){
			if (!knownFiles.contains(s)){
				this.fireFileAdded(s);
				this.filesKnown.add(s);
			}
		}
		for (String s : knownFiles){
			if (!filesFind.contains(s)){
				this.fireFileRemoved(s);
				this.filesKnown.remove(s);
			}
		}
	}
}
