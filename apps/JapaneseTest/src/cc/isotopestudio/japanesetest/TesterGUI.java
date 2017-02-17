package cc.isotopestudio.japanesetest;
/*
 * Created by Mars Tan on 2/16/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TesterGUI {
    private final boolean TYPE;
    private final List<String> kana;

    private JTextField AnswerField;
    private JPanel panel1;
    private JTextField QuestionField;
    private JTextArea infoArea;

    private boolean started = false;
    private long time = -1;


    TesterGUI(boolean hiragana) {
        this.TYPE = hiragana;
        if (TYPE) {
            kana = new ArrayList<>(Arrays.asList(Japanese.HIRAGANA));
        } else {
            kana = new ArrayList<>(Arrays.asList(Japanese.KATAKANA));
        }
        Collections.shuffle(kana);
        QuestionField.setFont(new Font("Microsoft YaHei", 0, 30));
        QuestionField.setText("Start");
        AnswerField.setText("");
        AnswerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (started && AnswerField.getText().equals(Japanese.getRomaji(QuestionField.getText()))
                        || AnswerField.getText().equalsIgnoreCase("Start")) {
                    update();
                    e.consume();
                }
            }
        });
        JFrame frame = new JFrame("TesterGUI");
        frame.setContentPane(panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void update() {
        if (!started) {
            started = true;
            QuestionField.setFont(new Font("Microsoft YaHei", 0, 100));
            update();
        } else {
            if (time != -1) {
                int diff = (int) (System.currentTimeMillis() - time);
                int mili = (int) ((diff) % 1000);
                int sec = (int) ((diff) / 1000);
                infoArea.setText(infoArea.getText() + "\n" + QuestionField.getText() + ": " + sec + "." + mili);
                WelcomeGUI.addRecord(QuestionField.getText(), diff);
                System.out.println(diff);
            }
            if (kana.size() > 0) {
                QuestionField.setText(kana.remove(0));
                AnswerField.setText("");
                time = System.currentTimeMillis();
            } else {
                // FINISH
                QuestionField.setFont(new Font("Microsoft YaHei", 0, 30));
                AnswerField.setFont(new Font("Microsoft YaHei", 0, 30));
                QuestionField.setText("FINISHED");
                AnswerField.setText("FINISHED");
                AnswerField.setEditable(false);
            }
        }

    }
}
