package answer;

import ihm.*;

/** Abstract class corresponding to the answer
 * An answer is define with his correct answer
 * 
 * @author Leane Texier
 *
 */
public abstract class Answer<T> {
	/* Attribut */
	protected T correctAnswer;
	
	/** Return the correct answer
	 * @return the correct answer
	 */
	public T getCorrectAnswer(){
		return this.correctAnswer;
	}
	
	/** Return the type of the answer
	 * @return the type of the answer
	 */
	public abstract String getType();
	
	/** Return true iff the txt is the same type as T, else return false
	 * @param txt the answer to test
	 * @return true if the answer txt is the same type as T, else false
	 */
	public abstract boolean accept(String txt);
	
	/** Return true iff the txt is the correct answer, else return false
	 * @param txt the answer to test
	 * @return true if the answer txt is the correct answer, else false
	 */
	public abstract boolean isCorrect(String txt);
	
	/** Return the correct answer (in String)
	 * @return a String corresponding to the correct answer
	 */
	public String toDisplayCorrectAnswer() {
		return this.getCorrectAnswer().toString();
	}
	
	/** Return the AnswerPanel corresponding to the answer to create the interface ihm
	 * @param factory the AnswerPanelFactory used to create the AnswerPanel
	 * @return the AnswerPanel corresponding to the answer
	 */
	public abstract AnswerPanel createAnswerPanel(AnswerPanelFactory factory);
}
