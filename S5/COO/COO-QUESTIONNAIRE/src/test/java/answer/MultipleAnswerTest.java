package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultipleAnswerTest {

	@Test
	public void testMultipleAnswer() throws AnswerException {
		Answer<?> a = new MultipleAnswer("test;answer2");
		assertNotNull(a);
	}

	@Test (expected = AnswerException.class)
	public void testMultipleAnswerExceptionEmpty() throws AnswerException {
		new MultipleAnswer("");
	}
	
	@Test (expected = AnswerException.class)
	public void testMultipleAnswerExceptionChoicesAllEmpty() throws AnswerException {
		new MultipleAnswer(";");
	}
	
	@Test
	public void testGetCorrectAnswer() throws AnswerException {
		String s1 = "test";
		String s2 = "answer2";
		String s = s1+";"+s2;
		MultipleAnswer a = new MultipleAnswer(s);
		assertTrue(a.getCorrectAnswer().contains(s1));
		assertTrue(a.getCorrectAnswer().contains(s2));
	}
	
	@Test
	public void testGetType() throws AnswerException{
		MultipleAnswer a = new MultipleAnswer("test;answer2");
		assertSame("MULTIPLE_KEY", a.getType());
	}
	
	@Test
	public void testAccept() throws AnswerException {
		Answer<?> a = new MultipleAnswer("good;test");
		assertTrue(a.accept("test"));
		assertTrue(a.accept("good"));
		assertTrue(a.accept("123"));
	}

	@Test
	public void testIsCorrect() throws AnswerException {
		String s1 = "test";
		String s2 = "answer2";
		String s = s1+";"+s2;
		Answer<?> a = new MultipleAnswer(s);
		assertFalse(a.isCorrect("tst"));
		assertFalse(a.isCorrect("123"));
		assertTrue(a.isCorrect(s1));
		assertTrue(a.isCorrect(s2));
	}
	
	@Test
	public void testToDisplayCorrectAnswer() throws AnswerException {
		String s1 = "test";
		String s2 = "answer2";
		String s = s1+";"+s2;
		Answer<?> a = new MultipleAnswer(s);
		assertTrue(a.toDisplayCorrectAnswer().contains(s1));
		assertTrue(a.toDisplayCorrectAnswer().contains(s2));
	}
}
