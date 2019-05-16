package donjon.action;

import donjon.gameCharacter.Player;
import donjon.room.Room;
import scanner.ScannerInt;
import donjon.item.*;

public class UseAction implements Action {
	
	/* Create a useAction */
	public UseAction(){}
	
	/* @see Action#isPossible(Player) */
	public boolean isPossible(Player p){
		Room r = p.getRoom();
		if (r.getItems().isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	/* Return the Item chosen by the Player
	 * @param r the room where is the Player
	 * @return the Item chosen by the Player
	 */
	public Item chooseItem(Room r){
		System.out.println("\nItems in the room : ");
		int i;
		i=0;
		for (Item it: r.getItems()){
			System.out.println((i++)+" : "+it.toString());
		}
		int choice =  ScannerInt.readInt(i);
		return r.getItems().get(choice);
	}
	
	/* @see Action#execute(Player) */
	public void execute(Player p){
		Room r;
		Item it;
		r = p.getRoom();
		it = this.chooseItem(r);
		it.isUsedBy(p);
	}
	
/*	public static void main(String[] args) {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		UseAction a = new UseAction();
		Item it = new Gold(5);
		Item it2 = new Care(20);
		System.out.println("LifePoints: " + p.getLifePoints());
		System.out.println("Gold: " + p.getGold());
		r.addItem(it);
		r.addItem(it2);
		a.execute(p);
		System.out.println("LifePoints: " + p.getLifePoints());
		System.out.println("Gold: " + p.getGold());
	}*/
}
