package app.japaneseOperation;
/*
 * Created by david on 2/9/2018.
 * Copyright ISOTOPE Studio
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReviewPlanner {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            list.add(i + 1);
        }
        Collections.shuffle(list);
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 24; i++) {
            System.out.print(format.format(date.getTime())+",");
            System.out.print(list.get(i * 2) + ",");
            System.out.print(list.get(i * 2 + 1));
            System.out.println();
            date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
        }
    }

}
