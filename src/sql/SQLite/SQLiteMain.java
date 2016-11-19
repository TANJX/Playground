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
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteMain {

    private static HashMap<Long, String> changeFormflds = new HashMap<>();
    private static HashMap<Long, String> changeFormsfld = new HashMap<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Database path...");
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
            Pattern pattern = Pattern.compile("\\[sound.*mp3\\]");
            ResultSet result = statement.executeQuery("SELECT * FROM notes");
            while (result.next()) {
                long row = result.getLong("id");
                String flds = result.getString("flds");
                String sfld = result.getString("sfld");
                if (sfld.matches(".*\\[sound.*mp3\\].*")) {
                    System.out.println(flds);
                    changeFormsfld.put(row, sfld.replaceAll("\\[sound.*mp3\\]", "").replace("'","''"));
                    Matcher matcher = pattern.matcher(sfld);
                    if (matcher.find()) {
                        changeFormflds.put(row,
                                flds.replaceAll("<br />\\[sound.*mp3\\]\u001F", "\u001F" + matcher.group(0) + "<br />").replace("'","''"));
                    }
                }

            }
            for (long row : changeFormflds.keySet()) {
                String s = "UPDATE notes SET flds='" + changeFormflds.get(row) + "', sfld='" + changeFormsfld.get(row) + "' WHERE id = '" + row + "';";
                System.out.println(s);
                statement.executeUpdate(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
