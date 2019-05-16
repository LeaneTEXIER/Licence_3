package donjon.item;

import donjon.gameCharacter.Player;

public interface Item {
	/* The player uses the item
	 * @param p the player who uses the item
	 */
	public void isUsedBy(Player p);
	
	/* Return the type of the item
	 * @return the item
	 */
	public String toString();
}
