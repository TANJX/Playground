package app.japaneseOperation;
/*
 * Created by david on 5/17/2018.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JapaneseCourseVocabParser extends JFrame {

    private JTextField copy = new JTextField();
    private JTextField paste = new JTextField();

    private JapaneseCourseVocabParser() {
        super("Japanese Vocab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(copy);
        pane.add(paste);

        copy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String line = copy.getText();
                String out;
                try {
                    int i1 = line.indexOf("£¨");
                    int i2 = line.indexOf("£©");
                    int i3 = line.indexOf("[");
                    out = line.substring(i1 + 1, i2) + "/"
                            + line.substring(0, i1) + "/"+
                            line.substring(i3);
                } catch (Exception e1) {
                    return;
                }
                paste.setText(out);
            }
        });
        setSize(500,170);
        setVisible(true);
    }

    public static void main(String[] a) {
        new JapaneseCourseVocabParser();
    }
}
