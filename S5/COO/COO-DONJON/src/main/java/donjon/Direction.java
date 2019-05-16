package donjon;

public enum Direction {
	N("North"),W("West"),S("South"),E("East");
	
	/*Attributs */
	private String longName ="";
	
	/* Create a Direction
	 * @param name the name of the direction
	 */
	Direction(String name){
		this.longName = name;
	}
	
	/* Return the longName corresponding to the direction 
	 * @return the longName corresponding to the direction
	 */
	public String toString(){
		return this.longName;
	}
	
	/* Return the opposite Direction of the current Direction
	 * @return the opposite Direction
	 */
	public Direction opposite(){
		return (values()[(this.ordinal()+2)%(values().length)]);
	}
} 
