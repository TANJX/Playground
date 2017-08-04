package cc.isotopestudio.phonetest;
/*
 * Created by Mars Tan on 8/3/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PhoneTestGUI {
    private JPanel panel1;
    private JTextField phoneNumField;
    private JTextField inputField;
    private JProgressBar timerBar;

    public static void main(String[] args) {
        new PhoneTestGUI();
    }

    private long stime;
    private long etime;

    public PhoneTestGUI() {
        JFrame frame = new JFrame("PhoneTestGUI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);
        String phoneNum = PhoneNumUtil.genRandomPhoneNum();
        this.phoneNumField.setText(phoneNum);
        inputField.setEditable(false);
        stime = System.currentTimeMillis();
        etime = stime + 1000 * 10;
        Thread t = new Thread(new ProgressBarUpdate());
        t.start();
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String input = inputField.getText() + e.getKeyChar();
                if (input.equals(phoneNum)) {
                    new Thread(() -> {
                        phoneNumField.setText("CORRECT!");
                    }).start();
                }
            }
        });
    }

    private class ProgressBarUpdate implements Runnable {

        @Override
        public void run() {
            while (System.currentTimeMillis() < etime) {
                timerBar.setValue((int) (100.0 * (System.currentTimeMillis() - stime) / (etime - stime)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            phoneNumField.setText("");
            inputField.setEditable(true);
        }
    }
}
