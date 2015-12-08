package readingPlan;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class LoadAction extends AbstractAction {
	
	public LoadAction(String text, Icon icon) {
		super(text, icon);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Action [" + e.getActionCommand() + "] performed!");
		// Add Action
		
	}

}
