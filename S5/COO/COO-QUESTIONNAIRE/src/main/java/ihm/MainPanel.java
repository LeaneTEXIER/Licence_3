package ihm;

import java.io.IOException;
import java.util.*;
import answer.*;
import questionnaire.*;
import ihm.francais.*;
import ihm.anglais.*;
import ihm.espagnol.*;

/** Class who creates a questionnaire interface
 * Asks the language the user wants
 * Do the questionnaire in an interface (= asks all the questions)
 * 
 * @author Leane Texier
 *
 */
public class MainPanel {
	/** Creates a questionnaire interface
	 * @param args the file who contains the questions
         * @throws AnswerException if the answer has a format problem when we create the questionnaire
	 * @throws IOException if the questionnaire has a format problem
	 */
	public static void main(String[] args) throws AnswerException, IOException {
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
		QuestionnaireUIPanel qip;
		AnswerPanelFactory apf;
		if (input.equals("ANG")) {
			qi = new QuestionnaireUIAnglais();
			qip = new QuestionnaireUIPanelAnglais();
			apf = new AnswerPanelFactoryAnglais();
		}
		else if (input.equals("ESP")){
			qi = new QuestionnaireUIEspagnol();
			qip = new QuestionnaireUIPanelEspagnol();
			apf = new AnswerPanelFactoryEspagnol();
		}
		else{ // if input.equals("FRA")
			qi = new QuestionnaireUIFrancais();
			qip = new QuestionnaireUIPanelFrancais();
			apf = new AnswerPanelFactoryFrancais();
		}
		Questionnaire q = QuestionnaireFactory.FACTORY.createQuestionnaire(args[0], qi);
		new QuestionnairePanel(q, qip, apf);
		sc.close();
	}

}