package ihm;

import java.awt.Dimension;
import javax.swing.*;
import answer.Answer;

/** Class corresponding to the text answer Panel used for the interface
 * The JPanel corresponding to the answer is define thanks to a JTextField
 * 
 * @author Leane Texier
 *
 */
public class TextAnswerPanel extends AnswerPanel{

	/** Create an answerPanel with a JTextField empty to write the answer in the JPanel
	 * @param a the answer of the question
	 */
	public JPanel createAnswerPanel(Answer<?> a) {
		JPanel rep = new JPanel();
		JTextField t = new JTextField();
		Dimension d = new Dimension(150,50);
		t.setPreferredSize(d);
		rep.add(t);
		return rep; 
	}

}
