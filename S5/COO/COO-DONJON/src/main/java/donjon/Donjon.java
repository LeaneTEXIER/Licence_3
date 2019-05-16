package donjon;

import donjon.gameCharacter.Monster;
import donjon.item.*;
import donjon.room.*;

public class Donjon {
	
	/*Create a donjon
	 * @param currentRoom the Room where the donjon beggins
	 */
	public Donjon(Room currentRoom){
		/* Add rooms*/
		Direction dW = Direction.W;
		Room r = new Room();
		ExitRoom rfin = new ExitRoom();
		currentRoom.addNeighbour(dW, r);
		r.addNeighbour(dW, rfin);
		/*Add monster and items in room 1 */
		Monster m = new Monster();
		Monster m2 = new Monster();
		Item it = new Gold(5);
		Item it2 = new Care(20);
		currentRoom.addMonster(m);
		currentRoom.addMonster(m2);
		currentRoom.addItem(it);
		currentRoom.addItem(it2);
		/*Add monster and items in room 2 */
		Monster m3 = new Monster();
		Item it3 = new Strength(10);
		Item it4 = new OneArmedBandit(30);
		r.addMonster(m3);
		r.addItem(it3);
		r.addItem(it4);
	}
}
