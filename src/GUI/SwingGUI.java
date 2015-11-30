package GUI;

import javax.swing.*;

public class SwingGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame jfr = new MainFrame("Mars TESTing");
			}
		});

	}

}
