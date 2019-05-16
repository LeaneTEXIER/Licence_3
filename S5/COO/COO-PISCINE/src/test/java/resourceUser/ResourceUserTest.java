package resourceUser;

import static org.junit.Assert.*;
import org.junit.Test;
import resource.*;

public class ResourceUserTest {
	
	/* Création du Mock */
	protected class MockResource implements Resource{
		public String description() {
			return "MockResource";
		}
	}
	
	@Test
	public void testCreate(){
		ResourceUser<MockResource> ru = new ResourceUser<MockResource>("test");
		assertNotNull(ru);
	}
	
	@Test
	public void testGetName(){
		ResourceUser<MockResource> ru = new ResourceUser<MockResource>("test");
		assertSame("test", ru.getName());
	}

	@Test
	public void testGetAndSetResource() {
		MockResource r = new MockResource();
		ResourceUser<MockResource> ru = new ResourceUser<MockResource>("test");
		assertNull(ru.getResource());
		ru.setResource(r);
		assertSame(r, ru.getResource());
	}

	@Test
	public void testResetResource() {
		MockResource r = new MockResource();
		ResourceUser<MockResource> ru = new ResourceUser<MockResource>("test");
		ru.setResource(r);
		ru.resetResource();
		assertNull(ru.getResource());
	}

}
