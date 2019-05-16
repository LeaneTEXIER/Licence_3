package resourceAction;

import java.util.NoSuchElementException;
import actions.Action;
import resource.Resource;
import resourcePool.ResourcePool;
import resourceUser.ResourceUser;

public class TakeResourceAction<R extends Resource> extends ResourceAction<R>{
	
	/** Create a TakeResourceAction
	 * @param rUser the ResourceUser of the TakeResourceAction
	 * @param rPool the ResourcePool of the TakeResourceAction
	 */
	public TakeResourceAction(ResourceUser<R> rUser, ResourcePool<R> rPool) {
		super(rUser, rPool);
	}
	
	/** @see Action#stepEffect() */
	public void stepEffect(){
		if (this.getRUser().getResource()==null){
			try{
				R res = this.rPool.provideResource();
				this.rUser.setResource(res);
				this.succeed = true;
			}catch (NoSuchElementException e){
				this.succeed = false;
			}
		}
		else{
			System.out.println("You have already take an object of this Resource");
			this.succeed = false;
		}
	}
	
	/** @see Action#descriptionAction()*/
	public void descriptionAction() {
		String name = this.getRUser().getName();
		System.out.println(name+"'s turn");
		String rType = this.rPool.createResource().description();
		String res = "  "+name+" trying to take resource from pool "+rType+"...";
		if (this.succeed==false){
			res+="failed";
		}
		else{
			res+="success";
		}
		System.out.println(res);		
	}
	
}
