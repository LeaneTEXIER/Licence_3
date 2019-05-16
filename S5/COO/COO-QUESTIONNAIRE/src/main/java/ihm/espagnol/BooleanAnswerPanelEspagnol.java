package ihm.espagnol;

import java.awt.GridLayout;
import javax.swing.*;
import answer.Answer;
import ihm.*;

/** Class corresponding to the boolean answer Panel used for the interface in spanish
 * The JPanel corresponding to the answer is define thanks to 2 JRadio Button ("si"/"no")
 * 
 * @author Leane Texier
 *
 */
public class BooleanAnswerPanelEspagnol extends AnswerPanel{
	/** @see AnswerPanel#createAnswerPanel(Answer a)*/
	public JPanel createAnswerPanel(Answer<?> a) {
		JPanel rep = new JPanel();
		rep.setLayout(new GridLayout(2,1));
		ButtonGroup group = new ButtonGroup();
		JRadioButton btn1 = new JRadioButton("si");
		JRadioButton btn2 = new JRadioButton("no");
		group.add(btn1);
		group.add(btn2);
		rep.add(btn1);
		rep.add(btn2);
		return rep; 
	}
}

