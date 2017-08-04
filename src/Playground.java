/*
 * Created by Mars Tan on 12/20/2016.
 * Copyright ISOTOPE Studio
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playground {

    public static void main(String[] args) {
        String PATTERN = "\\d+";
        Pattern r = Pattern.compile(PATTERN);
        String line = "≤‚ ‘π…£∫123 «";
        Matcher m = r.matcher(line);
        System.out.println(m.find());
        System.out.println(m.group(0));
    }

}
