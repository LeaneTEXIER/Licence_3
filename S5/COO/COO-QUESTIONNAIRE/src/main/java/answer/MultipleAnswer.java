package answer;

import java.util.*;
import ihm.*;

/** Class corresponding to a multiple possible answers
 * The answer has several good possible answers
 * 
 * @author Leane Texier
 *
 */
public class MultipleAnswer extends Answer<List<String>>{
	
	/** Creates a MultipleAnswer
	 * @param txt the answers (they are separated by ';')
	 * @throws AnswerException if the answer is empty
	 */
	public MultipleAnswer(String txt) throws AnswerException{
		this.correctAnswer = new ArrayList<String>();
		String[] answers = txt.split(";");
		for (String s : answers){
			if ((s=s.trim())!=""){
				this.correctAnswer.add(s);
			}
		}
		if (this.getCorrectAnswer().isEmpty()){
			throw new AnswerException("MultipleAnswer has to be not empty");
		}
	}
	
	/** @see Answer#getType()*/
	public String getType() {
		return "MULTIPLE_KEY";
	}
	
	/** @see Answer#accept(String txt)
	 * Return true
	 */
	public boolean accept(String txt) {
		return true;
	}
	
	/** @see Answer#isCorrect(String txt)*/
	public boolean isCorrect(String txt) {
		for (String c : this.getCorrectAnswer()) {
			if (txt.equals(c.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	/** @see Answer#toDisplayCorrectAnswer()
	 * Return the list of the correct answers*/
	public String toDisplayCorrectAnswer() {
		String rep = "";
		for (String s: this.getCorrectAnswer()) {
			if (rep!="") {
				rep+=", ";
			}
			rep += s;
		}
		return rep;
	}
	
	/** @see Answer#createAnswerPanel(AnswerPanelFactory factory)*/
	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return factory.createAnswerPanel(this);
	}

}
