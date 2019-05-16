package ihm.espagnol;

import answer.BooleanAnswer;
import ihm.*;

/** Class corresponding to the answer panel factory used for the interface in spanish
 * 
 * @author Leane Texier
 *
 */
public class AnswerPanelFactoryEspagnol extends AnswerPanelFactory{
	/** @see AnswerPanelFactory#createAnswerPanel(BooleanAnswer a)*/
	public AnswerPanel createAnswerPanel(BooleanAnswer a) {
		return new BooleanAnswerPanelEspagnol();
	}
}