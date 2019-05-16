package donjon.gameCharacter;

import donjon.item.*;

public class Monster extends GameCharacter{
	
	/*Create a monster */
	public Monster(){
		super(null, 20+(int)(Math.random()*((50-20)+1)), 15+(int)(Math.random()*((20-15)+1)), 5+(int)(Math.random()*((10-5)+1)));
	}
	
	/* When the monster dies, he drops his gold */
	public void die(){
		System.out.println("\nThe monster is dead!");
		Gold g = new Gold(this.getGold());
		this.room.addItem(g);
		this.gold = 0;
		this.room.removeMonster(this);
	}
}
