package app.seatArrangement;

class Random {

    public static int[] create(int number) {
        int[] list = new int[number + 1];
        boolean status = false;
        for (int i = 1; i < number; i++) {

            int temp = 0;
            while (!status) {
                status = true;
                temp = (int) (Math.random() * (number - 1)) + 1;
                for (int o : list)
                    if (o == temp)
                        status = false;
            }

            list[i] = temp;
            status = false;
            if (i == 1) {
                list[i + 1] = temp + 1;
                i++;
            }
        }
        return list;
    }

}
