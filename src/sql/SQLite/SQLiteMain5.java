package sql.SQLite;
/*
 * Created by Mars Tan on 11/14/2018.
 * Copyright ISOTOPE Studio
 */

// move google voice to its own field

import sql.SQLite.lib.SQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteMain5 {

    private static HashMap<Long, String> changeFormflds = new HashMap<>();
    private static final Pattern sound_pattern = Pattern.compile("[ ]*\\[sound:[^]]*]");

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
                String[] fields = flds.split("\u001F");

                if(row == 1529859033088L) {
                    System.out.println(flds);
                }
                if (fields.length > 0) {
                    Matcher matcher = sound_pattern.matcher(fields[1]);
                    if (matcher.find()) {
                        String sound = matcher.group();
                        flds = flds.replace(sound, "") + sound.replaceAll("^[ ]*", "");
                        changeFormflds.put(row, flds);
                    }
                }
            }
            for (long row : changeFormflds.keySet()) {
                String s = "UPDATE notes SET flds='" + changeFormflds.get(row) + "' WHERE id = '" + row + "';";
                System.out.println(s);
                statement.executeUpdate(s);
            }
            System.out.println(changeFormflds.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
