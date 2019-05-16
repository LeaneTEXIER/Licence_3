package donjon.item;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;
import donjon.room.Room;

public abstract class ItemTest {
	
	public abstract Item createItem1();
	public abstract Item createItem2();
	public abstract int getValue(Item i);
	public abstract int getPValue(Player p);
	
	@Test
	public void testCreate(){
		assertNotNull(this.createItem1());
	}

	@Test
	public void testIsUsedBy() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		Item i1 = this.createItem1();
		Item i2 = this.createItem2();
		r.addItem(i1);
		r.addItem(i2);
		int i = this.getPValue(p);
		assertTrue(r.getItems().contains(i1));
		assertTrue(r.getItems().contains(i2));
		i1.isUsedBy(p);
		i+=this.getValue(i1);
		assertEquals(i, this.getPValue(p));
		assertFalse(r.getItems().contains(i1));
		i2.isUsedBy(p);
		i+=this.getValue(i2);
		assertEquals(i, this.getPValue(p));
		assertFalse(r.getItems().contains(i2));
	}
	
}
