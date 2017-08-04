package cc.isotopestudio.phonetest;
/*
 * Created by Mars Tan on 8/3/2017.
 * Copyright ISOTOPE Studio
 */

public abstract class PhoneNumUtil {

    private static final int[] PHONESEC = {3, 3, 3, 5};

    static String genRandomPhoneNum() {
        String phone = "1";
        phone += PHONESEC[(int) (Math.random() * PHONESEC.length)];
        phone += String.valueOf(Math.random()).substring(2, 11);
        return phone;
    }

    public static void main(String[] s){
        for (int i = 0; i < 10; i++) {
            System.out.println(genRandomPhoneNum());
        }
    }

}
