package plugin;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class PluginFilterTest {
	@Test
	public void testAccept() {
		PluginFilter pf = new PluginFilter();
		File f = new File(getClass().getClassLoader().getResource("TestsPlugins").getFile());
		assertTrue(pf.accept(f, "LowerCase.class"));
	}

	@Test
	public void testDontAcceptBecauseNotClass() {
		PluginFilter pf = new PluginFilter();
		File f = new File(getClass().getClassLoader().getResource("TestsPlugins").getFile());
		assertFalse(pf.accept(f, "PasClass.txt"));
	}
	
	@Test
	public void testDontAcceptBecauseNotGoodConstructor() {
		PluginFilter pf = new PluginFilter();
		File f = new File(getClass().getClassLoader().getResource("TestsPlugins").getFile());
		assertFalse(pf.accept(f, "PluginPasBonConstructeur.class"));
	}

	@Test
	public void testDontAcceptBecauseNotGoodPackage() {
		PluginFilter pf = new PluginFilter();
		File f = new File(getClass().getClassLoader().getResource("TestsPlugins").getFile());
		assertFalse(pf.accept(f, "PluginPasBonPackage.class"));
	}
	
	@Test
	public void testDontAcceptBecauseNotInterface() {
		PluginFilter pf = new PluginFilter();
		File f = new File(getClass().getClassLoader().getResource("TestsPlugins").getFile());
		assertFalse(pf.accept(f, "PluginPasInterface.class"));
	}
}
