package resourcePool;

import resource.Basket;

public class BasketPoolTest extends ResourcePoolTest<Basket> {
	
	public ResourcePool<Basket> createResourcePool3() {
		return new BasketPool(3);
	}
}
