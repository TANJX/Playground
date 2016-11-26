package sql.SQLite;/*
 * Created by Mars Tan on 11/19/2016.
 * Copyright ISOTOPE Studio
 */

import sql.SQLite.lib.SQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.regex.Pattern;

public class SQLiteMain3 {

    private static HashMap<Long, String> changeFormflds = new HashMap<>();

    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Input Database path...");
//        String path = sc.next();
        SQLite sql = new SQLite("C:/Users/Mars Tan/Desktop/", "collection.anki2");
        Connection c;
        Statement statement;
        try {
            c = sql.openConnection();
            statement = c.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM notes");
            int count = 0;
            while (result.next()) {
                long row = result.getLong("id");
                String flds = result.getString("flds").replaceAll("'", "''");
                String sfld = result.getString("sfld").replaceAll("'", "''");

                if (hitNum(flds, "\u001F") > 3) {
                    changeFormflds.put(row,
                            sfld + "\u001F" + flds.replaceFirst(sfld + "\u001F", "").replace("\u001F", "<br />") + "\u001F\u001F\u001F");
                    count++;
                }
            }
            System.out.println(count);
            for (long row : changeFormflds.keySet()) {
                String s = "UPDATE notes SET flds='" + changeFormflds.get(row) + "' WHERE id = '" + row + "';";
                System.out.println(s);
                statement.executeUpdate(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int hitNum(String s, String search) {
        int cnt = 0;
        int offset = 0;
        while ((offset = s.indexOf(search, offset)) != -1) {
            offset = offset + search.length();
            cnt++;
        }
        return cnt;
    }
}
