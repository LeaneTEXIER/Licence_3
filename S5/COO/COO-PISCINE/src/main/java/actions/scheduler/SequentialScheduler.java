package actions.scheduler;

import actions.Action;

public class SequentialScheduler extends Scheduler {
	
	/** Create a SequentialScheduler */
	public SequentialScheduler(){
		super();
	}
	
	/** @see Scheduler#giveNextAction() */
	protected Action giveNextAction() {
		return this.getActions().get(0);
	}
	
	/** @see Action#descriptionAction()*/
	public void descriptionAction(){}

}
