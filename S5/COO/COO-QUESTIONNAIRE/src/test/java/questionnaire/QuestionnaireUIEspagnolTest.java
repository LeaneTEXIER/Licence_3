package questionnaire;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

public class QuestionnaireUIEspagnolTest {

	@Test
	public void testTreatAnswerOui() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		assertSame("oui", ui.treatAnswer("BOOLEAN_KEY", "si"));
	}
	
	@Test
	public void testTreatAnswerNon() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		assertSame("non", ui.treatAnswer("BOOLEAN_KEY", "no"));
	}
	
	@Test
	public void testTreatAnswerAnythingElse() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		assertSame("", ui.treatAnswer("BOOLEAN_KEY", "test"));
	}
	
	@Test
	public void testTreatAnswerNotBooleanKey() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		String rep = "si";
		assertSame(rep, ui.treatAnswer("NUMERICAL_KEY", rep));
	}
	
	
	@Test
	public void testTranslateInLaguageOui() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		assertSame("si", ui.translateInLaguage("oui"));
	}
	
	@Test
	public void testTranslateInLaguageNon() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		assertSame("no", ui.translateInLaguage("non"));
	}
	
	@Test
	public void testTranslateInLaguageAnythingElse() throws FileNotFoundException {
		QuestionnaireUI ui = new QuestionnaireUIEspagnol();
		String rep = "test";
		assertSame(rep, ui.translateInLaguage(rep));
	}
}
