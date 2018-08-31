package com.marstanjx.notes;
/*
 * Created by david on 2018/08/31.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JapaneseCourseVocabParser extends JFrame {

    private static final List<Character> ALPHABET = Arrays.asList(

            'あ', 'い', 'う', 'え', 'お',
            'か', 'き', 'く', 'け', 'こ',
            'さ', 'し', 'す', 'せ', 'そ',
            'た', 'ち', 'つ', 'て', 'と',
            'な', 'に', 'ぬ', 'ね', 'の',
            'は', 'ひ', 'ふ', 'へ', 'ほ',
            'ま', 'み', 'む', 'め', 'も',
            'や', 'ゆ', 'よ',
            'ら', 'り', 'る', 'れ', 'ろ',
            'わ', 'を',

            'ア', 'イ', 'ウ', 'エ', 'オ',
            'カ', 'キ', 'ク', 'ケ', 'コ',
            'サ', 'シ', 'ス', 'セ', 'ソ',
            'タ', 'チ', 'ツ', 'テ', 'ト',
            'ナ', 'ニ', 'ヌ', 'ネ', 'ノ',
            'ハ', 'ヒ', 'フ', 'ヘ', 'ホ',
            'マ', 'ミ', 'ム', 'メ', 'モ',
            'ヤ', 'ユ', 'ヨ',
            'ラ', 'リ', 'ル', 'レ', 'ロ',
            'ワ', 'ヲ',

            'ゃ', 'ゅ', 'ょ',
            'ャ', 'ュ', 'ョ', 'ー', 'っ'

    );

    private JTextField copy = new JTextField();
    private JTextField paste = new JTextField();

    private JapaneseCourseVocabParser() {
        super("Japanese Vocab 1.2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(copy);
        pane.add(paste);
        JButton runbtn1 = new JButton("/ + out");
        pane.add(runbtn1);
        JButton runbtn2 = new JButton("out");
        pane.add(runbtn2);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        runbtn2.addActionListener(e -> {
            try {
                String line = (String) clipboard.getData(DataFlavor.stringFlavor);
                String out = process(line);
                copy.setText(line);
                if (out.equals("")) return;
                paste.setText(out);
                StringSelection stringSelection = new StringSelection(out);
                clipboard.setContents(stringSelection, null);
            } catch (UnsupportedFlavorException | IOException e1) {
                e1.printStackTrace();
            }
        });
        runbtn1.addActionListener(e -> {
            try {
                String line = (String) clipboard.getData(DataFlavor.stringFlavor);
                String out = process(line);
                copy.setText(line);
                if (out.equals("")) return;
                out = "/" + out;
                paste.setText(out);
                StringSelection stringSelection = new StringSelection(out);
                clipboard.setContents(stringSelection, null);
            } catch (UnsupportedFlavorException | IOException e1) {
                e1.printStackTrace();
            }
        });
        copy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String out = process(copy.getText());
                paste.setText(out);
                StringSelection stringSelection = new StringSelection(out);
                clipboard.setContents(stringSelection, null);
            }
        });
        setSize(500, 170);
        setVisible(true);
    }

    private static String process(String line) {
        String out;
        try {
            int i1 = line.indexOf("（");
            int i2 = line.indexOf("）");
            int i3 = line.indexOf("[");
            if (i3 < 0)
                for (int i = line.length() - 1; i > 0; i--) {
                    if (line.charAt(i) == '　') {
                        i3 = i + 1;
                        break;
                    }
                }
            if (i3 <= 0) return "";

            String word = line.substring(i1 + 1, i2);
            String pron = line.substring(0, i1);
            String meaning = line.substring(i3);
            if (word.chars().mapToObj(c -> (char) c).allMatch(ALPHABET::contains)) {
                String temp = word;
                word = pron;
                pron = temp;
            }
            out = word + "/" + pron + "/" + meaning + "/";
        } catch (Exception e1) {
            return "";
        }
        return out;
    }

    public static void main(String[] a) {
        new JapaneseCourseVocabParser();
    }
}
