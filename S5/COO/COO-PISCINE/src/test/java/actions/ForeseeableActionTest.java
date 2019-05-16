package actions;

import static org.junit.Assert.*;

import org.junit.Test;

public class ForeseeableActionTest extends ActionTest{

	protected Action createAction(){
		return new ForeseeableAction(3);
	}
	
	@Test
	public void testGetTotalTime() {
		ForeseeableAction a = new ForeseeableAction(3);
		assertEquals(3, a.getTotalTime());
	}

	@Test
	public void testGetRemainingTime() {
		ForeseeableAction a = new ForeseeableAction(3);
		assertEquals(3, a.getRemainingTime());
	}
	
	@Test
	public void testStopCondition() {
		ForeseeableAction a = new ForeseeableAction(3);
		int rtime = a.getRemainingTime();
		while(rtime>0){
			assertFalse(a.stopCondition());
			a.stepEffect();
			rtime--;
		}
		assertTrue(a.getRemainingTime()<=0);
		assertTrue(a.stopCondition());		
	}

	@Test
	public void testStepEffect() {
		ForeseeableAction a = new ForeseeableAction(3);
		int rtime = a.getRemainingTime();
		a.stepEffect();
		rtime--;
		assertEquals(rtime, a.getRemainingTime());
	}
	
	@Test
	public void testForesseableActionNFinishedAfterNDoStep() throws ActionFinishedException{
		int n = (int)(Math.random()*20)+1 ;
		ForeseeableAction a = new ForeseeableAction(n);
		for (int i=0; i<n; i++){
			assertFalse(a.isFinished());
			a.doStep();
		}
		assertTrue(a.isFinished());
	}

}
