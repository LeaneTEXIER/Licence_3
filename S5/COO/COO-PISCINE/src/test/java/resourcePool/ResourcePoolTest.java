package resourcePool;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;
import resource.Resource;

public abstract class ResourcePoolTest<R extends Resource> {
	
	public abstract ResourcePool<R> createResourcePool3();

	@Test
	public void testResourcePoolAndGettersResourcesStockAndUse() {
		ResourcePool<R> rp = this.createResourcePool3();
		assertNotNull(rp);
		assertTrue(rp.getResourcesUse().isEmpty());
		assertSame(3,rp.getResourcesStock().size());
	}
	
	@Test
	public void testCreateResource() {
		ResourcePool<R> rp= this.createResourcePool3();
		assertNotNull(rp.createResource());
	}

	@Test
	public void testProvideResourceOK() {
		ResourcePool<R> rp= this.createResourcePool3();
		int iStock = rp.getResourcesStock().size();
		int iUse = rp.getResourcesUse().size();
		Resource r = null;
		try{
			r = rp.provideResource();
		}catch(NoSuchElementException e){
			System.out.println("No such element available");
			fail();
		}
		iStock--;
		iUse++;
		assertNotNull(r);
		assertSame(iStock, rp.getResourcesStock().size());
		assertSame(iUse, rp.getResourcesUse().size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testProvideResourceNoSuchElementException() throws NoSuchElementException{
		ResourcePool<R> rp= this.createResourcePool3();
		/* Use the 3 resources presents in the ResourcePool */
		rp.provideResource();
		rp.provideResource();
		rp.provideResource();
		/* Exception because no more resources */
		rp.provideResource();
	}

	@Test
	public void testRecoverResourceOK() {
		ResourcePool<R> rp= this.createResourcePool3();
		int iStock = rp.getResourcesStock().size();
		int iUse = rp.getResourcesUse().size();
		R res = null;
		try{
			res = rp.provideResource();
		}catch(NoSuchElementException e){
			System.out.println("No such element available");
			fail();
		}
		try{
			rp.recoverResource(res);
		}catch(IllegalArgumentException e){
			System.out.println("The argument is not available");
			fail();
		}
		assertSame(iStock, rp.getResourcesStock().size());
		assertSame(iUse, rp.getResourcesUse().size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRecoverResourceIllegalArgumentExceptionNotValidResource() throws IllegalArgumentException{
		ResourcePool<R> rp= this.createResourcePool3();
		R res = rp.createResource();
		rp.recoverResource(res);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRecoverResourceIllegalArgumentExceptionNull() throws IllegalArgumentException{
		ResourcePool<R> rp= this.createResourcePool3();
		rp.recoverResource(null);
	}

}
