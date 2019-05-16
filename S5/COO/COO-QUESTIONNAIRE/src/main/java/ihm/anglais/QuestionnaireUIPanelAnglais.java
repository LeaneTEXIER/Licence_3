package ihm.anglais;

import ihm.QuestionnaireUIPanel;

/** Class corresponding to the questionnaire UI Panel used for the interface in english
 * 
 * @author Leane Texier
 *
 */
public class QuestionnaireUIPanelAnglais implements QuestionnaireUIPanel{
	/** @see QuestionnaireUIPanel#btnValidation()*/
	public String btnValidation() {
		return "Validate";
	}
	
	/** @see QuestionnaireUIPanel#seeScore(int i)*/
	public String seeScore(int i) {
		String message = "You get : ";
		message+=i+" points!";
		return message;
	}

	/** @see QuestionnaireUIPanel#btnToSeeAnswers()*/
	public String btnToSeeAnswers() {
		return "See correct answers";
	}
}
