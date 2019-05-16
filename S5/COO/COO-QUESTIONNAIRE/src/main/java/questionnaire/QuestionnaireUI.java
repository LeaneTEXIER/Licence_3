package questionnaire;

import java.io.*;
import java.util.*;
import answer.*;

/** Abstract class corresponding to a QuestionnaireUI
 * Display in the output (System.out) the informations of the question/questionnaire
 * Read the input (System.in) of the user 
 *  
 * @author Leane Texier
 *
 */
public abstract class QuestionnaireUI {
	/* Attribut */
	protected Scanner sc;
	protected Properties p = new Properties();
	protected InputStream flux;

	/** Display the (text of the) question
	 * @param q the question to ask
	 */
	public void displayQuestion(Question q) {
		System.out.println(q.getText());
	}

	/** Display the message if the user has the good answer (display also the points win)
	 * @param q the question asked
	 */
	public abstract void displayWin(Question q);

	/** Display the message if the user has the wrong answer */
	public abstract void displayLose();

	/** Display the correct answer of the question
	 * @param q the question asked
	 */
	public abstract void displayCorrectAnswer(Question q);

	/** Display the final score of the user
	 * @param score the score of the user
	 */
	public abstract void displayFinalScore(int score);
	
	/** Display the type answer of the question
	 * @param type the type of the answer
	 * @param q the question asked
	 * @throws IOException if there is a problem in the file used to get properties
	 */
	public void displayTypeAnswer(String type, Question q) throws IOException {
		try {
			p.load(this.flux);
		}catch(Exception e) {
			System.out.println("erreur");
		}
		if (type.equals("MULTIPLE_KEY")) {
			MultipleAnswer qm = (MultipleAnswer) q.getAnswer();
			System.out.print("("+qm.getCorrectAnswer().size()+" "+p.get(type));
		}
		else if (type.equals("MULTIPLECHOICES_KEY")) {
			MultipleChoicesAnswer qm = (MultipleChoicesAnswer) q.getAnswer();
			String s = "(";
			for(String c : qm.getChoices()) {
				s+=" " + c;
			}
			s+=")";
			System.out.print(s);
		}
		else {
			System.out.print(p.get(type));		
		}
	}
	
	/** Treat the answer of the user (the input) and return it
	 * @param type the type of the answer
	 * @param input the userAnswer
	 * @return the input treated
	 */
	public abstract String treatAnswer(String type, String input);

	/** Read the answer of the user (in the input)
	 * @param q the question asked
	 * @throws IOException if there is a problem when we display the type
	 * @return the userAnswer treated
	 */
	public String readAnswer(Question q) throws IOException {
		this.displayTypeAnswer(q.getAnswerType(), q);
		sc = new Scanner(System.in);
		String input = sc.nextLine().toLowerCase().trim();
		input = this.treatAnswer(q.getAnswerType(), input);
		return input;
	}
	
	/** Translate an answer in his language 
	 * Translate the boolean answer (oui/non)
	 * @param answer the answer of the question
	 * @return the answer translate
	 */
	public abstract String translateInLaguage(String answer);
}
