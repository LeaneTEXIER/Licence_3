package plugins;

import plugin.Plugin;

/** Class who implements a Plugin. Transform the text into upper case.
 * 
 * @author Leane Texier
 *
 */
public class UpperCase implements Plugin{

	/** Transform the string into upper case
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		return s.toUpperCase();
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Upper case";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Transform all letters into upper case";
	}


}
