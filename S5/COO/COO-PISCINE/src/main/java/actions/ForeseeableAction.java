package actions;

public class ForeseeableAction extends Action {
	/* Attributs */
	protected int totalTime;
	protected int remainingTime;
	
	/** Create a ForeseeableAction
	 * @param i the duration of the action
	 */
	public ForeseeableAction(int i){
		super();
		this.totalTime=i;
		this.remainingTime=i;
	}
	
	/** Return the totalTime of the action
	 * @return the totalTime
	 */
	public int getTotalTime(){
		return this.totalTime;
	}
	
	/** Return the remainingTime of the action
	 * @return the remainingTime
	 */
	public int getRemainingTime(){
		return this.remainingTime;
	}
	
	/** @see Action#stopCondition() */
	public boolean stopCondition() {
		return this.getRemainingTime()<=0;
	}
	
	/** @see Action#stepEffect() */
	protected void stepEffect() {
		this.remainingTime--;
	}
	
	/** @see Action#descriptionAction()*/
	public void descriptionAction(){}

}
