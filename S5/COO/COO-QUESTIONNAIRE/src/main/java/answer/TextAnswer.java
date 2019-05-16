package answer;

import ihm.*;

/** Class corresponding to the text answer
 * The answer is a text (= a string)
 * 
 * @author Leane Texier
 *
 */
public class TextAnswer extends Answer<String>{
	
	/** Creates a TextAnswer
	 * @param txt the answer
	 * @throws AnswerException if the answer txt is empty
	 */
	public TextAnswer(String txt) throws AnswerException{
		this.correctAnswer=txt;
		if (this.getCorrectAnswer()==""){
			throw new AnswerException("TextAnswer has to be not empty");
		}
	}
	
	/** @see Answer#getType()*/
	public String getType() {
		return "SYMBOLIC_KEY";
	}
	
	/** @see Answer#accept(String txt)
	 * Return true
	 */
	public boolean accept(String txt){
		return true;
	}
	
	/** @see Answer#isCorrect(String txt)*/
	public boolean isCorrect(String txt){
		return txt.equals(this.getCorrectAnswer().toLowerCase());
	}
	
	/** @see Answer#createAnswerPanel(AnswerPanelFactory factory)*/
	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return factory.createAnswerPanel(this);
	}

}
