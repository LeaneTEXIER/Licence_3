package resourcePool;

import resource.*;

public class BasketPool extends ResourcePool<Basket>{
	/**Create a BasketPool
	 * @param nbResources the number of resources of the BasketPool
	 */
	public BasketPool(int nbResources) {
		super(nbResources);
	}
	
	/** @see ResourcePool#createResource() */
	public Basket createResource(){
		return new Basket();
	}
}
