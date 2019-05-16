package donjon;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testDirection() {
		Direction dN = Direction.N;
		assertNotNull(dN);
	}

	@Test
	public void testToString() {
		Direction dN = Direction.N;
		assertSame("North", dN.toString());
		Direction dE = Direction.E;
		assertSame("East", dE.toString());
	}

	@Test
	public void testOpposite() {
		Direction dN = Direction.N;
		Direction dE = Direction.E;
		Direction dS = Direction.S;
		Direction dW = Direction.W;
		assertSame(dS, dN.opposite());
		assertSame(dN, dS.opposite());
		assertSame(dW, dE.opposite());
		assertSame(dE, dW.opposite());
	}

}
