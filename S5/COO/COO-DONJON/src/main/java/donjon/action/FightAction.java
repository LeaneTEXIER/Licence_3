package donjon.action;

import donjon.gameCharacter.*;
import donjon.room.Room;
import scanner.ScannerInt;

public class FightAction implements Action {
	
	/* Create a fightAction */
	public FightAction(){}
	
	/* @see Action#isPossible(Player) */
	public boolean isPossible(Player p){
		Room r = p.getRoom();
		if (r.getMonsters().isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	/* Return the monster chosen by the Player
	 * @param r the room where is the Player
	 * @return the monster chosen by the Player
	 */
	public Monster chooseMonster(Room r){
		System.out.println("\nMonsters in the room : ");
		int i;
		i=0;
		for (Monster m: r.getMonsters()){
			System.out.println((i++)+" : Monster with "+ m.getLifePoints()+" lifepoints.");
		}
		int choice =  ScannerInt.readInt(i);
		return r.getMonsters().get(choice);
	}
	
	/* @see Action#execute(Player) */
	public void execute(Player p){
		Room r;
		GameCharacter c, c2, ctrans;
		r = p.getRoom();
		c = this.chooseMonster(r);
		c2 = p;
		while (!(c2.isDead()) && !(c.isDead())){
			c2.attack(c);
			ctrans = c2;
			c2 = c;
			c= ctrans;
		}
	}
	
	/*public static void main(String[] args) {
		Room r = new Room();
		Monster m = new Monster();
		r.addMonster(m);
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		FightAction a = new FightAction();
		a.execute(p);
	}*/
}
