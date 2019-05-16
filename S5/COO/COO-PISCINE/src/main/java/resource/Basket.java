package resource;

public class Basket implements Resource {
	/** Create a Basket */
	public Basket(){}
	
	/** @see Resource#description() */
	public String description() {
		return "basket";
	}

}
