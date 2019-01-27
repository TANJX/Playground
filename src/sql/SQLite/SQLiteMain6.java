package sql.SQLite;
/*
 * Created by Mars Tan on 11/14/2018.
 * Copyright ISOTOPE Studio
 */

import sql.SQLite.lib.SQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * extracting ABAB word, and add a tag to those words
 */
public class SQLiteMain6 {

    private static final Map<Long, String> changeFormflds = new HashMap<>();
    private static final String tag_name = "”MÉùÕZ";

    public static void main(String args[]) {
        SQLite sql = new SQLite("C:\\Users\\david\\AppData\\Roaming\\Anki2\\Mars", "collection.anki2");
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
            while (result.next()) {
                long row = result.getLong("id");
                String flds = result.getString("flds");
                String sfld = result.getString("sfld");
                String tags = result.getString("tags");
                String[] fields = flds.split("\u001F");

                if (fields.length > 0) {
                    if (sfld.length() >= 4 && sfld.toCharArray()[0] == sfld.toCharArray()[2] && sfld.toCharArray()[1] == sfld.toCharArray()[3]) {
                        tags += tag_name + " ";
                        System.out.println(sfld);
                        changeFormflds.put(row, tags);
                    } else if (fields[1].length() >= 4 && fields[1].toCharArray()[0] == fields[1].toCharArray()[2] && fields[1].toCharArray()[1] == fields[1].toCharArray()[3]) {
                        // System.out.println(flds);
                    }
                }
            }
            for (long row : changeFormflds.keySet()) {
                String s = "UPDATE notes SET tags='" + changeFormflds.get(row) + "' WHERE id = '" + row + "';";
                System.out.println(s);
                statement.executeUpdate(s);
            }
            statement.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
