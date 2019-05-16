package resourceAction;

import actions.Action;
import resource.Resource;
import resourcePool.ResourcePool;
import resourceUser.ResourceUser;

public abstract class ResourceAction<R extends Resource> extends Action {
	/*Attributs */
	boolean succeed;
	ResourceUser<R> rUser;
	ResourcePool<R> rPool;
	
	/** Create a ResourceAction
	 * @param rUser the ResourceUser of the ResourceAction
	 * @param rPool the ResourcePool of the ResourceAction
	 */
	public ResourceAction(ResourceUser<R> rUser, ResourcePool<R> rPool ){
		super();
		this.succeed = false;
		this.rUser = rUser;
		this.rPool = rPool;
	}
	
	/** Return the ResourceUser of the ResourceAction
	 * @return the ResourceUser
	 */
	public ResourceUser<R> getRUser(){
		return this.rUser;
	}
	
	/** Return the ResourcePool of the ResourceAction
	 * @return the ResourcePool
	 */
	public ResourcePool<R> getRPool(){
		return this.rPool;
	}
	
	/** @see Action#stopCondition() */
	public boolean stopCondition(){
		return this.succeed;
	}
}
