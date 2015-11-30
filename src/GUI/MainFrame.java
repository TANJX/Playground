package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private DetailedPanel detailsPanel;
	
	public MainFrame(String title) {
		super(title);
		// set layout manager
		setLayout(new BorderLayout());

		// create swing component
		JTextField textfield = new JTextField();
		JButton jbt = new JButton("This is a button");
		JLabel jbl = new JLabel("This is a label!");
		
		detailsPanel = new DetailedPanel();
		
		// add swing component to content panel
		Container c = getContentPane();
		c.add(jbt, BorderLayout.SOUTH);
		c.add(textfield, BorderLayout.NORTH);
		c.add(detailsPanel, BorderLayout.CENTER);
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// add behavior
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button clicked");
				textfield.setText("Hello");
				jbt.setVisible(false);
			}
		});
	}

}
