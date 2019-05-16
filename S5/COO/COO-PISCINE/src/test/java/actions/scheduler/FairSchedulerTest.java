package actions.scheduler;

import static org.junit.Assert.*;
import org.junit.Test;
import actions.ActionFinishedException;
import actions.ActionState;


public class FairSchedulerTest extends SchedulerTest{
	
	public Scheduler createScheduler(){
		return new FairScheduler();
	};	

	@Test
	public void testGiveNextAction() {
		Scheduler s = this.createSchedulerWith3Actions();
		try {
			s.doStep();
		} catch (ActionFinishedException e) {
			System.out.println("Error action finished");
			fail();
		}
		assertSame(s.getActions().get(1), s.giveNextAction());
	}

	@Test
	public void testFairScheduler() {
		assertNotNull(this.createScheduler());
	}

	@Test
	public void testGetNextAction() {
		FairScheduler s = (FairScheduler) this.createSchedulerWith3Actions();
		//assertSame(0, s.getCurrentAction());
		try {
			s.doStep();
		} catch (ActionFinishedException e) {
			System.out.println("Error action finished");
			fail();
		}
		//assertSame(1, s.getCurrentAction());
	}
	
	@Test
	public void testFairSchedulerDoStep() throws ActionFinishedException, SchedulerStartedException{
		/* Create actions and sequential scheduler*/
		MockAction m1 = new MockAction();
		MockAction m2 = new MockAction();
		MockAction m3 = new MockAction();
		FairScheduler s = new FairScheduler();
		/* Add actions */
		s.addAction(m1);
		s.addAction(m2);
		s.addAction(m3);
		/*Tests */
		assertEquals(ActionState.READY, m1.getState());
		assertEquals(ActionState.READY, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.IN_PROGRESS, m1.getState());
		assertEquals(ActionState.READY, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.IN_PROGRESS, m1.getState());
		assertEquals(ActionState.IN_PROGRESS, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.IN_PROGRESS, m1.getState());
		assertEquals(ActionState.IN_PROGRESS, m2.getState());
		assertEquals(ActionState.IN_PROGRESS, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.IN_PROGRESS, m2.getState());
		assertEquals(ActionState.IN_PROGRESS, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.FINISHED, m2.getState());
		assertEquals(ActionState.IN_PROGRESS, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.FINISHED, m2.getState());
		assertEquals(ActionState.FINISHED, m3.getState());
	}
}
