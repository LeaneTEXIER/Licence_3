package donjon.action;

import static org.junit.Assert.*;

import org.junit.Test;
import donjon.gameCharacter.*;
import donjon.room.*;

public class SeeActionTest extends ActionTest{
	
	public Action createAction(){
			return new SeeAction();
	}

	public void addToTrue(Room r){}

	@Test
	public void testIsPossible() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Action a = new SeeAction();
		assertTrue(a.isPossible(p));
	}
}
