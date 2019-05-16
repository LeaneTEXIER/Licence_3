package actions;

public class SwimmerForeseeableAction extends ForeseeableAction{
	/* Attributs */
	protected String activity;
	protected String name;
	
	/** Create a SwimmerForeseeableAction
	 * @param i the duration of the action
	 * @param activity the activity of the action
	 * @param name the name of the swimmer
	 */
	public SwimmerForeseeableAction(int i, String activity, String name) {
		super(i);
		this.activity = activity;
		this.name = name;
	}
	
	/** Return the activity of the action
	 * @return the activity
	 */
	public String getActivity(){
		return this.activity;
	}
	
	/** Return the name of the swimmer who do the action
	 * @return the name of the swimmer
	 */
	public String getName(){
		return this.name;
	}

	/** @see Action#descriptionAction()*/
	public void descriptionAction(){
		System.out.println(this.getName()+"'s turn");
		int etape = this.getTotalTime()-this.getRemainingTime();
		System.out.println("  "+getActivity()+" ("+etape+"/"+this.getTotalTime()+")");
	}
}