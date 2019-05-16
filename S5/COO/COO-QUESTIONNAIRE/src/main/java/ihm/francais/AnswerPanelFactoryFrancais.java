package ihm.francais;

import answer.BooleanAnswer;
import ihm.*;

/** Class corresponding to the answer panel factory used for the interface in french
 * 
 * @author Leane Texier
 *
 */
public class AnswerPanelFactoryFrancais extends AnswerPanelFactory{
	/** @see AnswerPanelFactory#createAnswerPanel(BooleanAnswer a)*/
	public AnswerPanel createAnswerPanel(BooleanAnswer a) {
		return new BooleanAnswerPanelFrancais();
	}
	
}
