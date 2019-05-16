package donjon.gameCharacter;

import static org.junit.Assert.*;

import org.junit.Test;

import donjon.item.Gold;
import donjon.room.Room;

public class MonsterTest extends GameCharacterTest{
	
	public GameCharacter createCharacter(){
		return new Monster();
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
	public void testDie() {
		Room r = new Room();
		Monster m = new Monster();
		r.addMonster(m);
		Gold g = new Gold(m.getGold());
		assertTrue(r.getItems().isEmpty());
		assertTrue(r.getMonsters().contains(m));
		m.die();
		assertSame(0, m.getGold());
		assertSame(g.getClass(), r.getItems().get(0).getClass());
		Gold g2 = (Gold)r.getItems().get(0);
		assertSame(g.getNbrGold(), g2.getNbrGold());
		assertFalse(r.getMonsters().contains(m));
	}

}
