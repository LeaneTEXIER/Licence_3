package donjon.action;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;
import donjon.room.Room;

public abstract class ActionTest {
	
	public abstract Action createAction();
	public abstract void addToTrue(Room r);
	
	@Test
	public void testCreate(){
		assertNotNull(this.createAction());
	}

	@Test
	public void testIsPossible() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Action a = this.createAction();
		assertFalse(a.isPossible(p));
		this.addToTrue(r);
		assertTrue(a.isPossible(p));
	}

}
