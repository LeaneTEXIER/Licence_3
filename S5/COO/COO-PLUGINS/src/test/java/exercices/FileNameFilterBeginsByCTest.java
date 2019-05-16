package exercices;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class FileNameFilterBeginsByCTest {
	
	@Test
	public void testConstructor(){
		assertNotNull(new FileNameFilterBeginsByC());
	}

	@Test
	public void testAcceptTrue() {
		FileNameFilterBeginsByC filter = new FileNameFilterBeginsByC();
		File f = new File("test");
		assertTrue(filter.accept(f, "Coucou"));
	}
	
	@Test
	public void test1AcceptFalse() {
		FileNameFilterBeginsByC filter = new FileNameFilterBeginsByC();
		File f = new File("test");
		assertFalse(filter.accept(f, "coucou"));
	}
	
	@Test
	public void test2AcceptFalse() {
		FileNameFilterBeginsByC filter = new FileNameFilterBeginsByC();
		File f = new File("test");
		assertFalse(filter.accept(f, "abC"));
	}

}
