/*
 * Created by Mars Tan on 12/20/2016.
 * Copyright ISOTOPE Studio
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Playground {

    public static void addUser(String username, String pw, String mail) {
        try {
            readUrlContent("http://tech.isotopestudio.cc/sql/addUser.php?username=" + username + "&pw=" + pw + "&mail=" + mail, 4000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addScore(String username, String year, String month, String day,
                                String mark, String math, String english, String reading, String science, String essay, String composite) {
        try {
            readUrlContent("http://tech.isotopestudio.cc/sql/addUser.php?" +
                    "username=" + username + "&year=" + year + "&month=" + month + "&day=" + day +
                    "&mark=" + mark + "&math=" + math + "&english=" + english + "&reading=" + reading + "&science=" + science + "&essay=" + essay + "&composite=" + composite, 4000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Score> getScoreByUser(String username) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            String data = readUrlContent("http://tech.isotopestudio.cc/sql/getScoreByUser.php?username=" + username, 4000);
            String[] scorelines = data.split(" ;");
            for (String score : scorelines) {
                String[] info = score.split(" ");
                scores.add(new Score(Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4], Integer.parseInt(info[5]), Integer.parseInt(info[6]), Integer.parseInt(info[7]), Integer.parseInt(info[8]), Integer.parseInt(info[9]), Integer.parseInt(info[10])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static boolean identification(String username, String pw) {
        try {
            String data = readUrlContent("http://tech.isotopestudio.cc/sql/getUser.php", 4000);
            String[] users = data.split(" ;");
            for (String user : users) {
                String[] info = user.split(" ");
                if (username.equals(info[0])) {
                    return pw.equals(info[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Can't find user");
        return false;
    }

    public static void main(String[] s) {
        System.out.print(identification("1234", "1234"));
    }

    private static String readUrlContent(String address, int timeout) throws IOException {
        StringBuilder contents = new StringBuilder(2048);
        BufferedReader br = null;

        try {
            URL url = new URL(address);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(timeout);
            huc.setReadTimeout(timeout);
            huc.setRequestMethod("GET");
            huc.connect();
            br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                contents.append(line);
            }
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contents.toString();
    }

    public static class Score {
        private final int year;
        private final int month;
        private final int day;
        private final String mark;
        private final int math;
        private final int english;
        private final int reading;
        private final int science;
        private final int essay;
        private final int composite;

        public Score(int year, int month, int day, String mark, int math, int english, int reading, int science, int essay, int composite) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.mark = mark;
            this.math = math;
            this.english = english;
            this.reading = reading;
            this.science = science;
            this.essay = essay;
            this.composite = composite;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public String getMark() {
            return mark;
        }

        public int getMath() {
            return math;
        }

        public int getEnglish() {
            return english;
        }

        public int getReading() {
            return reading;
        }

        public int getScience() {
            return science;
        }

        public int getEssay() {
            return essay;
        }

        public int getComposite() {
            return composite;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Score{");
            sb.append("year=").append(year);
            sb.append(", month=").append(month);
            sb.append(", day=").append(day);
            sb.append(", mark=").append(mark);
            sb.append(", math=").append(math);
            sb.append(", english=").append(english);
            sb.append(", reading=").append(reading);
            sb.append(", science=").append(science);
            sb.append(", essay=").append(essay);
            sb.append(", composite=").append(composite);
            sb.append('}');
            return sb.toString();
        }
    }
}
