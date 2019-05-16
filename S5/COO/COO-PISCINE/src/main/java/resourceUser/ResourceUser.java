package resourceUser;

import resource.Resource;

public class ResourceUser<R extends Resource>{
	/* Attributs */
	protected R resource;
	protected String name;
	
	/** Create a ResourceUser
	 * @param name the name of the user
	 */
	public ResourceUser(String name) {
		this.name = name;
	}
	
	/** Return the resource of the ResourceUser
	 * @return the resource
	 */
	public R getResource(){
		return this.resource;
	}
	
	/** Return the name of the ResourceUser
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}
	
	/** Set the resource of the resourceUser
	 * @param resource the new resource of the ResourceUser
	 */
	public void setResource(R resource){
		this.resource = resource;
	}
	
	/** Reset the resource of the ResourceUser to null */
	public void resetResource(){
		this.resource = null;
	}
	
}
