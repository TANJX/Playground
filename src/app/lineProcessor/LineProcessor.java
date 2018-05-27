package app.lineProcessor;
/*
 * Created by david on 5/21/2018.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineProcessor {
    private JPanel mainPane;
    private JTextPane inputText;
    private JTextPane outputPane;

    public LineProcessor() {
        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                List<String> texts = Arrays.asList(inputText.getText().split("\r\n"));
                outputPane.setText(texts.stream()
                        .map(s -> s.replaceAll("</.*>", "").replaceAll("<.*>", ""))
                        .collect(Collectors.joining()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LineProcessor");
        frame.setContentPane(new LineProcessor().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
