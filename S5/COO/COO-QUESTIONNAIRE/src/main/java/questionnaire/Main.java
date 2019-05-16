package questionnaire;

import java.io.*;
import java.util.*;

/** Class who creates a questionnaire
 * Asks the language the user wants
 * Do the questionnaire (= asks all the questions)
 * 
 * @author Leane Texier
 *
 */
public class Main {
	/** Creates a questionnaire
	 * @param args the file who contains the questions
	 * @throws IOException if the questionnaire has a format problem
	 */
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> s = new HashMap<String, String>();
		s.put("ANG","anglais");
		s.put("FRA","francais");
		s.put("ESP","espagnol");
		String input;
		do {
			System.out.println("Quelle langue souhaitez-vous? Tapez:");
			for (String l: s.keySet()) {
				System.out.println(l+" pour "+s.get(l));
			}
			input = sc.nextLine().toUpperCase().trim();
		} while (!s.keySet().contains(input));
		QuestionnaireUI qi;
		if (input.equals("ANG")) {
			qi = new QuestionnaireUIAnglais();
		}
		else if (input.equals("ESP")){
			qi = new QuestionnaireUIEspagnol();
		}
		else{ // if input.equals("FRA")
			qi = new QuestionnaireUIFrancais();
		}
		Questionnaire q = QuestionnaireFactory.FACTORY.createQuestionnaire(args[0], qi);
		q.askAll();
		sc.close();
	}
}
