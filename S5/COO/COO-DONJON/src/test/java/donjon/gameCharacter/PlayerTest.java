package donjon.gameCharacter;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.room.Room;
import donjon.Direction;
import donjon.action.*;

public class PlayerTest extends GameCharacterTest{
	
	public GameCharacter createCharacter(){
		return new Player();
	}
	
	public int getLifePointsCharacter(GameCharacter c){
		return c.getLifePoints();
	}
	
	public int getStrengthCharacter(GameCharacter c){
		return c.getStrength();
	}
	
	public int getGoldCharacter(GameCharacter c){
		return c.getGold();
	}

	@Test
	public void testAddAndGetAction() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Action a = new SeeAction();
		Action a2 = new MoveAction();
		assertTrue(p.getActions().isEmpty());
		p.addAction(a);
		assertTrue(p.getActions().contains(a));
		p.addAction(a2);
		assertTrue(p.getActions().contains(a2));
	}

	@Test
	public void testGetPossibleActions() {
		Room r = new Room();
		Room r2 = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Action a = new SeeAction();
		Action a2 = new MoveAction();
		Direction dN = Direction.N;
		r.addNeighbour(dN, r2);
		assertTrue(p.getPossibleActions().isEmpty());
		p.addAction(a);
		assertTrue(p.getPossibleActions().contains(a));
		p.addAction(a2);
		assertTrue(p.getPossibleActions().contains(a2));
	}
}
