package exercices;

import java.io.*;

/** Class who implements the interface FilenameFilter
 * and creates a filter who only accepts the files and directories ending with '.class'
 * 
 * @author Leane Texier
 *
 */
public class FileNameFilterEndsByclass implements FilenameFilter{
	/** Return true if the name of the file or directory ends by '.class'
	 * @param dir the directory to look
	 * @param name the name of the file or directory to check
	 * 
	 * @return true if the name ends by '.class'
	 */
	public boolean accept(File dir, String name) {
		return name.endsWith(".class");
	}
}
