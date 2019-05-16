package questionnaire;

import java.io.IOException;
import java.util.*;

/** Class corresponding to the questionnaire
 * A questionnaire is define by all his questions and the score and his questionnaireUI corresponding (to the outout and input)
 * 
 * @author Leane Texier
 *
 */
public class Questionnaire {
	/* Attributs */
	protected List<Question> allQuestions;
	protected int score;
	protected QuestionnaireUI ui;
	
	/** Creates a questionnaire
	 * Score is initialize to 0
	 * @param ui the questionnaireUI for this questionnaire (to the output and the input)
	 */	
	public Questionnaire(QuestionnaireUI ui){
		this.allQuestions = new ArrayList<Question>();
		this.score = 0;
		this.ui = ui;
	}
	
	/** Return all the questions of the questionnaire
	 * @return the questions
	 */
	public List<Question> getAllQuestions(){
		return this.allQuestions;
	}
	
	/** Return the score 
	 * @return the score
	 */
	public int getScore(){
		return this.score;
	}
	
	/** Return the questionnaireUI corresponding 
	 * @return the questionnaireUI 
	 */
	public QuestionnaireUI getQuestionnaireUI(){
		return this.ui;
	}
	
	/** Add points to the score
	 * @param i the points to add to the score
	 */
	public void addPointsToTheScore(int i) {
		this.score+=i;
	}
	
	/** Initialize the score to 0 */
	public void setScoreInitial() {
		this.score = 0;
	}
	
	/** Add a question in the questionnaire 
	 * @param q the question to add
	 */
	public void addQuestion(Question q){
		this.allQuestions.add(q);
	}
	
	/** Read the userAnswer until it's a correct answer (has the good type)
	 * @param q the question asked
	 * @return the userAnswer at the question asked
	 * @throws IOException if there is a problem in the read of the type of the answer
	 */
	private String ask (Question q) throws IOException{
		String userAnswer;
		do{
			userAnswer = this.ui.readAnswer(q);
		}while(!q.accept(userAnswer));
		return userAnswer;
	}
	
	/** Ask all the questions of the questionnaire and display all the messages (good or wrong answer/ the correct answer/ score)
	 * @throws IOException if there is a problem in the read of the type of the answer of the question
	 */
	public void askAll() throws IOException{
		for (Question q: this.getAllQuestions()){
			ui.displayQuestion(q);
			String userAnswer = this.ask(q);
			if (q.isCorrect(userAnswer)){
				ui.displayWin(q);
				this.score+=q.getPoints();
			}
			else{
				ui.displayLose();
				ui.displayCorrectAnswer(q);
			}
		}
		ui.displayFinalScore(this.score);
	}
}
