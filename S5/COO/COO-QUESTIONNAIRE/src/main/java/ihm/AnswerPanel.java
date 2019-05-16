package ihm;

import javax.swing.JPanel;
import answer.Answer;

/** Abstrcat class corresponding to the answer Panel used for the interface
 * 
 * @author Leane Texier
 *
 */
public abstract class AnswerPanel {
	
	/** Creates a JPanel for the answer
	 * @param a the answer
	 * @return JPanel corresponding to the answer
	 */
	public abstract JPanel createAnswerPanel(Answer<?> a);
}
