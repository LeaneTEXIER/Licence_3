package answer;

import java.util.*;
import ihm.*;

/** Class corresponding to the multiple choices answers
 * The correct answer is in the choices of the answer
 * 
 * @author Leane Texier
 *
 */
public class MultipleChoicesAnswer extends TextAnswer {
	/* Attribut */
	protected List<String> choices; 

	/** Creates a MultipleChoicesAnswer
	 * @param txt the answer (the correct is the first one, the other are the other possible choices, they are separated by '|')
	 * @throws AnswerException if the answer is empty
	 */
	public MultipleChoicesAnswer(String txt) throws AnswerException {
		super(Arrays.asList(txt.split("\\|")).get(0).trim());
		this.choices = new ArrayList<String>();
		String[] cho = txt.split("\\|");
		for (String s : cho){
			if ((s=s.trim())!=""){
				this.choices.add(s);
			}
		}
		Collections.shuffle(this.getChoices());
	}
	
	/** Return the possible choices of the answer
	 * @return the possible choices of the answer
	 */
	public List<String> getChoices(){
		return this.choices;
	}

	/** @see Answer#getType()*/
	public String getType() {
		return "MULTIPLECHOICES_KEY";
	}

	/** @see Answer#accept(String txt)
	 * Return true if the answer txt is a possible choice answer, else false
	 */
	public boolean accept(String txt) {
		for (String c : this.getChoices()) {
			if (txt.equals(c.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	/** @see Answer#createAnswerPanel(AnswerPanelFactory factory)*/
	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return factory.createAnswerPanel(this);
	}
}
