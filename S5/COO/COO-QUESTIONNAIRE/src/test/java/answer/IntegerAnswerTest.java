package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntegerAnswerTest {
	
	@Test
	public void testIntegerAnswer() throws AnswerException {
		Answer<?> a = new IntegerAnswer("1");
		assertNotNull(a);
	}
	
	@Test (expected = AnswerException.class)
	public void testIntegerAnswerException() throws AnswerException {
		new IntegerAnswer("test");
	}

	@Test
	public void testGetCorrectAnswer() throws AnswerException {
		String s = "1";
		Answer<?> a = new IntegerAnswer(s);
		assertSame(Integer.parseInt(s), a.getCorrectAnswer());
	}
	
	@Test
	public void testGetType() throws AnswerException{
		Answer<?> a = new IntegerAnswer("1");
		assertSame("NUMERICAL_KEY", a.getType());
	}

	@Test
	public void testAccept() throws AnswerException {
		Answer<?> a = new IntegerAnswer("1");
		assertFalse(a.accept("test"));
		assertTrue(a.accept("5"));
		assertTrue(a.accept("1"));
	}

	@Test
	public void testIsCorrect() throws AnswerException {
		String s = "1";
		Answer<?> a = new IntegerAnswer(s);
		assertFalse(a.isCorrect("5"));
		assertFalse(a.isCorrect("0"));
		assertTrue(a.isCorrect(s));
	}
	
	@Test
	public void testToDisplayCorrectAnswer() throws AnswerException {
		String s = "1";
		Answer<?> a = new IntegerAnswer(s);
		assertEquals(s, a.toDisplayCorrectAnswer());
	}
}
