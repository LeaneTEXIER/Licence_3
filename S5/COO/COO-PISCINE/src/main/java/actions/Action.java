package actions;

public abstract class Action {
	/* Attributs */
	protected ActionState state;
	
	/** Create an Action */
	public Action(){
		this.state = ActionState.READY;
	}
	
	/** Return the state of the Action
	 * @return the state of the Action
	 */
	public ActionState getState(){
		return this.state;
	}
	
	/** Return true if the action is finished, else return false
	 * @return true iff the action is finished, else false
	 */
	public boolean isFinished(){
		return this.getState()==ActionState.FINISHED;
	}
	
	/** Do a step of the action
	 * @exception ActionFinishedException if the action is finished
	 */
	public void doStep() throws ActionFinishedException {
		if (this.getState()==ActionState.FINISHED){
			throw new ActionFinishedException("Can't do step when the Action is finished");
		}
		if (this.getState()==ActionState.READY){
			this.state= ActionState.IN_PROGRESS;
		}
		this.stepEffect();
		this.descriptionAction();
		if (this.stopCondition()){
			this.state =  ActionState.FINISHED;
		}
	}
	
	/** If the stop condition is reached return true, else return false
	 * @return true if the stop condition is reached, else false
	 */
	public abstract boolean stopCondition();
	
	/** Do the effect of the step */
	protected abstract void stepEffect();
	
	/** Print the effect of the action */
	public abstract void descriptionAction();
}
