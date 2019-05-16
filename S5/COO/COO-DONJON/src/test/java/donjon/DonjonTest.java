package donjon;

import static org.junit.Assert.*;

import org.junit.Test;
import donjon.room.*;

public class DonjonTest {

	@Test
	public void testDonjon() {
		assertNotNull(new Donjon(new Room()));
	}

}
