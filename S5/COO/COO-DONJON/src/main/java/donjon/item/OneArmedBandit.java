package donjon.item;

import donjon.gameCharacter.Player;
import java.util.*;

public class OneArmedBandit implements Item {
	/*Attributs*/
	protected int nbrGold;
	protected List<Item> items;
	
	/* Create an item OneArmedBandit
	 * @param i the number of gold to give to the bandit
	 */
	public OneArmedBandit(int i){
		this.nbrGold = i;
		this.items = new ArrayList<Item>();
		this.items.add(new Gold(20));
		this.items.add(new Strength(10));
		this.items.add(new Care(30));
	}
	

	/* Return the number of gold needed to have an item
	 * @return the number of gold needed
	*/
	public int getNbrGold(){
		return this.nbrGold;
	}
	
	/* Return the list of items that the oneArmedBandit can gives
	 * @return the list of items
	*/
	public List<Item> getItems(){
		return this.items;
	}
	
	/* @see Item#isUsedBy(Player) */
	public void isUsedBy(Player p){
		if (p.getGold()>=this.getNbrGold()){
			p.removeGold(this.getNbrGold());
		    int i = (int)(Math.random()*((this.getItems().size())));
			Item it = this.getItems().get(i);
			System.out.println("The One-armed bandit gives you "+it+".");
			it.isUsedBy(p);
		}
		p.getRoom().removeItem(this);
	}
	
	/* @see Item#toString() */
	public String toString(){
		return "One-armed bandit";
	}
	
}
