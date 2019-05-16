package plugin.editor;

import javax.swing.*;
import files.*;
import plugin.*;

import java.awt.Frame;
import java.awt.event.*;
import java.io.File;

/** Class corresponding to the interface for the plugin project
 * 
 * @author Leane Texier
 *
 */
public class Editor {
	/* Attributs */
	protected JFrame frame;
	protected FileChecker fc;
	protected JMenu toolsMenu;
	protected JMenu helpMenu;
	protected JTextArea text;
	
	/** Creates an editor 
	 * Creates an interface for the plugin with a FileMenu, a ToolsMenu, a HelpMenu and a textArea
	 */
	public Editor(){
		// File Checker 
		this.fc = new FileChecker(new PluginFilter(), new File("repository"));
		fc.addFileListener(new PluginListener());
		// Frame 
		this.frame= new JFrame("Plugins");
		frame.addWindowListener(new CloseWindowEvent());
		frame.setLocation(200, 100);
		frame.setSize(700, 600);
		// Text
		this.text = new JTextArea();
		JScrollPane sp = new JScrollPane(this.text);
		frame.add(sp);
		// JMenu
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new FileMenu(this));
		this.toolsMenu = new JMenu("Tools");
		this.helpMenu = new JMenu("Help");
		menuBar.add(this.toolsMenu);
		menuBar.add(this.helpMenu);
		frame.setJMenuBar(menuBar);	

		frame.setVisible(true);
	}
	
	/** Return the Frame of the editor
	 * @return the Frame 
	 */
	public Frame getFrame(){
		return this.frame;
	}
	
	/** Return the FileChecker of the editor
	 * @return the FileChecker 
	 */
	public FileChecker getFileChecker(){
		return this.fc;
	}
	
	/** Return the ToolsMenu of the editor
	 * @return the ToolsMenu
	 */
	public JMenu getToolsMenu(){
		return this.toolsMenu;
	}
	
	/** Return the HelpMenu of the editor
	 * @return the HelpMenu 
	 */
	public JMenu getHelpMenu(){
		return this.helpMenu;
	}
	
	
	/** Return the JTextArea of the editor
	 * @return the JTextArea 
	 */
	public JTextArea getText(){
		return this.text;
	}
	
	/** Return the content of the JTextArea of the editor
	 * @return the content of the JTextArea
	 */
	public String getContentText() {
		return this.text.getText();
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
	

	/** Class who implements the interface FileListener.
	 * The action is to add or removed the plugin in the editor (in the toolsMenu and in the helpMenu)
	 * 
	 * @author Leane Texier
	 *
	 */
	private class PluginListener implements FileListener{

		/** The action do when a plugin is added
		 * @param e the FileEvent associated
		 */
		public void fileAdded(FileEvent e) {
			Plugin p = this.getPluginFromEvent(e);
			String label = p.getLabel();
			//Add to Tools Menu
			JMenuItem itTools = new JMenuItem(label);
			itTools.addActionListener(new DoAction(p));
			toolsMenu.add(itTools);
			//Add to Help menu
			JMenuItem itHelp = new JMenuItem(label);
			itHelp.addActionListener(new ShowHelp(p));
			helpMenu.add(itHelp);
		}

		
		/** The action do when a plugin is removed
		 * @param e the FileEvent associated
		 */
		public void fileRemoved(FileEvent e) {
			Plugin p = this.getPluginFromEvent(e);
			String label = p.getLabel();
			//Remove to Tools Menu
			Boolean find = false;
			for (int i=0; i<toolsMenu.getItemCount() && !find; i++){
				if (toolsMenu.getItem(i).getText().equals(label)){
					find = true;
					toolsMenu.remove(i);
				}
			}
			//Remove to Help menu
			find = false;
			for (int i=0; i<helpMenu.getItemCount() && !find; i++){
				if (helpMenu.getItem(i).getText().equals(label)){
					find = true;
					helpMenu.remove(i);
				}
			}
		}
		
		/** Return the plugin associated to the FileEvent (null if there isn't plugin associated)
		 * @param e the FileEvent
		 * @return the plugin associated to the FileEvent
		 */
		public Plugin getPluginFromEvent(FileEvent e) {
			String name = e.getFileName();
			Plugin p = null;
			try {
				name = name.substring(0, name.indexOf(".class"));
				Class<?> c = Class.forName("plugins."+name);
				p = (Plugin) c.newInstance();
			} catch (Exception ex) {}
			return p;
		}
	}
	
	/** Class who implements the interface ActionListener and transform the text of the editor thanks to the plugin chosen when we click on the item associated
	 * 
	 * @author Leane Texier
	 */
	private class DoAction implements ActionListener{
		/* Attribut */
		private Plugin plugin;
		
		/** Creates a DoAction
		 * @param p the plugin associated
		 */
		private DoAction(Plugin p){
			super();
			this.plugin = p;
		}

		/** Transform the text of the editor thanks to the plugin
		 * @param e the ActionEvent associated
		 */
		public void actionPerformed(ActionEvent e) {
			text.setText(plugin.transform(getContentText()));
		}
		
	}
	
	/** Class who implements the interface ActionListener and display the help Message associated to the plugin when we click on the item associated
	 * 
	 * @author texierl
	 */
	private class ShowHelp implements ActionListener{
		/* Attribut */
		private Plugin plugin;
		
		/** Creates a ShowHelp
		 * @param p the plugin associated
		 */
		private ShowHelp(Plugin p) {
			super();
			this.plugin = p;
		}

		/** Display in a showMessageDialog the help message of the plugin
		 * @param e the ActionEvent associated
		 */
		public void actionPerformed(ActionEvent e) {
			JPanel fenetreDialogue = new JPanel();
			fenetreDialogue.add(new JLabel(this.plugin.helpMessage()));
			JOptionPane.showMessageDialog(null, fenetreDialogue, "Help", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	
}
