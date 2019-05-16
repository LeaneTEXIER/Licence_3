package exercices;

import java.io.*;
import java.util.*;

/** Class listing the contents of a directory given in parameter following some filters
 * 
 * Exercice 2
 * 
 * @author Leane Texier
 *
 */
public class ContentsDirectory {
	/* Attribut */
	protected File path;
	
	/** Creates a ContentsDirectory
	 * @param name the path of the directory to look
	 */
	public ContentsDirectory(String name){
		this.path = new File(name);
	}
	
	/** Return the path of the directory to look
	 * @return the path of the directory
	 */
	public File getPath(){
		return this.path;
	}
	
	/** Return the list of files and directories in the directory beginning with a C 
	 * @return the list of files and directories in the directory beginning with a C 
	 */
	public List<String> allFilesBeginsByC(){
		FilenameFilter filter = new FileNameFilterBeginsByC();
		String[] stab = getPath().list(filter);
		return Arrays.asList(stab);
	}
	
	/** Return the list of files and directories in the directory ending by '.class' 
	 * @return the list of files and directories in the directory ending by '.class'
	 */
	public List<String> allFilesEndsByclass(){
		FilenameFilter filter = new FileNameFilterEndsByclass();
		String[] stab = getPath().list(filter);
		return Arrays.asList(stab);
	}
		
	/** Print the list of files and directories in the directory beginning with a C
	 * and the files and directories ending by '.class' 
	 * @param args (0) the directory to look
	 */
	public static void main(String[] args){
		ContentsDirectory f = new ContentsDirectory(args[0]);
		System.out.println(f.allFilesBeginsByC());
		System.out.println(f.allFilesEndsByclass());
	}
}
