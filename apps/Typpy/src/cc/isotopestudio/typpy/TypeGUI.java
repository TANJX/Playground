package cc.isotopestudio.typpy;
/*
 * Created by david on 2017/3/17.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TypeGUI {
    private JTextField aText;
    private JPanel mainPane;
    private JLabel timeLabel;
    private JLabel progressLabel;
    private JTextField tText;
    private JTextField qTextA;
    private JTextField qTextB;
    private JLabel speedLabel;

    private final String filename;
    private final String text;

    private long start;
    private int position = 0;
    private boolean started;
    private boolean finished;
    private int errors = 0;
    private java.util.List<Integer> secPos = new ArrayList<>();

    public TypeGUI(String filename, String rawText) {
        this.filename = filename;
        this.text = processString(rawText);
        JFrame frame = new JFrame(filename);
        frame.setContentPane(mainPane);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);

        qTextB.setText(text);

        Thread timerThread = new Thread(() -> {

            while (!finished) {
                secPos.add(position);
                timeLabel.setText("Consumed time: " + ((System.currentTimeMillis() - start) / 1000) + "s");
                if (secPos.size() > 1) {
                    speedLabel.setText(secPos.get(secPos.size() - 1) - secPos.get(secPos.size() - 2) + " char per sec");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        aText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
                if (!started) {
                    started = true;
                    start = System.currentTimeMillis();
                    timerThread.start();
                }
//                System.out.println(text.charAt(position) + " " + e.getKeyChar());
                if (isSame(text.charAt(position), e.getKeyChar())) {
                    updateProgress();
                    position++;
                    if (position == text.length()) {
                        frame.dispose();
                        Typpy.welcomeGUI.finish(filename, System.currentTimeMillis() - start);
                    }
                } else {
                    aText.setCaretColor(new Color(255, 48, 50));
                    aText.setText("");
                    errors++;
                }
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Typpy.welcomeGUI.finish();
            }
        });
        updateProgress();
    }

    private void updateProgress() {
        aText.setCaretColor(new Color(255, 255, 255));
        tText.setText(text.substring(0, position));
        qTextA.setText(text.substring(0, position));
        qTextB.setText(text.substring(position, Math.min(position + 33, text.length())));
        aText.setText("");
        progressLabel.setText(Math.min(100,
                Math.round(100.0 * tText.getText().length() / text.length())) + "%");
    }

    private String processString(String s) {
        return s.replaceAll("  ", " ").replaceAll("\t", " ");
    }

    private boolean isSame(char a, char b) {
        if (a == b) return true;
        if (a == '¡¯') return b == '\'';
        return false;
    }

}
