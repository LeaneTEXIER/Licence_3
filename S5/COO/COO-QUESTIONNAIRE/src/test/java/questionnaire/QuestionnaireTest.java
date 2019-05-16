package questionnaire;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import answer.MockAnswer;

public class QuestionnaireTest {

	@Test
	public void testQuestionnaire() {
		assertNotNull(new Questionnaire(new QuestionnaireUIMock()));
	}

	@Test
	public void testGetAndAddPointsToTheScore() {
		Questionnaire q = new Questionnaire(new QuestionnaireUIMock());
		assertSame(0, q.getScore());
		q.addPointsToTheScore(2);
		assertSame(2, q.getScore());
	}
	
	@Test
	public void testSetScoreInitial() {
		Questionnaire q = new Questionnaire(new QuestionnaireUIMock());
		q.addPointsToTheScore(2);
		assertSame(2, q.getScore());
		q.setScoreInitial();
		assertSame(0, q.getScore());
	}

	@Test
	public void testAddQuestionAndGetAllQuestions() {
		Questionnaire q = new Questionnaire(new QuestionnaireUIMock());
		assertTrue(q.getAllQuestions().isEmpty());
		Question qn = new Question("testext", new MockAnswer(), 2);
		q.addQuestion(qn);
		assertTrue(q.getAllQuestions().contains(qn));
	}
	
	@Test
	public void testGetQuestionnaireUI(){
		QuestionnaireUI ui = new QuestionnaireUIMock();
		Questionnaire q = new Questionnaire(ui);
		assertSame(ui, q.getQuestionnaireUI());
	}
	
	@Test
	public void testWaitUntilAnAcceptableAnswer() throws IOException{
		MockAnswer answer = new MockAnswer();
		QuestionnaireUIMock ui = new QuestionnaireUIMock();
		ui.setAttributeToReadAnswer("ab\nre\n1\nb\n");
		Questionnaire quiz = new Questionnaire(ui);
		quiz.addQuestion(new Question("input a character", answer, 2)) ;
		quiz.askAll();
		assertEquals(answer.getNbCallsAccept() , 4) ;
	}
}
