package actions;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwimmerForeseeableActionTest extends ForeseeableActionTest{
	
	protected Action createAction(){
		return new SwimmerForeseeableAction(3, "dressing", "test");
	}

	@Test
	public void testGetActivity() {
		SwimmerForeseeableAction a = new SwimmerForeseeableAction(3, "dressing", "test");
		assertEquals("dressing", a.getActivity());
	}
	
	@Test
	public void testGetName(){
		SwimmerForeseeableAction a = new SwimmerForeseeableAction(3, "dressing", "test");
		assertEquals("test", a.getName());
	}

}
