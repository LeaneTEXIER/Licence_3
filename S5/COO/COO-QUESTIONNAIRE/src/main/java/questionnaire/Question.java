package questionnaire;

import answer.Answer;

/** Class corresponding to the question
 * A question is define with a question (the text), his answer and the points of the question
 * 
 * @author Leane Texier
 *
 */
public class Question {
	/* Attributs */
	protected String text;
	protected Answer<?> answer;
	protected int points;
	
	/** Create a new Question
	 * @param text the question
	 * @param answer the answer of the question
	 * @param points the number of points of the question
	 */
	public Question(String text, Answer<?> answer, int points){
		this.text = text;
		this.answer = answer;
		this.points = points;
	}
	
	/** Return the (text of the) question
	 * @return text of the question
	 */
	public String getText(){
		return this.text;
	}
	
	/** Return the number of points of the question
	 * @return number of points of the question
	 */
	public int getPoints(){
		return this.points;
	}
	
	/**Return the answer of the question
	 * @return the answer of the question
	 */
	public Answer<?> getAnswer(){
		return this.answer;
	}

	/** Return the correct answer of the question
	 * @return the correct answer of the question
	 */
	public String getCorrectAnswer(){
		return this.getAnswer().getCorrectAnswer().toString();
	}
	
	/** Return the type of the answer of the question
	 * @return the type of the answer
	 */
	public String getAnswerType(){
		return this.answer.getType();
	}
	
	/** Return true iff the userAnswer is an accept answer(=if it's the good type answer), else return false
	 * @param userAnswer the answer of the user
	 * @return true if the userAnswer is a good type answer, else false
	 */
	public boolean accept(String userAnswer){
		return this.answer.accept(userAnswer);
	}
	
	/** Return true iff the userAnswer is the correct answer, else return false
	 * @param userAnswer the answer of the user
	 * @return true if the userAnswer is the correct answer, else false
	 */
	public boolean isCorrect(String userAnswer){
		return this.answer.isCorrect(userAnswer);
	}
}
