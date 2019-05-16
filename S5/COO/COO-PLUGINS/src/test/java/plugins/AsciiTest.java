package plugins;

import static org.junit.Assert.*;
import org.junit.Test;
import plugin.Plugin;

public class AsciiTest {

	@Test
	public void testTransform() {
		Plugin p = new Ascii();
		String s = "aB2";
		// code ASCII a: 97
		// code ASCII B: 66
		// code ASCII 2: 50
		String t = "97/66/50/";
		assertEquals(t, p.transform(s));
	}

}
