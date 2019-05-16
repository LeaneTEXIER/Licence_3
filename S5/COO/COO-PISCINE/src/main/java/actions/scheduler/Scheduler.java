package actions.scheduler;

import actions.Action;
import actions.ActionFinishedException;
import actions.ActionState;

import java.util.*;

public abstract class Scheduler extends Action {
	/* Attribut*/
	public List<Action> actions;
	
	/** Create a scheduler */
	public Scheduler(){
		super();
		this.actions = new ArrayList<Action>();
	}
	
	/** Return the list of the different Actions of the Scheduler
	 * @return the list of actions
	 */
	public List<Action> getActions(){
		return this.actions;
	}
	
	/** @see Action#stopCondition() */
	public boolean stopCondition() {
		return (this.getState()==ActionState.FINISHED || (this.getState()==ActionState.IN_PROGRESS && this.getActions().isEmpty()));
	}
	
	/** @see Action#stepEffect() */
	protected void stepEffect() {
		if (!this.getActions().isEmpty()){
			Action nextAction = this.giveNextAction();
			try{
				nextAction.doStep();
			}
			catch (ActionFinishedException e){
				System.out.println(e.getMessage());
			}
			if (nextAction.isFinished()){
				this.actions.remove(this.getActions().indexOf(nextAction));
			}
		}
	}
	
	/** Add an action in the scheduler
	 * @param a the action to add
	 * @exception ActionFinishedException if the action is finished
	 * @exception SchedulerStartedException if the scheduler is already in progress or has finished
	 */
	public void addAction(Action a) throws ActionFinishedException, SchedulerStartedException{
		if (this.getState()!=ActionState.READY){
			throw new SchedulerStartedException("Can't add when scheduler is in progress or has finished");
		}
		if (a.isFinished()){
			throw new ActionFinishedException("Can't add an already finished action");
		}
		else{
			this.actions.add(a);
		}
	}
	
	/** Give the next Action to do
	 * @return the next Action to do
	 */
	protected abstract Action giveNextAction();
}
