package app.seatArrangement;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
    /*	Scanner sc = new Scanner(System.in);
		while()
		sc.next();
		*/
        String address = "C:/Onedrive/Academics/Grade 11.2/Class/";
        String fileName = "list.txt";
        FileOperation list = new FileOperation(address, fileName);
        list.readFile();
        int studentNum = list.getTotalLines();
        System.out.println("人数:\t" + studentNum);
        Student studentList[] = new Student[studentNum + 1];

        for (int i = 1; i <= studentNum; i++) {
            String name = list.getName(i);
            studentList[i] = new Student(name);
        }

        System.out.println("读取完毕");
        System.out.println();

        System.out.println("生成随机数...");
        int[] randomList = Random.create(studentNum + 1);

        System.out.println("生成完毕");
        System.out.println();
        System.out.println("--结果--");
        System.out.println();

        FileOperation result = new FileOperation(address, "results.csv");
        StringBuilder resultLine = new StringBuilder();
        for (int i = 1; i <= studentNum; i++) {
            studentList[i].setSeat(randomList[i]);
            resultLine.append(studentList[i]).append("\r\n");
            System.out.println(studentList[i]);
        }

        result.write(resultLine.toString());


    }

}
