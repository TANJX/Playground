package sql.SQLite;
/*
 * Created by Mars Tan on 11/14/2018.
 * Copyright ISOTOPE Studio
 */

// generate verb entry, reverse pronunciation and kanji

import sql.SQLite.lib.SQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteMain4 {

    private static final Pattern sound_pattern = Pattern.compile("[ ]*\\[sound:.*]");
    private static final Map<String, List<Map<String, String>>> map = new HashMap<>();

    public static void main(String args[]) {
        SQLite sql = new SQLite("C:/Users/david/Desktop/", "collection.anki2");
        Connection c;
        Statement statement;
        try {
            c = sql.openConnection();
            statement = c.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int count = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM notes");
            while (result.next()) {
                long row = result.getLong("id");
                String flds = result.getString("flds");
                String sfld = result.getString("sfld");
                String[] fields = flds.split("\u001F");

                if (fields.length > 2) {
                    String meaning = fields[2];
                    String jap = fields[0];
                    String kana = fields[1].replaceAll("[ ]*\\[sound:.*]", "").replaceAll("[&a-zA-Z;</>]*", "");
                    Matcher matcher = sound_pattern.matcher(fields[1]);
                    String sound = "";
                    if (matcher.find())
                        sound = matcher.group();
                    if (!kana.equals("")) {
                        if (meaning.matches("\\[.{0,5}[\\u52a8\\u52d5].*].*")) {
                            Map<String, String> entry = new HashMap<>();
                            entry.put("meaning", meaning);
                            entry.put("japanese", jap);
                            entry.put("sound", sound);
                            if (!map.containsKey(kana)) {
                                List<Map<String, String>> list = new ArrayList<>();
                                list.add(entry);
                                map.put(kana, list);
                            } else {
                                map.get(kana).add(entry);
                            }

                            count++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String kana : map.keySet()) {
            StringBuilder jap = new StringBuilder();
            StringBuilder meaning = new StringBuilder();
            String sound = "";
            for (Map<String, String> entry : map.get(kana)) {
                meaning.append(entry.get("meaning")).append(" ");
                jap.append(entry.get("japanese")).append(" ");
                sound = entry.get("sound");
            }
            String output = kana + "," + jap.substring(0, jap.length() - 1)
                    + "," + meaning.substring(0, meaning.length() - 1) + "," + sound;
            System.out.println(output);
        }
        System.out.println(count);
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
