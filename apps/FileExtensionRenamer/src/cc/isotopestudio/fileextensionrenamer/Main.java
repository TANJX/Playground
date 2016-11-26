package cc.isotopestudio.fileextensionrenamer;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("File Renamer");
		frame.setSize(400, 70);
		frame.add(new MainPanel());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
