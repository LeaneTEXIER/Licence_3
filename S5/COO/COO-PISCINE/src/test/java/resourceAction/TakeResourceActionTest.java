package resourceAction;

import static org.junit.Assert.*;
import org.junit.Test;
import resourceUser.ResourceUser;

public class TakeResourceActionTest extends ResourceActionTest{
	
	public ResourceAction<MockResource> createActionArg(ResourceUser<MockResource> rUser, MockResourcePool rPool) {
		return new TakeResourceAction<MockResource>(rUser, rPool);
	}

	@Test
	public void testStepEffectOKAndStopCondition() {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		TakeResourceAction<MockResource> a = new TakeResourceAction<MockResource>(rUser, rPool);
		assertNull(a.getRUser().getResource());
		a.stepEffect();
		assertNotNull(a.getRUser().getResource());
		assertTrue(a.stopCondition());
	}
	
	@Test
	public void testStepEffectNoMoreResourceAndStopCondition() {
		/* Creation users */
		ResourceUser<MockResource> rUser1 = new ResourceUser<MockResource>("test");
		ResourceUser<MockResource> rUser2 = new ResourceUser<MockResource>("test");
		ResourceUser<MockResource> rUser3 = new ResourceUser<MockResource>("test");
		ResourceUser<MockResource> rUser4 = new ResourceUser<MockResource>("test");
		/* Creation Pool */
		MockResourcePool rPool = new MockResourcePool(3);
		/* Creation takeAction of the different users */
		TakeResourceAction<MockResource> a1 = new TakeResourceAction<MockResource>(rUser1, rPool);
		TakeResourceAction<MockResource> a2 = new TakeResourceAction<MockResource>(rUser2, rPool);
		TakeResourceAction<MockResource> a3 = new TakeResourceAction<MockResource>(rUser3, rPool);
		TakeResourceAction<MockResource> a4 = new TakeResourceAction<MockResource>(rUser4, rPool);
		/* The 3 first takes the 3 resources available */
		a1.stepEffect();
		a2.stepEffect();
		a3.stepEffect();
		/* Do nothing because there is no resource */
		assertNull(a4.getRUser().getResource());
		a4.stepEffect();
		assertNull(a4.getRUser().getResource());
		assertFalse(a4.stopCondition());
	}
	
	@Test
	public void testStepEffectUserTakesAlreadyAResourceAndStopCondition() {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		TakeResourceAction<MockResource> a = new TakeResourceAction<MockResource>(rUser, rPool);
		a.stepEffect();
		a.stepEffect();
		assertNotNull(a.getRUser().getResource());
		assertFalse(a.stopCondition());
	}

}
