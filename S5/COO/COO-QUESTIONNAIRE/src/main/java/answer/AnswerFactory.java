package answer;

import java.lang.reflect.Constructor;

/** Class used to create an object Answer
 * 
 * @author Leane Texier
 *
 */
public class AnswerFactory {
	/*Attribut*/
	public static AnswerFactory FACTORY = new AnswerFactory();
	
	/** Create the object Answer corresponding to the parameters given
	 * 
	 * @param answerClassName the type of the Answer (name of the class corresponding)
	 * @param answerText the correct answer 
	 * @return the object Answer created
	 * @throws Exception if the Answer can't be created
	 */
	public Answer<?> buildAnswer(String answerClassName, String answerText) throws Exception{
		Class<?> c = Class.forName("answer."+answerClassName);
		Constructor<?> constructor = c.getConstructor(String.class);
		Answer<?> answer = (Answer<?>) constructor.newInstance(answerText);
		return answer;
	}
}
