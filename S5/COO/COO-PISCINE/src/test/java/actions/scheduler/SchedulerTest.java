package actions.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;
import actions.*;

public abstract class SchedulerTest extends ActionTest{
	
	protected abstract Scheduler createScheduler();
	
	protected Action createAction() {
		return this.createSchedulerWith3Actions();
	}
	
	public Scheduler createSchedulerWith3Actions(){
		Scheduler s = this.createScheduler();
		for (int i=0; i<3; i++){
			try{
				s.addAction(new MockAction());
			}catch(ActionFinishedException e){
				System.out.println("Error action finished");
				fail();
			}catch(SchedulerStartedException e){
				System.out.println("Scheduler has started");
				fail();
			}
		}
		return s;
	}
	
	
	/* Creation mock */
	protected class MockAction extends Action{
		public int totalTime=2;
		
		public boolean stopCondition(){
			return totalTime==0;
		}
		
		public void stepEffect(){
			totalTime--;
		}

		public void descriptionAction() {}
	}
	
	
	

	@Test
	public void testGetActions() {
		Scheduler s = this.createScheduler();
		Action a = new MockAction();
		try{
			s.addAction(a);
		}catch(ActionFinishedException e){
			System.out.println("Error action finished");
			fail();
		}catch(SchedulerStartedException e){
			System.out.println("Scheduler has started");
			fail();
		}
		assertSame(a, s.getActions().get(0));
	}

	@Test
	public void testStopCondition() {
		Scheduler s = this.createSchedulerWith3Actions();
		assertFalse(s.stopCondition());
		while (s.getActions().size()>1){
				try{
					s.doStep();
				}catch(ActionFinishedException e){
					System.out.println("Error action finished");
					fail();
				}
			
		}
		s.getActions().remove(s.getActions().get(0));
		assertTrue(s.stopCondition());
	}
	
	@Test
	public void teststepEffect() {
		Scheduler s = this.createScheduler();
		Action a = new MockAction();
		try {
			s.addAction(a);
		}catch(ActionFinishedException e){
			System.out.println("Error action finished");
			fail();
		}catch(SchedulerStartedException e){
			System.out.println("Scheduler has started");
			fail();
		}
		/* Action has 2 steps*/
		s.stepEffect();
		s.stepEffect();
		assertTrue(s.getActions().isEmpty());
	}


	@Test
	public void testAddActionOK() {
		Scheduler s = this.createScheduler();
		assertEquals(0, s.actions.size());
		try{
			s.addAction(new MockAction());
		}catch(ActionFinishedException e){
			System.out.println("Error action finished");
			fail();
		}catch(SchedulerStartedException e){
			System.out.println("Scheduler has started");
			fail();
		}
		assertEquals(1, s.actions.size());
	}
	
	@Test (expected= ActionFinishedException.class)
	public void testAddActionFinishedActionException() throws ActionFinishedException {
		Scheduler s = this.createScheduler();
		Action a = new MockAction();
		while(!a.isFinished()){
			a.doStep();
		}
		try{
			s.addAction(a);
		}catch(SchedulerStartedException e){
			System.out.println("Scheduler has started");
			fail();
		}
	}
	
	@Test (expected= SchedulerStartedException.class)
	public void testAddActionSchedulerStartedException() throws SchedulerStartedException {
		Scheduler s = this.createScheduler();
		try{
			s.doStep();
		}catch(ActionFinishedException e){
			System.out.println("Error action finished");
			fail();
		}
		try{
			s.addAction(new MockAction());
		}catch(ActionFinishedException e){
			System.out.println("Error action finished");
			fail();
		}
	}
	
	@Test
	public void FinishedSchedulerContainsOnlyFinishedActions(){
		Scheduler s = this.createSchedulerWith3Actions();
		while(!s.isFinished()){
			try{
				s.doStep();
			}catch(ActionFinishedException e){
				System.out.println("Error action finished");
				fail();
			}
		}
		assertTrue(s.isFinished());
		for (Action a: s.getActions()){
			assertTrue(a.isFinished());
		}
	}
}
