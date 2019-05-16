package resourcePool;

import java.util.*;

import resource.*;

public abstract class ResourcePool<R extends Resource>{
	/*Attributs */
	protected List<R> resourcesStock;
	protected List<R> resourcesUse;
	
	/** Create a resourcePool
	 * @param nbResources the number of resources of the ResourcePool
	 */
	public ResourcePool(int nbResources){
		resourcesStock = new ArrayList<R>();
		resourcesUse = new ArrayList<R>();
		for (int i=0; i<nbResources; i++){
			resourcesStock.add(this.createResource());
		}
	}
	
	/** Return the list of resources in stock
	 * @return the resources in stock
	 */
	public List<R> getResourcesStock(){
		return this.resourcesStock;
	}
	
	/** Return the list of resources used
	 * @return the resources used
	 */
	public List<R> getResourcesUse(){
		return this.resourcesUse;
	}
	
	/** Create a resource of the ResourcePool
	 * @return the resource created
	 */
	public abstract R createResource();
	
	/** Provide a resource in the ResourcePool
	 * @return the provided resource
	 * @exception NoSuchElementException if there is no resource free (= no resource in the stock)
	 */
	public R provideResource() throws NoSuchElementException{
		if (this.getResourcesStock().isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			R resource = this.resourcesStock.get(0);
			this.resourcesStock.remove(0);
			this.resourcesUse.add(resource);
			return resource;
		}
	}
	
	/** Return a resource in the ResourcePool
	 * @param resource the resource to recover
	 * @exception IllegalArgumentException if the resource is not a resource already provided
	 */
	public void recoverResource(R resource) throws IllegalArgumentException{
		if (!this.resourcesUse.contains(resource)){
			throw new IllegalArgumentException();
		}
		else{
			this.resourcesUse.remove(resource);
			this.resourcesStock.add(resource);
		}
	}
	
}
