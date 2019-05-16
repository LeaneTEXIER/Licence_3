package questionnaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class QuestionnaireUIMock extends QuestionnaireUI{
	
	public void displayQuestion(Question q) {}
	
	protected BufferedReader readAnswerTo = new BufferedReader(new StringReader(""));
	
	public void displayWin(Question q) {}

	public void displayLose() {}

	public void displayCorrectAnswer(Question q) {}

	public void displayFinalScore(int score){}
	
	/** Initialize the attribute used to read an answer
	 * @param s the String used to initialize the attribute */
	public void setAttributeToReadAnswer(String s){
		this.readAnswerTo = new BufferedReader(new StringReader(s));
	}
	
	public String readAnswer(Question q) throws IOException {
		String input = readAnswerTo.readLine();
		return input;
	}

	public String treatAnswer(String type, String input) {
		return input;
	}

	@Override
	public String translateInLaguage(String answer) {
		return answer;
	}
}
