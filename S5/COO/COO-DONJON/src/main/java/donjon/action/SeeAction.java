package donjon.action;

import donjon.gameCharacter.Player;
import donjon.room.*;

/*import donjon.gameCharacter.Monster;
import donjon.Direction;
import donjon.item.*;*/

public class SeeAction implements Action {
	
	/* Create a seeAction */
	public SeeAction(){};
	
	/* @see Action#isPossible(Player) */
	public boolean isPossible(Player p){
		return true;
	}
	
	/* @see Action#execute(Player) */
	public void execute(Player p){
		Room r = p.getRoom();
		/* Issues possibles */
		System.out.println("The possible issues are:");
		for (Object direction: r.getPossibleDirections()){
			System.out.println("* "+ direction);
		}
		/* Monstres presents */
		System.out.println("There is/are " + r.getMonsters().size() + " monster(s) in the room.");
		/* Items presents */
		if (r.getItems().isEmpty()){
			System.out.println("There is no item in the room.");
		}
		else{
			System.out.println("The items presents are:");
			for (Object item: r.getItems()){
				System.out.println("* "+item.toString());
			}
		}	
	}
	/*
	public static void main(String[] args) {
		Room r = new Room();
		Room r2 = new Room();
		Room r3 = new Room();
		Direction dN = Direction.N;
		Direction dS = Direction.S;
		Monster m = new Monster();
		Monster m2 = new Monster();
		Item i = new Gold(5);
		Item i2 = new Care(30);
		r.addNeighbour(dN, r2);
		r.addNeighbour(dS, r3);
		r.addMonster(m);
		r.addMonster(m2);
		r.addItem(i);
		r.addItem(i2);
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Action see = new SeeAction();
		see.execute(p);
	}*/
}
