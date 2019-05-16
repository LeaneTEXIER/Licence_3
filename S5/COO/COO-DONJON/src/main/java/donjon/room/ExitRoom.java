package donjon.room;

public class ExitRoom extends Room{
	
	/*Create an exitRoom
	 */
	public ExitRoom(){
		super();
	}
	
	/* Return true if the room is an exitRoom, else return false
	 * @return true if the room is an exitRoom, else false
	*/
	public boolean isExit(){
		return true;
	}
}
