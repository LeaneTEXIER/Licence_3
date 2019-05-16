package donjon.item;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;


public class StrengthTest extends ItemTest{
	
	public Item createItem1(){
		return new Strength(30);
	}
	
	public Item createItem2(){
		return new Strength(21);
	}
	
	public int getValue(Item i){
		Strength c = (Strength)i;
		return c.getNbrStrength();
	};
	
	public int getPValue(Player p){
		return p.getStrength();
	}

	@Test
	public void testGetNbrStrength() {
		Strength c = new Strength(30);
		Strength c2 = new Strength(21);
		assertSame(30, c.getNbrStrength());
		assertSame(21, c2.getNbrStrength());
	}

}
