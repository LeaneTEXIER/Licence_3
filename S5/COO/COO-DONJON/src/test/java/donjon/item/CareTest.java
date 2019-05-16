package donjon.item;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;

public class CareTest extends ItemTest{
	
	public Item createItem1(){
		return new Care(30);
	}
	
	public Item createItem2(){
		return new Care(21);
	}
	
	public int getValue(Item i){
		Care c = (Care)i;
		return c.getNbrCare();
	}
	
	public int getPValue(Player p){
		return p.getLifePoints();
	}

	@Test
	public void testGetNbrCare() {
		Care c = new Care(30);
		Care c2 = new Care(21);
		assertSame(30, c.getNbrCare());
		assertSame(21, c2.getNbrCare());
	}
}
