package app.newLineEveryCharacter;
/*
 * Created by Mars Tan on 2/7/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainGUI {
    private JTextPane inputTextPane;
    private JPanel panel1;
    private JTextPane outputTextPane;
    private JCheckBox RANDOMCheckBox;
    private JRadioButton spaceRadioButton;
    private JRadioButton characterRadioButton;
    private JScrollPane outputPane;
    private JScrollPane intputPane;

    private MainGUI() {

        inputTextPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                update(String.valueOf(e.getKeyChar()));
            }
        });
        RANDOMCheckBox.addActionListener(e -> update(""));
        spaceRadioButton.addActionListener(e -> update(""));
        characterRadioButton.addActionListener(e -> update(""));
    }

    private void update(String additonal) {
        if (spaceRadioButton.isSelected()) {
            if (RANDOMCheckBox.isSelected()) {
                outputTextPane.setText(newLineEverySpaceRandom(inputTextPane.getText() + additonal));
            } else {
                outputTextPane.setText(newLineEverySpace(inputTextPane.getText() + additonal));
            }
        }
        if (characterRadioButton.isSelected()) {
            if (RANDOMCheckBox.isSelected()) {
                outputTextPane.setText(getRandomCharacter(inputTextPane.getText() + additonal));
            } else {
                outputTextPane.setText(getCharacter(inputTextPane.getText() + additonal));
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NewLineEveryCharacter");
        frame.setContentPane(new MainGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static String getCharacter(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '\n' && c != '\r')
                sb.append(c).append("\n");
        }
        return sb.toString().replaceAll("\u0016", "");
    }

    private static String getRandomCharacter(String s) {
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (c != '\n' && c != '\r' && c != '\u0016')
                list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c).append("\n");
        }
        return sb.toString().replaceAll("\u0016", "");
    }

    private static String newLineEverySpace(String s) {
        s = s.replaceAll("\r\n", " ").replaceAll("\n\r", " ").replaceAll("\r", " ").replaceAll("\n", " ");
        StringBuilder sb = new StringBuilder();
        for (String s1 : s.split(" ")) {
            sb.append(s1).append("\n");
        }
        return sb.toString().replaceAll("\u0016", "");
    }

    private static String newLineEverySpaceRandom(String s) {
        s = s.replaceAll("\r\n", " ").replaceAll("\n\r", " ").replaceAll("\r", " ").replaceAll("\n", " ");
        List<String> list = Arrays.asList(s.split(" "));
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (String c : list) {
            sb.append(c).append("\n");
        }
        return sb.toString().replaceAll("\u0016", "");
    }

}
