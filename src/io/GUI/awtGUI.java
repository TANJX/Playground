package io.GUI;

import java.awt.*;



public class awtGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame test = new Frame();
		Label lb1 = new Label("This is a Label!");
		Button but1= new Button("Button 1");
		test.setLayout(new FlowLayout());
		test.add(lb1);
		test.add(but1);
		test.setSize(200, 200);
		test.setVisible(true);

	}

}
