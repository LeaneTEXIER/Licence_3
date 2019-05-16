package questionnaire;

import java.io.*;

/** Class corresponding to a QuestionnaireUI french
 * Display in the output (System.out) the informations of the question/questionnaire in french
 * Read the input (System.in) of the user 
 *  
 * @author Leane Texier
 *
 */
public class QuestionnaireUIFrancais extends QuestionnaireUI{
	
	/** Creates a questionnaireUIFrancais
	 * @throws FileNotFoundException if the file used for the types answers can't be found
	 */
	public QuestionnaireUIFrancais() throws FileNotFoundException{
		File f = new File(".");
		String path = f.getAbsolutePath().split("COO-QUESTIONNAIRE")[0]+"COO-QUESTIONNAIRE/traduction_key/francais.txt";
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
		System.out.print("incorrect, ");
	}

	/** @see QuestionnaireUI#displayCorrectAnswer(Question q)*/
	public void displayCorrectAnswer(Question q) {
		System.out.println("la bonne réponse est : "+q.getAnswer().toDisplayCorrectAnswer());		
	}

	/** @see QuestionnaireUI#displayFinalScore(int score)*/
	public void displayFinalScore(int score) {
		System.out.println("Vous avez "+score+" points.");
	}
	
	/** @see QuestionnaireUI#treatAnswer(String type, String input)
	 * In this case, do nothing*/
	public String treatAnswer(String type, String input) {
		return input;
	}
	
	/** @see QuestionnaireUI#translateInLaguage(String answer)
	 */
	public String translateInLaguage(String answer){
		return answer;
	}
}
