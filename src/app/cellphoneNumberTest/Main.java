package app.cellphoneNumberTest;

import java.util.Random;

/**
 * Created by Mars on 8/9/2016.
 * Copyright ISOTOPE Studio
 */
public class Main {

    private static final int[] prefix =
            {134, 135, 136, 137, 138, 139, 150, 151, 157, 158, 159, 187, 188,
                    130, 131, 132, 152, 155, 156, 185, 186,
                    133, 153, 180, 189};

    public static void main(String args[]) {
        long count = 0;
        //for (int i = 0; i < 50; i++) {
        for (; ; ) {
            count++;
            String result = getRandomCellphoneNumber();
            //System.out.println(result);
            if (result.startsWith("135 3800 3259")) {
                System.out.println(count);
                break;
            }
        }
    }

    private static String getRandomCellphoneNumber() {
        return prefix[(int) (Math.random() * prefix.length)]
                + " " + getFourDigitString() + " " + getFourDigitString();
    }

    private static String getFourDigitString() {
        String num = getRandom(0, 10000);
        if (num.length() == 1) {
            return "000" + num;
        }
        if (num.length() == 2) {
            return "00" + num;
        }
        if (num.length() == 3) {
            return "0" + num;
        }
        return num;
    }

    private static String getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }

}
