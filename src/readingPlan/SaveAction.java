package readingPlan;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class SaveAction extends AbstractAction {
	
	public SaveAction(String text, Icon icon) {
		super(text, icon);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Action [" + e.getActionCommand() + "] performed!");
		// Add Action
		
	}

}
