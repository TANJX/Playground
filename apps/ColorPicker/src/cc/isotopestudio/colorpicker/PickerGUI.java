package cc.isotopestudio.colorpicker;
/*
 * Created by david on 2017/1/6.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;

public class PickerGUI {
    private JPanel panel1;
    private JTextField redText;
    private JTextField greenText;
    private JTextField blueText;
    private JTextField colorText;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PickerGUI");
        PickerGUI pickerGUI = new PickerGUI();
        frame.setContentPane(pickerGUI.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        pickerGUI.update();
    }

    public void update() {
        ColorPicker cp = new ColorPicker();
        while (true) {
            try {
                Point point = MouseInfo.getPointerInfo().getLocation();
                Color color = new Color(cp.getPixel((int) point.getX(), (int) point.getY()));
                redText.setText("" + color.getRed());
                greenText.setText("" + color.getGreen());
                blueText.setText("" + color.getBlue());
                colorText.setForeground(color);
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
