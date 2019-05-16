package files;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileEventTest {

	@Test
	public void testConstructor() {
		assertNotNull(new FileEvent("nameTest"));
	}

	@Test
	public void testGetFileName() {
		String p = "name";
		FileEvent fe = new FileEvent(p);
		assertSame(p, fe.getFileName());
	}

}
