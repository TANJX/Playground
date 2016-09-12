package basic.date;

import java.util.Date;

/**
 * Created by Mars on 7/27/2016.
 * Copyright ISOTOPE Studio
 */
public class DateDemo {
    public static void main(String args[]) {
        System.out.println(new Date().getTime());
        System.out.println(new Date().getTime() - 1469611443583L);
        System.out.println((new Date().getTime() - 1469611443583L) / (1000 * 60 * 60 * 24));
        System.out.println(((new Date().getTime() + 2592000000L) - 1469611443583L) / (1000 * 60 * 60 * 24));
    }
}
