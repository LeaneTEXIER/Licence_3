package donjon.item;

import donjon.gameCharacter.Player;

public class Strength implements Item{
	/*Attributs*/
	protected int nbrStrength;
	
	/* Create an item Strength
	 * @param i the number of strength
	 */
	public Strength(int i){
		this.nbrStrength = i;
	}
	
	/* Return the number of points of strength in the item
	 * @return the number of strength
	*/
	public int getNbrStrength(){
		return this.nbrStrength;
	}
	
	/* @see Item#isUsedBy(Player) */
	public void isUsedBy(Player p){
		p.addStrength(this.getNbrStrength());
		p.getRoom().removeItem(this);
	}
	
	/* @see Item#toString() */
	public String toString(){
		return "Strenght";
	}
}
