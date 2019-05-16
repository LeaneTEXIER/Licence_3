package answer;

import static org.junit.Assert.*;
import org.junit.Test;

public class BooleanAnswerTest {

	@Test
	public void testBooleanAnswer() throws AnswerException {
		Answer<?> a = new BooleanAnswer("vrai");
		assertNotNull(a);
	}

	@Test (expected = AnswerException.class)
	public void testBooleanAnswerException() throws AnswerException {
		new BooleanAnswer("test");
	}
	
	@Test
	public void testGetCorrectAnswerTrue() throws AnswerException {
		Answer<?> a = new BooleanAnswer("vrai");
		/* vrai = true */
		assertSame(true, a.getCorrectAnswer());
	}
	
	@Test
	public void testGetCorrectAnswerFalse() throws AnswerException {
		Answer<?> a = new BooleanAnswer("faux");
		/* faux = false */
		assertSame(false, a.getCorrectAnswer());
	}
	
	@Test
	public void testGetType() throws AnswerException{
		Answer<?> a = new BooleanAnswer("faux");
		assertSame("BOOLEAN_KEY", a.getType());
	}

	@Test
	public void testAccept() throws AnswerException {
		Answer<?> a = new BooleanAnswer("vrai");
		assertFalse(a.accept("test"));
		assertTrue(a.accept("oui"));
		assertTrue(a.accept("non"));
	}

	@Test
	public void testIsCorrect() throws AnswerException {
		Answer<?> a = new BooleanAnswer("vrai");
		/* vrai = oui */
		assertFalse(a.isCorrect("non"));
		assertTrue(a.isCorrect("oui"));
	}
	
	@Test
	public void testToDisplayCorrectAnswerTrue() throws AnswerException {
		Answer<?> a = new BooleanAnswer("vrai");
		/* vrai = oui */
		assertSame("oui",a.toDisplayCorrectAnswer());
	}
	
	@Test
	public void testToDisplayCorrectAnswerFalse() throws AnswerException {
		Answer<?> a = new BooleanAnswer("faux");
		/* faux = non */
		assertSame("non",a.toDisplayCorrectAnswer());
	}
}
