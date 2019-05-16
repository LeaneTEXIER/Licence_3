package ihm;

/** Interface corresponding to the questionnaire UI Panel used for the interface 
 * 
 * @author Leane Texier
 *
 */
public interface QuestionnaireUIPanel {
	
	/** Return the text used for the button of Validation
	 * @return the text for the button of Validation
	 */
	public String btnValidation();
	
	/** Return the text used for the button to see the correct answers
	 * @return the text for the button to see the correct answers
	 */
	public String btnToSeeAnswers();
	
	/** Return the text used to display the user score
	 * @param i the score
	 * @return the text used to display the score
	 */
	public String seeScore(int i);
}
