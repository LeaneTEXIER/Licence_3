package ihm.espagnol;

import ihm.QuestionnaireUIPanel;

/** Class corresponding to the questionnaire UI Panel used for the interface in spanish
 * 
 * @author Leane Texier
 *
 */
public class QuestionnaireUIPanelEspagnol implements QuestionnaireUIPanel{
	/** @see QuestionnaireUIPanel#btnValidation()*/
	public String btnValidation() {
		return "Validar";
	}
	
	/** @see QuestionnaireUIPanel#seeScore(int i)*/
	public String seeScore(int i) {
		String message = "Tienes : ";
		message+=i+" puntos!";
		return message;
	}

	/** @see QuestionnaireUIPanel#btnToSeeAnswers()*/
	public String btnToSeeAnswers() {
		return "Ver las respuestas correctas";
	}
}
