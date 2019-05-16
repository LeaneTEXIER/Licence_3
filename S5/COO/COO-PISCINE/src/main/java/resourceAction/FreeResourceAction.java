package resourceAction;

import actions.Action;
import resource.Resource;
import resourcePool.ResourcePool;
import resourceUser.ResourceUser;

public class FreeResourceAction<R extends Resource> extends ResourceAction<R>{
	
	/** Create a FreeResourceAction
	 * @param rUser the ResourceUser of the FreeResourceAction
	 * @param rPool the ResourcePool of the FreeResourceAction
	 */
	public FreeResourceAction(ResourceUser<R> rUser, ResourcePool<R> rPool) {
		super(rUser, rPool);
	}

	/** @see Action#stepEffect() */
	public void stepEffect(){
		R res = this.rUser.getResource();
		try{
			this.rPool.recoverResource(res);
			this.rUser.resetResource();
			this.succeed = true;
		}catch (IllegalArgumentException e){
			this.succeed = false;
		}
	}
	
	/** @see Action#descriptionAction()*/
	public void descriptionAction(){
		String name = this.getRUser().getName();
		System.out.println(name+"'s turn");
		String rType = this.rPool.createResource().description();
		System.out.println("  "+name+" freeing resource from pool "+rType);
	}
}
