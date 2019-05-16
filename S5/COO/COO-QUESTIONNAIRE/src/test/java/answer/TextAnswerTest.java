package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextAnswerTest {

	@Test
	public void testTextAnswer() throws AnswerException {
		Answer<?> a = new TextAnswer("test");
		assertNotNull(a);
	}

	@Test (expected = AnswerException.class)
	public void testTextAnswerException() throws AnswerException {
		new TextAnswer("");
	}
	
	@Test
	public void testGetCorrectAnswer() throws AnswerException {
		String s = "good";
		Answer<?> a = new TextAnswer(s);
		assertSame(s, a.getCorrectAnswer());
	}
	
	@Test
	public void testGetType() throws AnswerException{
		Answer<?> a = new TextAnswer("test");
		assertSame("SYMBOLIC_KEY", a.getType());
	}
	
	@Test
	public void testAccept() throws AnswerException {
		Answer<?> a = new TextAnswer("good");
		assertTrue(a.accept("test"));
		assertTrue(a.accept("good"));
		assertTrue(a.accept("123"));
	}

	@Test
	public void testIsCorrect() throws AnswerException {
		String s = "good";
		Answer<?> a = new TextAnswer(s);
		assertFalse(a.isCorrect("test"));
		assertFalse(a.isCorrect("123"));
		assertTrue(a.isCorrect(s));
	}
	
	@Test
	public void testToDisplayCorrectAnswer() throws AnswerException {
		String s = "good";
		Answer<?> a = new TextAnswer(s);
		assertEquals(s, a.toDisplayCorrectAnswer());
	}
}
