package plugins;

import plugin.Plugin;

/** Class who implements a Plugin. Transform the text in ASCII code.
 * 
 * @author Leane Texier
 *
 */
public class Ascii implements Plugin{

	/** Transform the string in ASCII code 
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		String res="";
		for (int i=0; i<s.length(); i++){
			res+=((int) s.charAt(i)+"/");
		}
		return res;
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Ascii";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Transform all letters into their ASCII code";
	}

}
