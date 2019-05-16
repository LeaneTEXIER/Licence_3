package actions.scheduler;

import actions.Action;

public class FairScheduler extends Scheduler {
	/* Attribut */
	protected Action nextAction;
	
	/** Create a FairScheduler */
	public FairScheduler(){
		super();
		this.nextAction = null;
	}
	
	/** Return the nextAction
	 * @return the nextAction
	 */
	public Action getNextAction(){
		return this.nextAction;
	}
	
	/** @see Scheduler#giveNextAction() */
	protected Action giveNextAction() {
		Action a = this.getNextAction();
		if (a==null){
			a = this.getActions().get(0);
		}
		int i_prev = this.getActions().indexOf(a);
		int i_next = (i_prev+1)%(this.getActions().size());
		this.nextAction = this.getActions().get(i_next);
		return a;
	}
	
	/** @see Action#descriptionAction()*/
	public void descriptionAction(){}
}
