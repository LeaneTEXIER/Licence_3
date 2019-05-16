package plugins;

import static org.junit.Assert.*;
import org.junit.Test;
import plugin.Plugin;

public class SignatureTest {

	@Test
	public void testTransform() {
		Plugin p = new Signature();
		String s = "aB2";
		String sign = "Léane TEXIER";
		String t = p.transform(s);
		String r = t.substring(t.length()-sign.length(), t.length());
		assertEquals(r, sign);
	}

}
