package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnswerFactoryTest {

	@Test
	public void testBuildAnswerInteger() throws Exception {
		AnswerFactory af = new AnswerFactory();
		String s = "IntegerAnswer";
		Answer<?> a = af.buildAnswer(s, "1");
		assertEquals(s, a.getClass().getSimpleName());
	}
	
	@Test
	public void testBuildAnswerText() throws Exception {
		AnswerFactory af = new AnswerFactory();
		String s = "TextAnswer";
		Answer<?> a = af.buildAnswer(s, "hello");
		assertEquals(s, a.getClass().getSimpleName());
	}
	
	@Test (expected = Exception.class)
	public void testBuildAnswerException() throws Exception{
		AnswerFactory af = new AnswerFactory();
		af.buildAnswer("IntAnswer", "1");
	}

}
