package donjon.room;


import static org.junit.Assert.*;
import donjon.room.Room;
import donjon.gameCharacter.*;
import donjon.Direction;
import donjon.item.*;
import org.junit.Test;

public class RoomTest {

	@Test
	public void testRoom() {
		Room r = new Room();
		assertNotNull(r);
	}

	@Test
	public void testAddMonsterAndGetMonsters() {
		Room r = new Room();
		assertTrue(r.getMonsters().isEmpty());
		Monster m1 = new Monster();
		r.addMonster(m1);
		assertTrue(r.getMonsters().contains(m1));
		Monster m2 = new Monster();	
		r.addMonster(m2);
		assertTrue(r.getMonsters().contains(m1));
		assertTrue(r.getMonsters().contains(m2));
	}
	
	@Test
	public void testRemoveMonster(){
		Room r = new Room();
		Monster m1 = new Monster();
		Monster m2 = new Monster();
		r.addMonster(m1);
		r.addMonster(m2);
		assertTrue(r.getMonsters().contains(m1));
		assertTrue(r.getMonsters().contains(m2));
		r.removeMonster(m1);
		assertFalse(r.getMonsters().contains(m1));
		r.removeMonster(m2);
		assertFalse(r.getMonsters().contains(m2));
		assertTrue(r.getMonsters().isEmpty());
	}

	@Test
	public void testAddItemAndGetItems() {
		Room r = new Room();
		Item i1 = new Gold(5);
		Item i2 = new Care(30);
		assertTrue(r.getItems().isEmpty());
		r.addItem(i1);
		assertTrue(r.getItems().contains(i1));
		r.addItem(i2);
		assertTrue(r.getItems().contains(i1));
		assertTrue(r.getItems().contains(i2));
	}
	
	@Test
	public void testRemoveItem(){
		Room r = new Room();
		Item i1 = new Gold(5);
		Item i2 = new Care(30);
		r.addItem(i1);
		r.addItem(i2);
		assertTrue(r.getItems().contains(i1));
		assertTrue(r.getItems().contains(i2));
		r.removeItem(i1);
		assertFalse(r.getItems().contains(i1));
		r.removeItem(i2);
		assertFalse(r.getItems().contains(i2));
		assertTrue(r.getItems().isEmpty());
	}

	@Test
	public void testAddNeighbourAndGetNeighbour() {
		Room r = new Room();
		Room r2 = new Room();
		Room r3 = new Room();
		Direction dN = Direction.N;
		Direction dE = Direction.E;
		assertNull(r.getNeighbour(dN));
		assertNull(r2.getNeighbour(dE));
		assertNull(r2.getNeighbour(dN.opposite()));
		assertNull(r3.getNeighbour(dE.opposite()));
		r.addNeighbour(dN, r2);
		assertSame(r2, r.getNeighbour(dN));
		assertSame(r, r2.getNeighbour(dN.opposite()));
		r2.addNeighbour(dE, r3);
		assertSame(r3, r2.getNeighbour(dE));
		assertSame(r2, r3.getNeighbour(dE.opposite()));
	}

	@Test
	public void testIsExit() {
		Room r = new Room();
		assertFalse(r.isExit());
	}
	
	@Test
	public void testGetPossibleDirections() {
		Room r = new Room();
		Room r2 = new Room();
		Room r3 = new Room();
		Direction dN = Direction.N;
		Direction dS = Direction.S;
		assertTrue(r.getPossibleDirections().isEmpty());
		r.addNeighbour(dN, r2);
		assertTrue(r.getPossibleDirections().contains(dN));
		r.addNeighbour(dS, r3);
		assertTrue(r.getPossibleDirections().contains(dN));
		assertTrue(r.getPossibleDirections().contains(dS));
	}

}
