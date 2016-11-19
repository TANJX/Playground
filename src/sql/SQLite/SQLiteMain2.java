package sql.SQLite;/*
 * Created by Mars Tan on 11/19/2016.
 * Copyright ISOTOPE Studio
 */

import sql.SQLite.lib.SQLite;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteMain2 {

    private static HashMap<Long, String> changeFormflds = new HashMap<>();
    private static HashMap<Long, String> changeFormsfld = new HashMap<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
//        String path = sc.next();
        SQLite sql1 = new SQLite("C:/Users/Mars Tan/Desktop/", "collection.anki2");
        SQLite sql2 = new SQLite("C:/Users/Mars Tan/Desktop/", "collection (2).anki2");
        Connection c1;
        Statement statement1;
        Connection c2;
        Statement statement2;
        try {
            c1 = sql1.openConnection();
            statement1 = c1.createStatement();
            c2 = sql2.openConnection();
            statement2 = c2.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            ResultSet result = statement1.executeQuery("SELECT * FROM notes");
            while (result.next()) {
                ResultSet result2 = statement2.executeQuery("SELECT * FROM notes WHERE id='" + result.getLong("id") + "';");
                if(!result2.next()) {
                    System.out.println(result.getString("flds"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
