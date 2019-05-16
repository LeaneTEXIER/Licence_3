package ihm;

import java.awt.*;
import javax.swing.*;
import questionnaire.Question;

/** Class corresponding to the question Panel used for the interface
 * The JPanel corresponding to the question is define thanks to a JLabel (for the text of the question) and a JPanel for the answer
 * 
 * @author Leane Texier
 *
 */
public class QuestionPanel {
	
	/** Creates a JPanel for the question
	 * It contains a JLabel for the text of the question and a JPanel for the answer
	 * @param q the question
	 * @param a the answerPanelFactory used
	 * @return the JPanel of the question
	 */
	public JPanel createQuestionPanel(Question q, AnswerPanelFactory a){
		JPanel questionRep = new JPanel();
		questionRep.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		questionRep.setLayout(new GridLayout(1, 2));
		JLabel question = new JLabel(q.getText());
		questionRep.add(question);
		JPanel answer = a.createAnswerPanel(q.getAnswer()).createAnswerPanel(q.getAnswer());
		questionRep.add(answer);
		return questionRep;
	}
}
