package donjon.item;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;
import donjon.room.Room;

public class OneArmedBanditTest extends ItemTest{
	
	public Item createItem1(){
		return new OneArmedBandit(3);
	}
	
	public Item createItem2(){
		return new OneArmedBandit(5);
	}
	
	public int getValue(Item i){return 0;}
	
	public int getPValue(Player p){return 0;}

	@Test
	public void testGetNbrGold() {
		OneArmedBandit o = new OneArmedBandit(30);
		OneArmedBandit o2 = new OneArmedBandit(21);
		assertSame(30, o.getNbrGold());
		assertSame(21, o2.getNbrGold());
	}

	@Test
	public void testGetItems() {
		OneArmedBandit o = new OneArmedBandit(30);
		assertFalse(o.getItems().isEmpty());
	}
	
	@Test
	public void testIsUsedBy() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		OneArmedBandit o1 = new OneArmedBandit(p.getGold()-1);
		r.addItem(o1);
		int lp = p.getLifePoints();
		int g = p.getGold();
		int s = p.getStrength();
		assertTrue(g>=o1.getNbrGold());
		o1.isUsedBy(p);
		assertTrue(lp!=p.getLifePoints() || g!=p.getGold() || s!=p.getStrength());
		assertFalse(r.getItems().contains(o1));
	}

	@Test
	public void testIsUsedByNotEnoughMoney() {
		Room r = new Room();
		Player p = new Player();
		// Put the player in a room
		p.setRoom(r);
		OneArmedBandit o1 = new OneArmedBandit(p.getGold()+1);
		r.addItem(o1);
		int lp = p.getLifePoints();
		int g = p.getGold();
		int s = p.getStrength();
		assertTrue(g<o1.getNbrGold());
		o1.isUsedBy(p);
		assertTrue(lp==p.getLifePoints() && g==p.getGold() && s==p.getStrength());
		assertFalse(r.getItems().contains(o1));
	}
}
