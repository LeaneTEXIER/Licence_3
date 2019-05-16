package ihm;

import java.awt.Dimension;
import javax.swing.*;
import answer.Answer;

/** Class corresponding to the integer answer Panel used for the interface
 * The JPanel corresponding to the answer is define thanks to a JSpinner
 * 
 * @author Leane Texier
 *
 */
public class IntegerAnswerPanel extends AnswerPanel{

	/** Create an answerPanel with a JSpinner for numbers
	 * @param a the answer of the question
	 */
	public JPanel createAnswerPanel(Answer<?> a) {
		SpinnerModel model = new SpinnerNumberModel();
		JSpinner numberSpin = new JSpinner(model);
		Dimension d = new Dimension(100,30);
		numberSpin.setPreferredSize(d);
		JPanel j2 = new JPanel();
		j2.add(numberSpin);
		return j2; 
	}

}
