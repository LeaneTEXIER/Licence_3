package questionnaire;

import java.io.*;

/** Class corresponding to a QuestionnaireUI english
 * Display in the output (System.out) the informations of the question/questionnaire in english
 * Read the input (System.in) of the user 
 *  
 * @author Leane Texier
 *
 */
public class QuestionnaireUIAnglais extends QuestionnaireUI{
	
	/** Creates a questionnaireUIAnglais
	 * @throws FileNotFoundException if the file used for the types answers can't be found
	 */
	public QuestionnaireUIAnglais() throws FileNotFoundException{
		File f = new File(".");
		String path = f.getAbsolutePath().split("COO-QUESTIONNAIRE")[0]+"COO-QUESTIONNAIRE/traduction_key/anglais.txt";
		this.flux = new FileInputStream(new File(path));
	}

	/** @see QuestionnaireUI#displayWin(Question q)*/
	public void displayWin(Question q) {
		String win = "correct (";
		int pts = q.getPoints();
		if (pts<=1){
			win += pts+" point)";
		}
		else{
			win += pts+" points)";
		}
		System.out.println(win);		
	}

	/** @see QuestionnaireUI#displayLose()*/
	public void displayLose() {
		System.out.print("wrong, ");
	}

	/** @see QuestionnaireUI#displayCorrectAnswer(Question q)*/
	public void displayCorrectAnswer(Question q) {
		if (q.getAnswerType()=="BOOLEAN_KEY"){
			System.out.println("the right answer was : "+translateInLaguage(q.getAnswer().toDisplayCorrectAnswer()));
		}
		else{
			System.out.println("the right answer was : "+q.getAnswer().toDisplayCorrectAnswer());		
		}
	}

	/** @see QuestionnaireUI#displayFinalScore(int score)*/
	public void displayFinalScore(int score) {
		System.out.println("You get "+score+" points.");
	}
	
	/** @see QuestionnaireUI#treatAnswer(String type, String input)
	 * If it's a boolean answer, translate the answer*/
	public String treatAnswer(String type, String input) {
		if (type.equals("BOOLEAN_KEY")) {
			if (input.equals("yes")) {
				input = "oui";
			}
			else if (input.equals("no")) {
				input = "non";
			}
			else {
				input="";
			}
		}
		return input;
	}
	
	/** @see QuestionnaireUI#translateInLaguage(String answer) */
	public String translateInLaguage(String answer){
		if (answer.equals("oui")){
			answer = "yes";
		}
		else if (answer.equals("non")){
			answer = "no";
		}
		return answer;
	}

}
