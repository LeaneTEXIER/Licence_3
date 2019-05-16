package resourcePool;

import resource.*;

public class CubiclePool extends ResourcePool<Cubicle>{
	/**Create a CubiclePool
	 * @param nbResources the number of resources of the CubiclePool
	 */
	public CubiclePool(int nbResources) {
		super(nbResources);
	}

	/** @see ResourcePool#createResource() */
	public Cubicle createResource(){
		return new Cubicle();
	}
}
