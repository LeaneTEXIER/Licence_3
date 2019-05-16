package actions.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;
import actions.ActionFinishedException;
import actions.ActionState;

public class SequentialSchedulerTest extends SchedulerTest{
	
	public Scheduler createScheduler(){
		return new SequentialScheduler();
	}

	@Test
	public void testGiveNextAction() throws ActionFinishedException {
		Scheduler s = this.createSchedulerWith3Actions();
		assertSame(s.getActions().get(0), s.giveNextAction());
		s.doStep();
		assertSame(s.getActions().get(0), s.giveNextAction());
	}

	@Test
	public void testSequentialSchedulerDoStep() throws ActionFinishedException, SchedulerStartedException{
		/* Create actions and sequential scheduler*/
		MockAction m1 = new MockAction();
		MockAction m2 = new MockAction();
		MockAction m3 = new MockAction();
		SequentialScheduler s = new SequentialScheduler();
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
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.READY, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.IN_PROGRESS, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
		/* Do step*/
		s.doStep();
		/*Tests */
		assertEquals(ActionState.FINISHED, m1.getState());
		assertEquals(ActionState.FINISHED, m2.getState());
		assertEquals(ActionState.READY, m3.getState());
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
