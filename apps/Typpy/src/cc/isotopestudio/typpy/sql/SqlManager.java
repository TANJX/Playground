package cc.isotopestudio.typpy.sql;
/*
 * Created by david on 2017/2/17.
 * Copyright ISOTOPE Studio
 */

import java.io.File;
import java.sql.*;

public abstract class SqlManager {
    public static Connection c;
    public static Statement statement;

    public static void init() {
//        SQLite db = new SQLite("C:\\OneDrive\\apps\\Mars' Collection\\Typpy\\", "data.db");

        SQLite db = new SQLite(new File("").getAbsolutePath(), "data.db");

        try {
            c = db.openConnection();
            statement = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS record " +
                    "(DATE      INT PRIMARY KEY  NOT NULL," +
                    " FILE      INT              NOT NULL," +
                    " RESPONSE  INT              NOT NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addRecord(String filename, long time) {
        new Thread(() -> {
            try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO record VALUES(?,?,?);");
                ps.setString(1, String.valueOf(System.currentTimeMillis()));
                ps.setString(2, String.valueOf(filename));
                ps.setString(3, String.valueOf(time));
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String getLastTestFinished(boolean hiragana) {
        String sql;
        sql = hiragana ? "SELECT * FROM test WHERE TYPE=\"HIRAGANA\" " +
                "ORDER BY ID DESC;" : "SELECT * FROM test WHERE TYPE=\"KATAKANA\" " +
                "ORDER BY ID DESC;";
        try {
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return res.getString("REMAIN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getLastTestID(boolean hiragana) {
        String sql;
        sql = hiragana ? "SELECT * FROM test WHERE TYPE=\"HIRAGANA\" " +
                "ORDER BY ID DESC;" : "SELECT * FROM test WHERE TYPE=\"KATAKANA\" " +
                "ORDER BY ID DESC;";
        try {
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return res.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
