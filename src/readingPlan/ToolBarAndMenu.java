package readingPlan;

import java.awt.Container;

import javax.swing.*;
import javax.swing.border.*;

public class ToolBarAndMenu extends JPanel {
	public JMenuBar menuBar;
	public JToolBar toolBar;

	public ToolBarAndMenu() {
		super(true);
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		// Create a menu and add it to the menu bar.
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		// Create a toolbar and give it an etched border.
		toolBar = new JToolBar();
		toolBar.setBorder(new EtchedBorder());
		// Instantiate a sample action with the NAME property of "Download" and
		// the appropriate SMALL_ICON property.
		SaveAction saveAction = new SaveAction("Save", new ImageIcon("save.gif"));
		LoadAction loadAction = new LoadAction("Load", new ImageIcon("load.gif"));
		AboutAction aboutAction = new AboutAction("About", new ImageIcon("help.git"));
		// Finally, add the sample action to the menu and the toolbar.
		// These methods are no longer preferred:
		// menu.add(exampleAction);
		// toolBar.add(exampleAction);
		// Instead, you should create actual menu items and buttons:
		JMenuItem saveItem = new JMenuItem(saveAction);
		JMenuItem loadItem = new JMenuItem(loadAction);
		JMenuItem aboutItem = new JMenuItem(aboutAction);
		JButton saveButton = new JButton(saveAction);
		JButton loadButton = new JButton(loadAction);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		helpMenu.add(aboutItem);
		toolBar.add(saveButton);
		toolBar.add(loadButton);
	}

}
