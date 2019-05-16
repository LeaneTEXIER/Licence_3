package plugins;

import static org.junit.Assert.*;
import org.junit.Test;
import plugin.Plugin;

public class UpperCaseTest {

	@Test
	public void testTransform() {
		Plugin p = new UpperCase();
		String s = "aB2";
		assertEquals(s.toUpperCase(), p.transform(s));
	}

}
