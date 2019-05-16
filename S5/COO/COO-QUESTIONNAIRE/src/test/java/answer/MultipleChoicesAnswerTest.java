package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultipleChoicesAnswerTest {

	@Test
	public void testMultipleChoicesAnswer() throws AnswerException {
		MultipleChoicesAnswer a = new MultipleChoicesAnswer("test|wrong");
		assertNotNull(a);
	}
	
	@Test (expected = AnswerException.class)
	public void testMultipleChoicesAnswerExceptionEmpty() throws AnswerException {
		new MultipleChoicesAnswer("");
	}

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMultipleChoicesAnswerExceptionAllEmpty() throws AnswerException {
		new MultipleChoicesAnswer("|");
	}
	
	@Test
	public void testGetCorrectAnswer() throws AnswerException {
		String s1 = "good";
		String s2 = "wrong";
		String s = s1+"|"+s2;
		Answer<?> a = new MultipleChoicesAnswer(s);
		assertTrue(s1.equals(a.getCorrectAnswer()));
	}
	
	@Test
	public void testGetType() throws AnswerException{
		Answer<?> a = new MultipleChoicesAnswer("good | wrong");
		assertSame("MULTIPLECHOICES_KEY", a.getType());
	}
	
	@Test
	public void testGetChoices() throws AnswerException{
		String s1 = "good";
		String s2 = "wrong";
		String s = s1+"|"+s2;
		MultipleChoicesAnswer a = new MultipleChoicesAnswer(s);
		assertTrue(a.getChoices().contains(s1));
		assertTrue(a.getChoices().contains(s2));
	}
	
	@Test
	public void testAccept() throws AnswerException {
		String s1 = "good";
		String s2 = "wrong";
		String s = s1+"|"+s2;
		Answer<?> a = new MultipleChoicesAnswer(s);
		assertFalse(a.accept("test"));
		assertFalse(a.accept("123"));
		assertTrue(a.accept(s1));
		assertTrue(a.accept(s2));
	}

	@Test
	public void testIsCorrect() throws AnswerException {
		String s1 = "good";
		String s2 = "wrong";
		String s = s1+"|"+s2;
		Answer<?> a = new MultipleChoicesAnswer(s);
		assertFalse(a.isCorrect("test"));
		assertFalse(a.isCorrect(s2));
		assertTrue(a.isCorrect(s1));
	}
	
	@Test
	public void testToDisplayCorrectAnswer() throws AnswerException {
		String s1 = "good";
		String s2 = "wrong";
		String s = s1+"|"+s2;
		Answer<?> a = new MultipleChoicesAnswer(s);
		assertEquals(s1, a.toDisplayCorrectAnswer());
	}
}
