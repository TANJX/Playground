package app.readingPlan;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class AboutAction extends AbstractAction {
	
	public AboutAction(String text, Icon icon) {
		super(text, icon);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Action [" + e.getActionCommand() + "] performed!");
		// Add Action
		JOptionPane.showMessageDialog(null, "ISOTOPE Studio\nMars");
		
	}

}
