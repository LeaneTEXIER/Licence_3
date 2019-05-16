package resourceAction;

import static org.junit.Assert.*;
import org.junit.Test;
import actions.*;
import resource.Resource;
import resourcePool.ResourcePool;
import resourceUser.ResourceUser;

public abstract class ResourceActionTest extends ActionTest {
	
	public abstract ResourceAction<MockResource> createActionArg(ResourceUser<MockResource> rUser, MockResourcePool rPool);
		
	/* Création du Mock pour la resource */
	protected class MockResource implements Resource{
		public String description() {
			return "MockResource";
		}
	}
	
	/* Création du mock pour la resourcePool */
	protected class MockResourcePool extends ResourcePool<MockResource>{
		public MockResourcePool(int nbResources) {
			super(nbResources);
		}

		public MockResource createResource() {
			return new MockResource();
		}	
	}
	
	
	protected Action createAction() {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		return this.createActionArg(rUser, rPool);
	}
	
	
	
	@Test
	public void testCreateAndGettersRUserAndRPool() {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		ResourceAction<MockResource> a = this.createActionArg(rUser, rPool);
		assertSame(rUser, a.getRUser());
		assertSame(rPool, a.getRPool());
	}
	
}
