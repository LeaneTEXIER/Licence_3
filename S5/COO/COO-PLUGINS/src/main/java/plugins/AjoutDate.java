package plugins;

import plugin.Plugin;
import java.util.*;
import java.text.*;

/** Class who implements a Plugin. Add the date to the beginning of the text.
 * 
 * @author Leane Texier
 *
 */
public class AjoutDate implements Plugin {

	/** Add the date to the beginning of the string
	 * @param s the the string to transform
	 * @return the string transformed
	 */
	public String transform(String s) {
		SimpleDateFormat formater = new SimpleDateFormat("dd MMMM YYYY");
		Date d = new Date();
		return formater.format(d).toString()+"\n\n"+s;
	}

	/** @see plugin.Plugin#getLabel() */
	public String getLabel() {
		return "Date";
	}

	/** @see plugin.Plugin#helpMessage() */
	public String helpMessage() {
		return "Add the date to the beginning of the text";
	}

}
