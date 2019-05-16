package piscine;

import static org.junit.Assert.*;

import org.junit.Test;
import actions.ActionFinishedException;
import actions.scheduler.SchedulerStartedException;
import resourcePool.BasketPool;
import resourcePool.CubiclePool;

public class SwimmerTest {
	
	@Test
	public void testSwimmer() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertNotNull(s);
	}

	@Test
	public void testGetName() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame("Camille", s.getName());
	}

	@Test
	public void testGetTheBasketPool() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame(baskets, s.getTheBasketPool());
	}

	@Test
	public void testGetTheCubiclePool() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame(cubicles, s.getTheCubiclePool());
	}

	@Test
	public void testGetUndressTime() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame(6, s.getUndressTime());
	}

	@Test
	public void testGetSwimTime() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame(4, s.getSwimTime());
	}

	@Test
	public void testGetDressTime() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertSame(8, s.getDressTime());
	}

	@Test
	public void testGetTheBasket() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertNotNull(s.getTheBasket());
	}

	@Test
	public void testGetTheCubicle() throws ActionFinishedException, SchedulerStartedException{
		BasketPool baskets = new BasketPool(6);
		CubiclePool cubicles = new CubiclePool(3);
		Swimmer s = new Swimmer("Camille", baskets, cubicles, 6, 4, 8);
		assertNotNull(s.getTheCubicle());
	}

}
