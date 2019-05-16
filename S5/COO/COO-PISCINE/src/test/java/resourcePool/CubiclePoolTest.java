package resourcePool;

import resource.Cubicle;

public class CubiclePoolTest extends ResourcePoolTest<Cubicle> {
	
	public ResourcePool<Cubicle> createResourcePool3() {
		return new CubiclePool(3);
	}
}
