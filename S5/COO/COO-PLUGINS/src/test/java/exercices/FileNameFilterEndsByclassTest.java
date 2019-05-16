package exercices;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class FileNameFilterEndsByclassTest {

	@Test
	public void testConstructor(){
		assertNotNull(new FileNameFilterEndsByclass());
	}
	
	@Test
	public void testAcceptTrue() {
		FileNameFilterEndsByclass filter = new FileNameFilterEndsByclass();
		File f = new File("test");
		assertTrue(filter.accept(f, "jeTest.class"));
	}
	
	@Test
	public void test1AcceptFalse() {
		FileNameFilterEndsByclass filter = new FileNameFilterEndsByclass();
		File f = new File("test");
		assertFalse(filter.accept(f, "jeTest.clas"));
	}
	
	@Test
	public void test2AcceptFalse() {
		FileNameFilterEndsByclass filter = new FileNameFilterEndsByclass();
		File f = new File("test");
		assertFalse(filter.accept(f, "jeTest"));
	}

}
