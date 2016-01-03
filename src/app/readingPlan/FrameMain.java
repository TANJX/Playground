package app.readingPlan;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class FrameMain extends JFrame {
	
	String[] bookList = {"<Click here to create a new book reading PLAN>"};

	public FrameMain(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ToolBarAndMenu toolbarPane = new ToolBarAndMenu();
		setJMenuBar(toolbarPane.menuBar);
		
		getContentPane().add(toolbarPane.toolBar, BorderLayout.NORTH);

		JPanel bigPanel = new JPanel();
		add(bigPanel, BorderLayout.CENTER);
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createVerticalStrut(10));

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		bigPanel.add(leftPanel);
		bigPanel.add(Box.createHorizontalStrut(20));
		bigPanel.add(rightPanel);
		// leftPanel.setBounds(0, 0, 300, 200);
		// rightPanel.setBounds(300, 200, 300, 200);

		JLabel pagesLabel = new JLabel("Set the total pages of the book");
		leftPanel.add(pagesLabel);
		leftPanel.add(Box.createVerticalStrut(10));
		JSpinner pagesSpinner = new JSpinner();
		leftPanel.add(pagesSpinner);
		leftPanel.add(Box.createVerticalStrut(50));
		JLabel daysLabel = new JLabel("Set the total days of the reading process");
		leftPanel.add(daysLabel);
		leftPanel.add(Box.createVerticalStrut(10));
		JSpinner daysSpinner = new JSpinner();
		leftPanel.add(daysSpinner);
		leftPanel.add(Box.createVerticalStrut(50));
		
		JLabel finshedPagesLabel = new JLabel("Set the finished pages of the books");
		leftPanel.add(finshedPagesLabel);
		leftPanel.add(Box.createVerticalStrut(10));
		JSpinner finshedPagesSpinner = new JSpinner();
		///leftPanel.add(Box.createRigidArea (new Dimension(15, 15))); 
		leftPanel.add(finshedPagesSpinner);
		leftPanel.add(Box.createVerticalStrut(50));
		

		JLabel listsLabel = new JLabel("The List of Books");
		rightPanel.add(listsLabel, BorderLayout.NORTH);
		
		JList BooksList = new JList(bookList);
		rightPanel.add(BooksList, BorderLayout.CENTER);
		JPanel rightSouthPanel = new JPanel();
		JButton addButton = new JButton("Add");
		rightSouthPanel.add(addButton);
		JButton removeButton = new JButton("Remove");
		rightSouthPanel.add(removeButton);
		rightPanel.add(rightSouthPanel, BorderLayout.SOUTH);
		
		
		
		setSize(600, 400);
		// setResizable(false);

	}

}
