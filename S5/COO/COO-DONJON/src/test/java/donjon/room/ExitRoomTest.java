package donjon.room;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExitRoomTest extends RoomTest{

	@Test
	public void testRoomExit() {
		Room r = new ExitRoom();
		assertNotNull(r);
	}


	@Test
	public void testIsExit() {
		Room r = new ExitRoom();
		assertTrue(r.isExit());
	}

}
