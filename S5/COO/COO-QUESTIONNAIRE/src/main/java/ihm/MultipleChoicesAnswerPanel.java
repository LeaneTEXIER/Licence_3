package ihm;

import javax.swing.*;
import answer.*;
import java.awt.GridLayout;
import java.util.*;

/** Class corresponding to the multiple choices answer Panel used for the interface
 * The JPanel corresponding to the answer is define thanks to several JRadioButton
 * 
 * @author Leane Texier
 *
 */
public class MultipleChoicesAnswerPanel extends AnswerPanel{

	/** Create an answerPanel with a JRadioButton corresponding to the different choices
	 * @param a the answer of the question
	 */
	public JPanel createAnswerPanel(Answer<?> a) {
		JPanel rep = new JPanel();
		MultipleChoicesAnswer a2 = (MultipleChoicesAnswer)a;
		List<String> s = a2.getChoices();
		int size = s.size()/2;
		if (s.size()%2==1) {
			size+=1;
		}
		rep.setLayout(new GridLayout(2,size));
		ButtonGroup group = new ButtonGroup();
		JRadioButton btn;
		for (String choice: s) {
			btn = new JRadioButton(choice);
			group.add(btn);
			rep.add(btn);
		}
		return rep;  
	}
}
