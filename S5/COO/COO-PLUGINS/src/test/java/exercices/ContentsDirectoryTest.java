package exercices;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ContentsDirectoryTest {

	@Test
	public void testtestConstructor() {
		assertNotNull(new ContentsDirectory("path"));
	}

	@Test
	public void testGetPath() {
		String p = "path";
		ContentsDirectory c = new ContentsDirectory(p);
		assertEquals(new File(p), c.getPath());
	}
}
