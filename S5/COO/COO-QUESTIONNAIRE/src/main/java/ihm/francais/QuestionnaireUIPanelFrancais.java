package ihm.francais;

import ihm.QuestionnaireUIPanel;

/** Class corresponding to the questionnaire UI Panel used for the interface in french
 * 
 * @author Leane Texier
 *
 */
public class QuestionnaireUIPanelFrancais implements QuestionnaireUIPanel{
	/** @see QuestionnaireUIPanel#btnValidation()*/
	public String btnValidation() {
		return "Valider";
	}
	
	/** @see QuestionnaireUIPanel#seeScore(int i)*/
	public String seeScore(int i) {
		String message = "Vous avez : ";
		message+=i+" points!";
		return message;
	}

	/** @see QuestionnaireUIPanel#btnToSeeAnswers()*/
	public String btnToSeeAnswers() {
		return "Voir les bonnes réponses";
	}

}
