package piscine;

import actions.*;
import actions.scheduler.*;
import resource.*;
import resourcePool.*;
import resourceUser.ResourceUser;
import resourceAction.*;

public class Swimmer extends SequentialScheduler{
	/* Attributs */
	protected String name;
	protected BasketPool theBasketPool;
	protected CubiclePool theCubiclePool;
	protected int undressTime;
	protected int swimTime;
	protected int dressTime;
	
	protected ResourceUser<Basket> theBasket;
	protected ResourceUser<Cubicle> theCubicle;
	
	/** Create a Swimmer
	 * 
	 * @param name the name of the swimmer
	 * @param bp the basketPool concerned
	 * @param cp the cubiclePool concerned
	 * @param undress the time needed to undress
	 * @param swim the duration during he will swim
	 * @param dress the time needed to dress
	 */
	public Swimmer(String name, BasketPool bp, CubiclePool cp, int undress, int swim, int dress){
		super();
		this.name = name;
		this.theBasketPool = bp;
		this.theCubiclePool = cp;
		this.undressTime = undress;
		this.swimTime = swim;
		this.dressTime = dress;
		
		this.theBasket = new ResourceUser<Basket>(name);
		this.theCubicle = new ResourceUser<Cubicle>(name);
		
		try {
			this.addAction(new TakeResourceAction<Basket>(this.theBasket, this.theBasketPool));
			this.addAction(new TakeResourceAction<Cubicle>(this.theCubicle, this.theCubiclePool));
			this.addAction(new SwimmerForeseeableAction(this.undressTime, "undressing", name));
			this.addAction(new FreeResourceAction<Cubicle>(this.theCubicle, this.theCubiclePool));
			this.addAction(new SwimmerForeseeableAction(this.swimTime, "swimming", name));
			this.addAction(new TakeResourceAction<Cubicle>(this.theCubicle, this.theCubiclePool));
			this.addAction(new SwimmerForeseeableAction(this.dressTime, "dressing", name));
			this.addAction(new FreeResourceAction<Cubicle>(this.theCubicle, this.theCubiclePool));
			this.addAction(new FreeResourceAction<Basket>(this.theBasket, this.theBasketPool));
		} catch (ActionFinishedException e) {
			System.out.println("Action Finished. Can't add Action.");
		} catch (SchedulerStartedException e) {
			System.out.println("Scheduler started. Can't add Action.");
		}
		
	}
	
	/** Return the name of the swimmer
	 * @return the name of the swimmer
	 */
	public String getName(){
		return this.name;
	}
	
	/** Return the basketPool concerned
	 * @return the basketPool
	 */
	public BasketPool getTheBasketPool(){
		return this.theBasketPool;
	}
	 
	/** Return the cubiclePool concerned
	 * @return the cubiclePool
	 */
	public CubiclePool getTheCubiclePool(){
		return this.theCubiclePool;
	}
	
	/** Return the time needed to undress
	 * @return the time needed to undress
	 */
	public int getUndressTime(){
		return this.undressTime;
	}
	
	/** Return the duration during he will swim
	 * @return the duration during he will swim
	 */
	public int getSwimTime(){
		return this.swimTime;
	}
	
	/** Return the time needed to dress
	 * @return the time needed to dress
	 */
	public int getDressTime(){
		return this.dressTime;
	}
	
	/** Return the BasketResourceUser of the swimmer
	 * @return the BasketResourceUser
	 */
	public ResourceUser<Basket> getTheBasket(){
		return this.theBasket;
	}
	
	/** Return the CubicleResourceUser of the swimmer
	 * @return the CubicleResourceUser
	 */
	public ResourceUser<Cubicle> getTheCubicle(){
		return this.theCubicle;
	}

}
