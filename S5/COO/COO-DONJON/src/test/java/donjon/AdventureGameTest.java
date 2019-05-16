package donjon;

import static org.junit.Assert.*;

import org.junit.Test;
import donjon.room.*;
import donjon.gameCharacter.*;
import donjon.item.*;

public class AdventureGameTest {

	@Test
	public void testAdventureGame() {
		assertNotNull(new AdventureGame(new Room()));
	}

	@Test
	public void testCurrentRoom() {
		Room r = new Room();
		AdventureGame ag = new AdventureGame(r);
		assertSame(r, ag.currentRoom());
	}

	@Test
	public void testGetPlayer() {
		Room r = new Room();
		AdventureGame ag = new AdventureGame(r);
		assertNotNull(ag.getPlayer());
	}

	@Test
	public void testAddMonster() {
		Room r = new Room();
		AdventureGame ag = new AdventureGame(r);
		assertTrue(ag.currentRoom().getMonsters().isEmpty());
		Monster m = new Monster();
		ag.addMonster(m, r);
		assertTrue(ag.currentRoom().getMonsters().contains(m));
	}

	@Test
	public void testAddItem() {
		Room r = new Room();
		AdventureGame ag = new AdventureGame(r);
		assertTrue(ag.currentRoom().getItems().isEmpty());
		Item i = new Gold(5);
		ag.addItem(i, r);
		assertTrue(ag.currentRoom().getItems().contains(i));
	}

	@Test
	public void testIsFinished1() {
		Room r = new Room();
		AdventureGame ag = new AdventureGame(r);
		assertFalse(ag.isFinished());
		ag.getPlayer().removeLifePoints(ag.getPlayer().getLifePoints());
		assertTrue(ag.isFinished());
	}
	
	@Test
	public void testIsFinished2() {
		Room r = new ExitRoom();
		AdventureGame ag = new AdventureGame(r);
		assertTrue(ag.isFinished());
	}
}
