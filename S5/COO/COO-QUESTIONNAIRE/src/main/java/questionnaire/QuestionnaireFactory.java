package questionnaire;

import java.io.*;
import answer.AnswerFactory;

/** Class who creates a questionnaireFactory : Create the questions of the questionnaire
 * 
 * @author Leane Texier
 *
 */
public class QuestionnaireFactory {
	/* Attribut */
	public static QuestionnaireFactory FACTORY = new QuestionnaireFactory();
	
	/** Creates a question if it's possible
	 * 
	 * @param type the type of the question
	 * @param text the text (the question)
	 * @param answer the answer of the question
	 * @param points the number of points of the question
	 * @return the Question creates
	 * @throws IOException if the question has a format problem
	 */
	public Question createQuestion(String type, String text, String answer , String points) throws IOException {
		try {
			int nbPoints = Integer.parseInt(points);
			return new Question(text, AnswerFactory.FACTORY.buildAnswer(type, answer), nbPoints);
		}catch(NumberFormatException e){
			throw new IOException("Bad format");
		} catch (Exception e) {
			throw new IOException("Bad format");
		}
	}
	
	/** Creates a questionnaire if it's possible thanks to a file
	 * @param fileName the file who contains all the questions
	 * All the questions are written on 4 lines:
	 * The first is the question
	 * The second is the answer
	 * The third is the number of points of the question
	 * The fourth is the type of the question
	 * @param q the questionnaireUI used for the questionnaire
	 * @return the Questionnaire creates
	 * @throws IOException if we can't open the file or the question has a format problem
	 */
	public Questionnaire createQuestionnaire(String fileName, QuestionnaireUI q) throws IOException {
		Questionnaire questionnaire = new Questionnaire(q);
		File source = new File(fileName);
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(source));
			String text;
			while((text = in.readLine()) != null){
				String answer = in.readLine();
				String nbPoints = in.readLine();
				String type = in.readLine();
				if(answer == null || nbPoints == null || type == null){
					throw new IOException ("bad format");
				}
				questionnaire.addQuestion(this.createQuestion(type, text, answer.trim(), nbPoints));
			}
		}catch(FileNotFoundException e){
			throw new IOException(e);
		}
		finally{
			in.close();
		}
		return questionnaire;
	}
}
