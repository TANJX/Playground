package cc.isotopestudio.japanesetest;
/*
 * Created by Mars Tan on 2/17/2017.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.japanesetest.sql.SQLite;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WelcomeGUI {
    private JPanel panel1;
    private JButton hiriganaQuizButton;
    private JButton katakanaQuizButton;

    public WelcomeGUI() {
        hiriganaQuizButton.addActionListener(e -> {
            new TesterGUI(true);
        });
        katakanaQuizButton.addActionListener(e -> {
            new TesterGUI(false);
        });
    }

    static Connection c;
    static Statement statement;

    public static void main(String[] args) {
        SQLite db = new SQLite("C:\\OneDrive\\apps\\Mars' Collection\\Japanese\\", "data.db");

        try {
            c = db.openConnection();
            statement = c.createStatement();
            String sql = "CREATE TABLE record " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " KANA           CHARACTER(1)    NOT NULL, " +
                    " TIME           INT     NOT NULL)";
            statement.executeUpdate(sql);
//            statement.close();
//            c.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
//            return;
        }

        JFrame frame = new JFrame("Welcome");
        frame.setContentPane(new WelcomeGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static void addRecord(String kana, int time) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO record VALUES(?,?,?)");
            ps.setString(1, String.valueOf(System.currentTimeMillis()));
            ps.setString(2, kana);
            ps.setString(3, String.valueOf(time));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
