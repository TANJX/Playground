package cc.isotopestudio.japanesetest.sql;
/*
 * Created by david on 2017/2/17.
 * Copyright ISOTOPE Studio
 */

import java.sql.*;

public class SqlManager {
    public static Connection c;
    public static Statement statement;

    public static void init() {
        SQLite db = new SQLite("O:\\OneDrive\\apps\\Mars' Collection\\Japanese\\", "data.db");

        try {
            c = db.openConnection();
            statement = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS record " +
                    "(DATE      INT PRIMARY KEY  NOT NULL," +
                    " TEST      INT              NOT NULL," +
                    " KANA      CHARACTER(1)     NOT NULL," +
                    " RESPONSE  INT              NOT NULL)";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS test " +
                    "(ID       INTEGER PRIMARY KEY  AUTOINCREMENT," +
                    " TYPE     TEXT    ," +
                    " REMAIN   TEXT    ," +
                    " DATE     INT     NOT NULL," +
                    " RESPONSE INT     )";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addRecord(int testid, String kana, int time) {
        new Thread(() -> {
            try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO record VALUES(?,?,?,?);");
                ps.setString(1, String.valueOf(System.currentTimeMillis()));
                ps.setString(2, String.valueOf(testid));
                ps.setString(3, kana);
                ps.setString(4, String.valueOf(time));
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

    public static void addTest(boolean hiragana, String kana) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO test (TYPE, REMAIN, DATE, RESPONSE) VALUES(?,?,?,?);");
            if (hiragana)
                ps.setString(1, "HIRAGANA");
            else {
                ps.setString(1, "KATAKANA");
            }
            ps.setString(2, kana);
            ps.setString(3, String.valueOf(System.currentTimeMillis()));
            ps.setString(4, null);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setRemain(int id, String kana) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE test SET REMAIN = ? WHERE ID = ?;");
            ps.setString(1, kana);
            ps.setString(2, String.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void finishTest(int id) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE test SET REMAIN = ? WHERE ID = ?;");
            ps.setString(1, null);
            ps.setString(2, String.valueOf(id));
            ps.executeUpdate();
            String sql = "SELECT * FROM record WHERE TEST=\"" + id + "\";";
            int time = 0;
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                time += res.getInt("RESPONSE");
            }
            ps = c.prepareStatement("UPDATE test SET RESPONSE = ? WHERE ID = ?;");
            ps.setString(1, String.valueOf(time));
            ps.setString(2, String.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
