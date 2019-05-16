package ihm.anglais;

import answer.BooleanAnswer;
import ihm.*;

/** Class corresponding to the answer panel factory used for the interface in english
 * 
 * @author Leane Texier
 *
 */
public class AnswerPanelFactoryAnglais extends AnswerPanelFactory{
	/** @see AnswerPanelFactory#createAnswerPanel(BooleanAnswer a)*/
	public AnswerPanel createAnswerPanel(BooleanAnswer a) {
		return new BooleanAnswerPanelAnglais();
	}
}
