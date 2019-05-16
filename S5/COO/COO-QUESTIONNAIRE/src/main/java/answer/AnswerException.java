package answer;

/**Exception AnswerException
 * If the answer is not in the correct type
 *
 * @author Leane Texier
 */
public class AnswerException extends Exception{
	/** Creates an answerException
	 * @param msg the message to display
	 */
	public AnswerException(String msg){
		super(msg);
	}
}
