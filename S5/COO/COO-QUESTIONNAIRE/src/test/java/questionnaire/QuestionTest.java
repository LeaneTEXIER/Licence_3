package questionnaire;

import static org.junit.Assert.*;

import org.junit.Test;

import answer.MockAnswer;

public class QuestionTest {

	@Test
	public void testQuestion() {
		assertNotNull(new Question("test", new MockAnswer(), 2));
	}

	@Test
	public void testGetText() {
		String t = "test";
		Question q = new Question(t, new MockAnswer(), 2);
		assertSame(t, q.getText());
	}

	@Test
	public void testGetPoints() {
		int i = 2;
		Question q = new Question("test", new MockAnswer(), i);
		assertSame(i, q.getPoints());
	}

	@Test
	public void testGetAnswer() {
		MockAnswer a = new MockAnswer();
		Question q = new Question("test", a, 2);
		assertSame(a, q.getAnswer());
	}

	@Test
	public void testGetCorrectAnswer() {
		MockAnswer a = new MockAnswer();
		Question q = new Question("test", a, 2);
		String s = Character.toString(a.getCorrectAnswer());
		assertEquals(s,q.getCorrectAnswer());
	}

	@Test
	public void testGetAnswerType() {
		MockAnswer a = new MockAnswer();
		Question q = new Question("test", a, 2);
		assertSame(a.getType(), q.getAnswerType());
	}

	@Test
	public void testAccept() {
		MockAnswer a = new MockAnswer();
		Question q = new Question("test", a, 2);
		String s = a.getCorrectAnswer().toString();
		assertSame(a.accept(s), q.accept(s));
		String s2 = "a";
		assertSame(a.accept(s2), q.accept(s2));
		String s3 = "tset";
		assertSame(a.accept(s3), q.accept(s3));
	}

	@Test
	public void testIsCorrect() {
		MockAnswer a = new MockAnswer();
		Question q = new Question("test", a, 2);
		String s = a.getCorrectAnswer().toString();
		assertSame(a.isCorrect(s), q.isCorrect(s));
		String s2 = s+"1";
		assertSame(a.isCorrect(s2), q.isCorrect(s2));
	}

}
