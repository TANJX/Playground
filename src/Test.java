
/*
 * Created by david on 2018/09/13.
 * Copyright ISOTOPE Studio
 */

public class Test {
    private static final String[] NUM = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    public static void main(String[] s) {
        for (int i = 1; i <= 12; i++) {
            System.out.println("- [Chapter "+ i +" | 第" + NUM[i - 1] + "单元](http://notes.marstanjx.com/n5/chapter/"+ i +")");
        }


    }

}
