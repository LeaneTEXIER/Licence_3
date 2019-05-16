package plugins;

import plugin.Plugin;

/** Class who implements a Plugin. Transform the text into lower case.
 * 
 * @author Leane Texier
 *
 */
public class LowerCase implements Plugin{

	/** Transform the string into lower case
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		return s.toLowerCase();
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Lower case";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Transform all letters into lower case";
	}


}
