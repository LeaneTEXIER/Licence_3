package plugin;

/** Interface who implements the plugin
 * 
 * @author Leane Texier
 *
 */
public interface Plugin {
	/** Transform the string (following the plugin)
	 * @param s the string to transform
	 * @return the string transformed
	 */
	public String transform(String s);
	
	/** Return the label of the plugin
	 * @return the label of the plugin
	 */
	public String getLabel();
	
	/** Return the help message of the plugin (ie a sentence to explain what the plugin does)
	 * @return the help message of the plugin
	 */
	public String helpMessage();
}
