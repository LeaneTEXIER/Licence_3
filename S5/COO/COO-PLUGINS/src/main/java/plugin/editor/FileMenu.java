package plugin.editor;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/** Class who creates a FileMenu in swing
 * 
 * @author Leane Texier
 *
 */
public class FileMenu extends JMenu{
	/* Attributs */
	private static final long serialVersionUID = 1L;
	protected Editor editor;
	
	/** Creates a FileMenu
	 * @param editor the editor linked to this FileMenu
	 */
	public FileMenu(Editor editor){
		super("File");
		this.editor = editor;
		this.add(this.newItem());
		this.add(this.openItem());
		this.add(this.exitItem());
	}
	
	/** Return the editor linked to this FileMenu
	 * @return the editor
	 */
	public Editor getEditor(){
		return this.editor;
	}
	
	/** Creates an item named 'new' and do the 'NewItemAction' when we click on it
	 * @return an item named 'new' who has a ActionListener
	 */
	public JMenuItem newItem(){
		JMenuItem it = new JMenuItem("New");
		it.addActionListener(new NewItemAction());
		return it;
	}
	
	/** Creates an item named 'open' and do the 'OpenItemAction' when we click on it
	 * @return an item named 'open' who has a ActionListener
	 */
	public JMenuItem openItem(){
		JMenuItem it = new JMenuItem("Open");
		it.addActionListener(new OpenItemAction());
		return it;
	}
	
	/** Creates an item named 'exit' and do the 'ExitItemAction' when we click on it
	 * @return an item named 'exit' who has a ActionListener
	 */
	public JMenuItem exitItem(){
		JMenuItem it = new JMenuItem("Exit");
		it.addActionListener(new ExitItemAction());
		return it;
	}
	
	
	/** Class who implements the interface ActionListener and reset the text of the editor when we click on the item associated
	 * 
	 * @author Leane Texier
	 */
	private class NewItemAction implements ActionListener{
		/** Reset the text of the editor (clears the TextArea)
		 * @param e the ActionEvent associated
		 */
		public void actionPerformed(ActionEvent e) {
			editor.text.setText("");			
		}
	}
	
	/** Class who implements the interface ActionListener and open the file choose by the user when we click on the item associated
	 * It puts the text present in the file chosen in the textArea of the editor
	 * 
	 * @author Leane Texier
	 */
	private class OpenItemAction implements ActionListener{
		/** Set the text of the editor with the file chosen by the user
		 * @param e the ActionEvent associated
		 */
		public void actionPerformed(ActionEvent e) {
			// Ask which file the user want
			JFileChooser chooser = new JFileChooser();
			int val = chooser.showOpenDialog(chooser);
			if (val == JFileChooser.APPROVE_OPTION) {
				// Get the file choose by the user
				File f = chooser.getSelectedFile();
				// Read the file if it's possible
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(f));
					StringBuffer file = new StringBuffer();
					String line;
					while((line = reader.readLine()) != null){
						file.append(line);
						file.append("\n");
					}
					reader.close();
					String s = file.toString();
					// Write the file in the JTextArea
					editor.text.setText(s);
				} catch (Exception ex) {}				
			}
		}
	}
	
	/** Class who implements the interface ActionListener and quit the editor when we click on the item associated
	 * 
	 * @author Leane Texier
	 */
	private class ExitItemAction implements ActionListener{
		/** Exit the editor
		 * @param e the ActionEvent associated
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
