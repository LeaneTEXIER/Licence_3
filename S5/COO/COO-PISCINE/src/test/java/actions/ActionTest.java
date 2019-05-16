package actions;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class ActionTest {
	
	protected abstract Action createAction();

	@Test
	public void testActionAndGetState() {
		Action a = this.createAction();
		assertNotNull(a);
		assertEquals(ActionState.READY, a.getState());
	}
	
	@Test
	public void testIsFinished() throws ActionFinishedException {
		Action a = this.createAction();
		assertFalse(a.isFinished());
		assertFalse(a.stopCondition());
		while (!a.isFinished()){
			assertFalse(a.stopCondition());
			a.doStep();
		}
		assertTrue(a.isFinished());
		assertTrue(a.stopCondition());
	}

	@Test (expected = ActionFinishedException.class)
	public void testDoStepExceptionActionFinished() throws ActionFinishedException {
		Action a = this.createAction();
		while (!a.isFinished()){
			a.doStep();
		}
		assertTrue(a.isFinished());
		a.doStep();
	}
	
	
	@Test 
	public void testDoStepOK() throws ActionFinishedException {
		Action a = this.createAction();
		assertEquals(ActionState.READY, a.getState());
		a.doStep();
		if (a.stopCondition()){
			assertEquals(ActionState.FINISHED,a.getState());
		}
		else{
			assertEquals(ActionState.IN_PROGRESS,a.getState());
		}
	}
	
}
