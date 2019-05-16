package donjon.item;

import donjon.gameCharacter.Player;

public class Gold implements Item{
	/*Attributs*/
	protected int nbrGold;
	
	/* Create an item gold
	 * @param i the number of gold
	 */
	public Gold(int i){
		this.nbrGold = i;
	}
	
	/* Return the number of gold in the item
	 * @return the number of gold
	*/
	public int getNbrGold(){
		return this.nbrGold;
	}
	
	/* @see Item#isUsedBy(Player) */
	public void isUsedBy(Player p){
		p.addGold(this.getNbrGold());
		p.getRoom().removeItem(this);
	}
	
	/* @see Item#toString() */
	public String toString(){
		return "Gold";
	}
}
