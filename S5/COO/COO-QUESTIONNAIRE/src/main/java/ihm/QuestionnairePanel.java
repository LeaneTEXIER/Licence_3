package ihm;

import java.awt.*;
import java.awt.event.*;
import questionnaire.*;
import java.util.List;
import javax.swing.*;

/** Class corresponding to the questionnaire Panel used for the interface
 * 
 * @author Leane Texier
 *
 */
public class QuestionnairePanel {
	/* Attributs */
	protected Questionnaire questionnaire;
	protected JPanel questionsAnswersAll;
	protected QuestionnaireUIPanel ui;
	protected AnswerPanelFactory apf;
	
	/** Creates a QuestionnairePanel
	 * @param q the questionnaire
	 * @param ui the questionnaire UI
	 * @param apf the answerPanelFactory
	 */
	public QuestionnairePanel(Questionnaire q, QuestionnaireUIPanel ui, AnswerPanelFactory apf) {
		this.questionnaire = q;
		this.ui = ui;
		this.apf = apf;
		JFrame f = new JFrame("Questionnaire");
		f.addWindowListener(new CloseWindowEvent());
		f.setLocation(200, 100);
		JPanel j = new JPanel();
		j.setLayout(new BorderLayout());
		j.add(this.contentQuestionsAnswers());
		j.add(this.contentValidation(),BorderLayout.SOUTH);
		JScrollPane scrollFrame = new JScrollPane(j);
		f.add(scrollFrame);
		f.pack();
		f.setVisible(true);
	}
	
	/** Creates a JPanel to contains the questions and the answers
	 * @return the JPanel to contains questions and answers
	 */
	public JPanel contentQuestionsAnswers() {
		this.questionsAnswersAll = new JPanel();
		List<Question> allQuestions = this.questionnaire.getAllQuestions();
		GridLayout g = new GridLayout(allQuestions.size(), 1, 2, 2);
		questionsAnswersAll.setLayout(g);
		QuestionPanel qp = new QuestionPanel();
		for (Question q: allQuestions){
			questionsAnswersAll.add(qp.createQuestionPanel(q, apf));
		}
		return questionsAnswersAll;
	}
	
	/** Create a JButton to valid the Questionnaire
	 * @return JButton to valid
	 */
	public JButton contentValidation() {
		JButton validationButton = new JButton(ui.btnValidation());
		validationButton.addActionListener((ActionListener) new ShowScoreListener());
		return validationButton;
	}
	
	/** Calculate the score of the user */
	public void calculerScore() {
		JPanel answersPanel;
		JPanel answers;
		int i = 0;
		for (Question q: questionnaire.getAllQuestions()){
			answersPanel = (JPanel)questionsAnswersAll.getComponent(i);
			answers = (JPanel) answersPanel.getComponent(1);
			String userAnswer = this.getUserAnswer(answers);
			if (q.accept(userAnswer) && q.isCorrect(userAnswer)){
				questionnaire.addPointsToTheScore(q.getPoints());
			}
			i++;
		}
	}
	
	/** Return the userAnswer for the question (thanks to the JPanel answer)
	 * @param answers the JPanel who contains the answer
	 * @return the string corresponding to the user answer
	 */
	public String getUserAnswer(JPanel answers){
		Class<? extends Component> clas = answers.getComponent(0).getClass();
		String userAnswer = "";
		if (clas.isAssignableFrom(JTextField.class)){
			JTextField c = (JTextField) answers.getComponent(0);
			userAnswer = c.getText();
		}
		else if (clas.isAssignableFrom(JRadioButton.class)){
			for (int j=0; j<answers.getComponentCount(); j++) {
				JRadioButton c = (JRadioButton) answers.getComponent(j);
				if (c.isSelected()) {
					userAnswer = c.getText();
				}
			}
			if (answers.getComponentCount()==2){
				userAnswer = this.treatJRadioButton(answers, userAnswer);
			}
		}
		else if (clas.isAssignableFrom(JSpinner.class)){
			JSpinner c = (JSpinner) answers.getComponent(0);
			userAnswer = c.getValue().toString();
		}
		return userAnswer.toLowerCase().trim();
	}
	
	/** Return the userAnswer treated for the question
	 * @param answers the JPanel who contains the answer
	 * @param userAnswer the userAnswer in the language chosen by him
	 * @return the string corresponding to the user answer treated (translate in french if it's a boolean answer)
	 */
	public String treatJRadioButton(JPanel answers, String userAnswer){
		JRadioButton c1 = (JRadioButton) answers.getComponent(0);
		JRadioButton c2 = (JRadioButton) answers.getComponent(1);
		String tradC1 = questionnaire.getQuestionnaireUI().treatAnswer("BOOLEAN_KEY", c1.getText());
		String tradC2 = questionnaire.getQuestionnaireUI().treatAnswer("BOOLEAN_KEY", c2.getText());
		if ((tradC1.equals("oui") && tradC2.equals("non")) || (tradC1.equals("non") && tradC2.equals("oui"))){
			userAnswer = questionnaire.getQuestionnaireUI().treatAnswer("BOOLEAN_KEY", userAnswer);
		}
		return userAnswer;
	}
		
	
	/** Class corresponding to close window event 
	 * 
	 * @author Leane Texier
	 *
	 */
	private class CloseWindowEvent extends WindowAdapter {
		/** Close the window
		 * @param e the window event
		 */
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
	}
	
	/** Class corresponding to a listener used to show the score of the user and possibility to see correct answers for this questionnaire Panel
	 * 
	 * @author Leane Texier
	 *
	 */
	private class ShowScoreListener implements ActionListener{
		/** Do the action listener (show score and possibility to see correct answers) on the action event
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			calculerScore();
			JPanel fenetreDialogue = new JPanel();
			fenetreDialogue.setLayout(new GridLayout(2, 1));
			JButton buttonAnswer = new JButton(ui.btnToSeeAnswers());
			buttonAnswer.addActionListener(new ShowCorrectAnswersListener());
			fenetreDialogue.add(new JLabel(ui.seeScore(questionnaire.getScore())));
			fenetreDialogue.add(buttonAnswer);
			JOptionPane.showMessageDialog(null, fenetreDialogue, "Score", JOptionPane.PLAIN_MESSAGE);
			questionnaire.setScoreInitial();
		}
	}
	
	/** Class corresponding to a listener used to show the correct answers for this questionnaire Panel
	 * 
	 * @author Leane Texier
	 *
	 */
	private class ShowCorrectAnswersListener implements ActionListener{
		/** Do the action listener (show correct answers) on the action event
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			JPanel answers = new JPanel();
			List<Question> allQuestions = questionnaire.getAllQuestions();
			GridLayout g = new GridLayout(allQuestions.size(), 2, 1, 1);
			answers.setLayout(g);
			for (Question q: allQuestions) {
				answers.add(new JLabel(q.getText()));
				if (q.getAnswerType()=="BOOLEAN_KEY"){
					answers.add(new JLabel(questionnaire.getQuestionnaireUI().translateInLaguage(q.getAnswer().toDisplayCorrectAnswer())));
				}
				else{
					answers.add(new JLabel(q.getAnswer().toDisplayCorrectAnswer()));
				}
			}
			JOptionPane.showMessageDialog(null, answers, "Réponses", JOptionPane.PLAIN_MESSAGE);
		}
	}

}

