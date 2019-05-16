package plugins;

import plugin.Plugin;

/** Class who implements a Plugin. Reverse the text.
 * 
 * @author Leane Texier
 *
 */
public class ReverseText implements Plugin {

	/** Reverse the string
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		String res = "";
		for (int i=s.length()-1; i>=0; i--){
			res += s.charAt(i);
		}
		return res;
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Reverse";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Reverse the text";
	}

}
