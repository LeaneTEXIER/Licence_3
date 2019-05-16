package exercices;

import java.io.*;

/** Class who implements the interface FilenameFilter
 * and creates a filter who only accepts the files and directories beginning with a C
 * 
 * @author Leane Texier
 *
 */
public class FileNameFilterBeginsByC implements FilenameFilter{
	/** Return true if the name of the file or directory begins with a C
	 * @param dir the directory to look
	 * @param name the name of the file or directory to check
	 * 
	 * @return true if the name begins with a C
	 */
	public boolean accept(File dir, String name){
		return name.startsWith("C");
	}
}