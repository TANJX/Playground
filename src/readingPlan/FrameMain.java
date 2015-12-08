package readingPlan;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class FrameMain extends JFrame {
	
	

	public FrameMain(String title){
		super(title);
		ToolBarAndMenu toolbarPane = new ToolBarAndMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(toolbarPane.menuBar);
		getContentPane().add(toolbarPane.toolBar, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		JSpinner pagesSpinner = new JSpinner();
		panel.add(pagesSpinner);
		JSpinner daysSpinner = new JSpinner();
		panel.add(daysSpinner);
		JButton addButton = new JButton("Add");
		panel.add(addButton);
		JButton removeButton = new JButton("Remove");
		panel.add(removeButton);
		setSize(600, 400);
		
	}

}
