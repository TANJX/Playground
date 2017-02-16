package cc.isotopestudio.japanesetest;
/*
 * Created by Mars Tan on 2/16/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static cc.isotopestudio.japanesetest.Japanese.HIRAGANA;

public class TesterGUI {
    private JTextField AnswerField;
    private JPanel panel1;
    private JTextField QuestionField;
    private JTextArea infoArea;

    private int current;
    private long time = -1;

    private TesterGUI() {
        update();
        AnswerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (AnswerField.getText().equals(Japanese.ROMAJI[current])) {
                    update();
                    e.consume();
                }
            }
        });
    }

    private void update() {
        if (time != -1) {
            long diff = System.currentTimeMillis() - time;
            int mili = (int) ((diff) % 1000);
            int sec = (int) ((diff) / 1000);
            infoArea.setText(infoArea.getText() + "\n" + HIRAGANA[current] + ": " + sec + "." + mili);
            System.out.println(diff);
        }
        current = (int) (Math.random() * HIRAGANA.length);
        QuestionField.setText(String.valueOf(HIRAGANA[current]));
        AnswerField.setText("");
        time = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Japanese Tester");
        frame.setContentPane(new TesterGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
