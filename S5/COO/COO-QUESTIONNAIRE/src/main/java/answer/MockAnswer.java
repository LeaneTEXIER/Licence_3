package answer;

import ihm.*;

public class MockAnswer extends Answer<Character>{
	
	protected int nbCallsAccept;
	
	public MockAnswer() {
		this.correctAnswer='b';
		this.nbCallsAccept = 0;
	}
	
	public MockAnswer(String c) {
		this.correctAnswer=c.charAt(0);
		this.nbCallsAccept = 0;
	}

	public String getType() {
		return "char";
	}
	
	public int getNbCallsAccept() {
		return this.nbCallsAccept;
	}

	public boolean accept(String txt) {
		this.nbCallsAccept++;
		/* Taille d'un character = 1 */
		txt = txt.trim();
		Character ch = txt.charAt(0);
		int chValue = Character.getNumericValue(ch);
		Boolean charMin = Character.getNumericValue('a')<=chValue && chValue<=Character.getNumericValue('z');
		Boolean charMaj = Character.getNumericValue('A')<=chValue && chValue<=Character.getNumericValue('Z');
		return txt.length()==1 && (charMin || charMaj);
	}

	public boolean isCorrect(String txt) {
		return txt.equals(Character.toString(this.getCorrectAnswer()));
	}

	public AnswerPanel createAnswerPanel(AnswerPanelFactory factory) {
		return null;
	}
}
