package plugins;

import static org.junit.Assert.*;
import org.junit.Test;
import plugin.Plugin;

public class LowerCaseTest {

	@Test
	public void testTransform() {
		Plugin p = new LowerCase();
		String s = "aB2";
		assertEquals(s.toLowerCase(), p.transform(s));
	}

}