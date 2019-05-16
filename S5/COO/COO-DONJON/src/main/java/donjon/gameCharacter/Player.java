package donjon.gameCharacter;

import java.util.*;

/*import donjon.Direction;
import donjon.room.*;*/
import donjon.action.*;
import scanner.ScannerInt;

public class Player extends GameCharacter{
	/*Attributs*/
	protected List<Action> actions;
	
	/*Create a player */
	public Player(){
		super(null, 100, 25, 30);
		this.actions = new ArrayList<Action>();
	}
	
	/* Add an action possible to do by the player
	 * @param m the Action to add
	*/
	public void addAction(Action a){
		this.actions.add(a);
	}
	
	/* Return the list of the actions of the player
	 * @return the list of the actions
	*/
	public List<Action> getActions(){
		return this.actions;
	}
	
	/* Return the list of the possible actions of the player
	 * @return the list of the possible actions
	*/
	public List<Action> getPossibleActions(){
		List<Action> PossibleActions = new ArrayList<Action>();
		for (Action a: this.getActions()){
			if (a.isPossible(this)){
				PossibleActions.add(a);
			}
		}
		return PossibleActions;
	}
	
	/* Return the action chosen by the player
	 * @return the action chosen by the player
	 */
	public Action chooseAction(){
		System.out.println("\nActions possibles : ");
		int i;
		i=0;
		for (Action a: this.getPossibleActions()){
			System.out.println((i++)+" : "+a.toString().split("donjon.action.")[1].split("@")[0]);
		}
		int choice =  ScannerInt.readInt(i);
		return this.getPossibleActions().get(choice);
	}
	
	/* Execute the action choose by the player */
	public void act(){
		Action a = this.chooseAction();
		a.execute(this);
	}
	
	/* When the player dies, the game is over */
	public void die(){
		System.out.println("\nYou are dead!");
	}
	
/*	public static void main(String[] args) {
		Room r = new Room();
		Room r2 = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Direction dN = Direction.N;
		Action a = new SeeAction();
		Action a2 = new MoveAction();
		r.addNeighbour(dN, r2);
		p.addAction(a);
		p.addAction(a2);s
		p.act();
	}*/
}
