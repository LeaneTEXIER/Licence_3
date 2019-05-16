package plugin;

import java.io.*;

/** Class who implements the interface FilenameFilter
 * and creates a filter who only accepts the plugin
 * 
 * @author Leane Texier
 *
 */
public class PluginFilter implements FilenameFilter{
	/** Return true if the name of the file is a plugin
	 * It's a plugin if ends with '.class', implements the interface plugin, belongs to the package 'plugin' and has a constructor with no parameter
	 * @param dir the directory to look
	 * @param name the name of the file or directory to check
	 * 
	 * @return true if the name is a plugin
	 */
	public boolean accept(File dir, String name){
		if (!name.endsWith(".class")){
			return false;
		}
		Class<?> c;
		try {
			name = name.substring(0, name.indexOf(".class"));
			c = Class.forName("plugins."+name);
			c.getConstructor();
		} catch (Exception e) {
			return false;
		}
		return (Plugin.class.isAssignableFrom(c) && c.getPackage().getName().equals("plugins"));
	}

}
