package donjon.item;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.gameCharacter.Player;

public class GoldTest extends ItemTest{
	
	public Item createItem1(){
		return new Gold(30);
	}
	
	public Item createItem2(){
		return new Gold(21);
	}
	
	public int getValue(Item i){
		Gold g = (Gold)i;
		return g.getNbrGold();
	};
	
	public int getPValue(Player p){
		return p.getGold();
	}
	
	@Test
	public void testGetNbrGold() {
		Gold c = new Gold(30);
		Gold c2 = new Gold(21);
		assertSame(30, c.getNbrGold());
		assertSame(21, c2.getNbrGold());
	}

}
