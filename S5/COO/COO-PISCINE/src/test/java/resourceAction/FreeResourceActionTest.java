package resourceAction;

import static org.junit.Assert.*;
import org.junit.Test;
import actions.ActionFinishedException;
import resourceUser.ResourceUser;

public class FreeResourceActionTest extends ResourceActionTest{
	
	public ResourceAction<MockResource> createActionArg(ResourceUser<MockResource> rUser, MockResourcePool rPool) {
		/* Create a TakeResourceAction */
		ResourceAction<MockResource> at = new TakeResourceAction<MockResource>(rUser, rPool);
		/* Do an action of the takeResourceAction to can provide a resource */
		try {
			at.doStep();
		} catch (ActionFinishedException e) {
			System.out.println("Can take an object");
			fail();
		}
		return new FreeResourceAction<MockResource>(rUser, rPool);
	}

	@Test
	public void testStepEffectOKAndStopCondition() throws ActionFinishedException {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		TakeResourceAction<MockResource> at = new TakeResourceAction<MockResource>(rUser, rPool);
		at.doStep();
		FreeResourceAction<MockResource> af = new FreeResourceAction<MockResource>(rUser, rPool);
		assertNotNull(af.getRUser().getResource());
		af.stepEffect();
		assertNull(af.getRUser().getResource());
		assertTrue(af.stopCondition());
	}
	
	@Test
	public void testStepEffectNoTakeResourceBeforeAndStopCondition() throws ActionFinishedException {
		ResourceUser<MockResource> rUser = new ResourceUser<MockResource>("test");
		MockResourcePool rPool = new MockResourcePool(3);
		FreeResourceAction<MockResource> af = new FreeResourceAction<MockResource>(rUser, rPool);
		af.stepEffect();
		assertNull(af.getRUser().getResource());
		assertFalse(af.stopCondition());
	}	
	
}
