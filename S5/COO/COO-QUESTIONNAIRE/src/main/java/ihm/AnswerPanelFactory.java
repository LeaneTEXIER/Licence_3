package ihm;

import answer.*;

/** Abstract class corresponding to the answer panel factory used for the interface
 * 
 * @author Leane Texier
 *
 */
public abstract class AnswerPanelFactory {
	
	/** Creates an AnswerPanel corresponding to the answer
	 * @param a the answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public AnswerPanel createAnswerPanel(Answer<?> a) {
		return a.createAnswerPanel(this);
	}
	
	/** Creates an AnswerPanel corresponding to a boolean answer
	 * @param a the boolean answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public abstract AnswerPanel createAnswerPanel(BooleanAnswer a);
	
	/** Creates an AnswerPanel corresponding to an integer answer
	 * @param a the integer answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public AnswerPanel createAnswerPanel(IntegerAnswer a) {
		return new IntegerAnswerPanel();
	}
	
	/** Creates an AnswerPanel corresponding to a multiple answer
	 * @param a the multiple answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public AnswerPanel createAnswerPanel(MultipleAnswer a) {
		return new MultipleAnswerPanel();
	}
	
	/** Creates an AnswerPanel corresponding to a multiple choices answer
	 * @param a the multiple choices answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public AnswerPanel createAnswerPanel(MultipleChoicesAnswer a) {
		return new MultipleChoicesAnswerPanel();
	}
	
	/** Creates an AnswerPanel corresponding to a text answer
	 * @param a the text answer
	 * @return the AnswerPanel corresponding to the answer
	 */
	public AnswerPanel createAnswerPanel(TextAnswer a) {
		return new TextAnswerPanel();
	}
}
