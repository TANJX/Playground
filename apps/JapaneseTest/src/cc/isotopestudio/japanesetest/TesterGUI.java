package cc.isotopestudio.japanesetest;
/*
 * Created by Mars Tan on 2/16/2017.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.japanesetest.sql.SqlManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TesterGUI {
    private final int testID;
    private final List<String> kana;

    private JTextField AnswerField;
    private JPanel panel1;
    private JTextField QuestionField;
    private JTextArea infoArea;
    private JLabel testIDLabel;
    private JLabel progressLabel;

    private boolean started = false;
    private long time = -1;


    TesterGUI(boolean hiragana) {
        if (hiragana) {
            String remain = SqlManager.getLastTestFinished(true);
            if (remain == null) {
                // Start a new test
                kana = new ArrayList<>(Arrays.asList(Japanese.HIRAGANA));
                SqlManager.addTest(true, getKanaString());
            } else {
                // Continue on last test
                kana = new ArrayList<>(Arrays.asList(remain.split(" ")));
            }
            testID = SqlManager.getLastTestID(true);
        } else {
            String remain = SqlManager.getLastTestFinished(false);
            if (remain == null) {
                // Start a new test
                kana = new ArrayList<>(Arrays.asList(Japanese.KATAKANA));
                SqlManager.addTest(false, getKanaString());
            } else {
                // Continue on last test
                kana = new ArrayList<>(Arrays.asList(remain.split(" ")));
            }
            testID = SqlManager.getLastTestID(false);
        }
        testIDLabel.setText(String.valueOf(testID));
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
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

    }

    private void update() {
        if (!started) {
            started = true;
            QuestionField.setFont(new Font("Microsoft YaHei", 0, 100));
            update();
        } else {
            if (time != -1) {
                int diff = (int) (System.currentTimeMillis() - time);
                int mili = ((diff) % 1000);
                int sec = ((diff) / 1000);
                infoArea.setText(infoArea.getText() + "\n" + QuestionField.getText() + ": " + sec + "." + mili);
                SqlManager.addRecord(testID, QuestionField.getText(), diff);
                SqlManager.setRemain(testID, getKanaString());
                System.out.println(diff);
            }
            if (kana.size() > 0) {
                QuestionField.setText(kana.remove(0));
                AnswerField.setText("");
                time = System.currentTimeMillis();
                progressLabel.setText((45 - kana.size()) + " / " + 45);
            } else {
                // FINISH
                SqlManager.finishTest(testID);
                QuestionField.setFont(new Font("Microsoft YaHei", 0, 30));
                AnswerField.setFont(new Font("Microsoft YaHei", 0, 30));
                QuestionField.setText("FINISHED");
                AnswerField.setText("FINISHED");
                AnswerField.setEditable(false);
            }
        }
    }

    private String getKanaString() {
        String result = "";
        for (String s : kana) {
            result += s + " ";
        }
        return result;
    }
}
