package generics;

public class AlreadyCarryingException extends Exception {
    /**
	 * Exception raised when the person carries already an item
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyCarryingException(String message) {
    	super(message);
    }
}