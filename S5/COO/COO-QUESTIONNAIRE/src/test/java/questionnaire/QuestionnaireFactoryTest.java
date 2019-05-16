package questionnaire;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class QuestionnaireFactoryTest {

	@Test
	public void testCreateQuestion() throws IOException {
		QuestionnaireFactory qf = new QuestionnaireFactory();
		String type = "MockAnswer";
		String texte = "Texte de la question?";
		String answer = "d";
		Question q = qf.createQuestion(type, texte, answer, "2");
		assertNotNull(q);
		assertEquals(texte, q.getText());
		assertEquals(answer, q.getCorrectAnswer());
		assertEquals(2, q.getPoints());
		assertEquals(type, q.getAnswer().getClass().getSimpleName());
	}
	
	@Test (expected = IOException.class)
	public void testCreateQuestionNumberFormatException() throws IOException {
		QuestionnaireFactory qf = new QuestionnaireFactory();
		qf.createQuestion("MockAnswer", "Texte de la question?", "d", "a");
	}
	
	@Test (expected = IOException.class)
	public void testCreateQuestionBadFormatException() throws IOException {
		QuestionnaireFactory qf = new QuestionnaireFactory();
		qf.createQuestion("MockijkAnswer", "Texte de la question?", "d", "2");
	}

}
