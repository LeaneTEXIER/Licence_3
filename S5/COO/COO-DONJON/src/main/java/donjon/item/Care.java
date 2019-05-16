package donjon.item;

import donjon.gameCharacter.Player;

public class Care implements Item{
	/*Attributs*/
	protected int nbrCare;
	
	/* Create an item Care
	 * @param i the number of lifePoints
	 */
	public Care(int i){
		this.nbrCare = i;
	}
	
	/* Return the number of lifepoints in the item
	 * @return the number of lifepoints
	*/
	public int getNbrCare(){
		return this.nbrCare;
	}
	
	/* @see Item#isUsedBy(Player) */
	public void isUsedBy(Player p){
		p.addLifePoints(this.getNbrCare());
		p.getRoom().removeItem(this);
	}
	
	/* @see Item#toString() */
	public String toString(){
		return "Care";
	}
}
