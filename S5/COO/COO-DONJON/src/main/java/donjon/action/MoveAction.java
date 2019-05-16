package donjon.action;

import donjon.gameCharacter.*;
import donjon.room.*;
import scanner.ScannerInt;
import donjon.Direction;
import java.util.*;

public class MoveAction implements Action {
	
	/* Create a moveAction  */
	public MoveAction(){}
	
	/* @see Action#isPossible(Player) */
	public boolean isPossible(Player p){
		Room r = p.getRoom();
		if (r.getMonsters().isEmpty() && !r.getPossibleDirections().isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* Return the direction chosen by the Player
	 * @param r the room where is the Player
	 * @return the Direction chosen by the Player
	 */
	public Direction chooseDirection(Room r){
		System.out.println("\nPossible directions : ");
		int i;
		i=0;
		List<Direction> directions = new ArrayList<Direction>();
		for (Direction d: r.getPossibleDirections()){
			System.out.println((i++)+" : "+d);
			directions.add(d);
		}
		int choice =  ScannerInt.readInt(i);
		return directions.get(choice);
	}
	
	/* @see Action#execute(Player) */
	public void execute(Player p){
		Direction d;
		Room r;
		r = p.getRoom();
		d = this.chooseDirection(r);
		p.setRoom(r.getNeighbour(d));
	}
	
/*	public static void main(String[] args) {
		Room r = new Room();
		Room r2 = new Room();
		Room r3 = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Direction dN = Direction.N;
		Direction dE = Direction.E;
		MoveAction a = new MoveAction();
		r.addNeighbour(dN, r2);
		r.addNeighbour(dE, r3);
		a.execute(p);
		System.out.println("Room at the beginning : " + r);
		System.out.println("Room at the North : " + r2);
		System.out.println("Room at the East : " + r3);
		System.out.println("Current room : " + p.getRoom());
	}*/
}
