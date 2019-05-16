package answer;

import ihm.*;

/** Class corresponding to the boolean answer
 * The answer is a boolean 
 * 
 * @author Leane Texier
 *
 */
public class BooleanAnswer extends Answer<Boolean>{
	
	/** Creates a BooleanAnswer
	 * @param txt the answer (vrai if the answer is true, faux if the answer is false)
	 * @throws AnswerException if the answer txt is neither vrai or faux
	 */
	public BooleanAnswer(String txt) throws AnswerException{
		if (txt.toLowerCase().equals("vrai")){
			this.correctAnswer=true;
		}
		else if (txt.toLowerCase().equals("faux")){
			this.correctAnswer=false;
		}
		else{
			throw new AnswerException("BooleanAnswer has to be 'vrai' or 'false'");
		}
	}
	
	/** @see Answer#getType()*/
	public String getType() {
		return "BOOLEAN_KEY";
	}
	
	/** @see Answer#accept(String txt)
	 * Return true if the answer txt is 'oui' or 'non'
	 */
	public boolean accept(String txt){
		return (txt.equals("oui") || txt.equals("non"));
	}
	
	/** @see Answer#isCorrect(String txt)*/
	public boolean isCorrect(String txt){
		if (txt.equals("oui") && this.getCorrectAnswer()==true){
			return true;
		}
		else if (txt.equals("non") && this.getCorrectAnswer()==false){
			return true;
		}
		return false;
	}
	
	/** @see Answer#toDisplayCorrectAnswer()
	 * Return oui if the answer is true, else non*/
	public String toDisplayCorrectAnswer() {
		if (this.getCorrectAnswer()==true) {
			return "oui";
		}
		else {
			return "non";
		}
	}

	/** @see Answer#createAnswerPanel(AnswerPanelFactory factory)*/
	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return factory.createAnswerPanel(this);
	}
}
