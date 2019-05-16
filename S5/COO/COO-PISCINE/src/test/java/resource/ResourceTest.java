package resource;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class ResourceTest {
	
	public abstract Resource createResource();
	
	@Test
	public void testCreate(){
		assertNotNull(this.createResource());
	}
}
