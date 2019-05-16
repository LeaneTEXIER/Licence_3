package plugins;

import static org.junit.Assert.*;
import org.junit.Test;
import plugin.Plugin;

public class ReverseTextTest {

	@Test
	public void testTransform() {
		Plugin p = new ReverseText();
		String s = "aB2";
		String t = "2Ba";
		assertEquals(t, p.transform(s));
	}

}
