package plugins;

import plugin.Plugin;

/** Class who implements a Plugin. Add a signature at the end of the text
 * 
 * @author Leane Texier
 *
 */
public class Signature implements Plugin{

	/** Add a signature at the end of the string
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		return s+"\n\nLéane TEXIER";
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Signature";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Add signature at the end of the text";
	}


}
