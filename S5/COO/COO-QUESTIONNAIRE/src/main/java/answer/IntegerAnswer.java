package answer;

import ihm.*;

/** Class corresponding to the integer answer
 * The answer is an integer 
 * 
 * @author Leane Texier
 *
 */
public class IntegerAnswer extends Answer<Integer>{
	
	/** Creates an IntegerAnswer
	 * @param txt the answer (an integer)
	 * @throws AnswerException if the answer txt is not an integer
	 */
	public IntegerAnswer(String txt) throws AnswerException{
		try{
			this.correctAnswer = Integer.parseInt(txt);
		}catch(NumberFormatException e){
			throw new AnswerException("IntegerAnswer has to be an integer");
		}
	}
	
	/** @see Answer#getType()*/
	public String getType() {
		return "NUMERICAL_KEY";
	}
	
	/** @see Answer#accept(String txt)
	 * Return true if the answer txt is an integer
	 */
	public boolean accept(String txt){
		try{
			Integer.parseInt(txt);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/** @see Answer#isCorrect(String txt)*/
	public boolean isCorrect(String txt){
		return Integer.parseInt(txt)==this.getCorrectAnswer();
	}
	
	/** @see Answer#createAnswerPanel(AnswerPanelFactory factory)*/
	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return factory.createAnswerPanel(this);
	}
}
