package donjon.gameCharacter;

import static org.junit.Assert.*;
import donjon.room.*;

import org.junit.Test;

public abstract class GameCharacterTest {
	
	public abstract GameCharacter createCharacter();
	public abstract int getLifePointsCharacter(GameCharacter c);
	public abstract int getStrengthCharacter(GameCharacter c);
	public abstract int getGoldCharacter(GameCharacter c);
	
	@Test
	public void testCreate(){
		assertNotNull(this.createCharacter());
	}
	
	@Test
	public void testGetRoom() {
		GameCharacter c = this.createCharacter();
		assertSame(null, c.getRoom());
	}

	@Test
	public void testSetRoom() {
		Room r = new Room();
		GameCharacter c = this.createCharacter();
		assertSame(null, c.getRoom());
		c.setRoom(r);
		assertSame(r, c.getRoom());
	}

	@Test
	public void testGetAndAddLifePoints() {
		GameCharacter c = this.createCharacter();
		int i = this.getLifePointsCharacter(c);
		assertEquals(i, c.getLifePoints());
		c.addLifePoints(20);
		i+=20;
		assertEquals(i, c.getLifePoints());
		c.addLifePoints(10);
		i+=10;
		assertEquals(i, c.getLifePoints());
	}

	@Test
	public void testRemoveLifePoints() {
		GameCharacter c = this.createCharacter();
		int i = this.getLifePointsCharacter(c);
		assertSame(i, c.getLifePoints());
		c.removeLifePoints(20);
		i-=20;
		if (i<0){i=0;};
		assertSame(i, c.getLifePoints());
		c.removeLifePoints(10);
		i-=10;
		if (i<0){i=0;};
		assertSame(i, c.getLifePoints());
	}

	@Test
	public void testGetAndAddStrength() {
		GameCharacter c = this.createCharacter();
		int i = this.getStrengthCharacter(c);
		assertSame(i, c.getStrength());
		c.addStrength(20);
		i+=20;
		assertSame(i, c.getStrength());
		c.addStrength(10);
		i+=10;
		assertSame(i, c.getStrength());
	}

	@Test
	public void testRemoveStrength() {
		GameCharacter c = this.createCharacter();
		int i = this.getStrengthCharacter(c);
		assertSame(i, c.getStrength());
		c.removeStrength(20);
		i-=20;
		if (i<0){i=0;};
		assertSame(i, c.getStrength());
		c.removeStrength(10);
		i-=10;
		if (i<0){i=0;};
		assertSame(i, c.getStrength());
	}

	@Test
	public void testGetAndAddGold() {
		GameCharacter c = this.createCharacter();
		int i = this.getGoldCharacter(c);
		assertSame(i, c.getGold());
		c.addGold(20);
		i+=20;
		assertSame(i, c.getGold());
		c.addGold(10);
		i+=10;
		assertSame(i, c.getGold());
	}

	@Test
	public void testRemoveGold() {
		GameCharacter c = this.createCharacter();
		int i = this.getGoldCharacter(c);
		assertSame(i, c.getGold());
		c.removeGold(20);
		i-=20;
		if (i<0){i=0;};
		assertSame(i, c.getGold());
		c.removeGold(10);
		i-=10;
		if (i<0){i=0;};
		assertSame(i, c.getGold());
	}

	@Test
	public void testAttack() {
		GameCharacter c = this.createCharacter();
		GameCharacter c2 = this.createCharacter();
		int l = c2.getLifePoints();
		int s = c.getStrength();
		c.attack(c2);
		l-=s;
		if (l<0){l=0;}
		assertSame(l, c2.getLifePoints());
	}

	@Test
	public void testIsDead() {
		GameCharacter c = this.createCharacter();
		assertFalse(c.isDead());
		int i = this.getLifePointsCharacter(c);
		c.removeLifePoints(i);
		assertTrue(c.isDead());
	}

}
