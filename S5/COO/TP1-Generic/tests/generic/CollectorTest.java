package generic;

import static org.junit.Assert.*;

import org.junit.Test;

import generics.*;

public class CollectorTest {

	@Test
	public void testTakeOK() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Carrot carr1 = new Carrot(1);
		collector1.take(carr1);
		assertSame(carr1, collector1.getCarriedObject());
	}
	
	@Test (expected = AlreadyCarryingException.class)
	public void testTakeKO() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Carrot carr1 = new Carrot(1);
		Carrot carr2 = new Carrot(2);
		collector1.take(carr1);
		assertSame(carr1, collector1.getCarriedObject());
		collector1.take(carr2);
	}

	@Test
	public void testGetCarriedObject() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Carrot carr1 = new Carrot(1);
		assertSame(null, collector1.getCarriedObject());
		collector1.take(carr1);
		assertSame(carr1, collector1.getCarriedObject());
	}

	@Test
	public void testGiveToOK() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Carrot> collector2 = new Collector<Carrot>("carrot-collector-2");
		Carrot carr1 = new Carrot(1);
		collector1.take(carr1);
		collector1.giveTo(collector2);
		assertSame(carr1, collector2.getCarriedObject());
		assertSame(null, collector1.getCarriedObject());
	}

	@Test (expected = AlreadyCarryingException.class)
	public void testGiveToKO1() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Carrot> collector2 = new Collector<Carrot>("carrot-collector-2");
		collector1.giveTo(collector2);
	}
	
	@Test (expected = AlreadyCarryingException.class)
	public void testGiveToKO2() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Vegetable> collector2 = new Collector<Vegetable>("vegetable-collector-1");
		Carrot carr1 = new Carrot(1);
		Carrot carr2 = new Carrot(2);
		collector1.take(carr1);
		collector2.take(carr2);
		collector1.giveTo(collector2);
	}
	
	@Test
	public void testDrop() throws AlreadyCarryingException {
		Collector<Carrot> collector1 = new Collector<Carrot>("carrot-collector-1");
		Carrot carr1 = new Carrot(1);
		assertSame(null,collector1.drop());
		collector1.take(carr1);
		assertSame(carr1, collector1.drop());
	}


	public static junit.framework.Test suite() {
       	return new junit.framework.JUnit4TestAdapter(CollectorTest.class);
    }


}
