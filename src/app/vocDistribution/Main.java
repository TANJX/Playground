package app.vocDistribution;

import java.util.Scanner;
import app.fileOperation.WriteFile;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input Students numbers");
		String[] stuList = new String[sc.nextInt()];
		System.out.println("Input Voc numbers");
		String[] vocList = new String[sc.nextInt()];

		for (int i = 0; i < stuList.length; i++) {
			stuList[i] = sc.next();
		}
		System.out.println("ok");
		for (int i = 0; i < vocList.length; i++) {
			vocList[i] = sc.next();
		}
		int count = 0;
		StringBuffer temp = new StringBuffer("Vocabulary,Synonym or explanation,Example\n");
		for (int i = 0; i < vocList.length; i++) {

			count++;
			if (count == 1) {
				System.out.println(stuList[i / 22]); // Need to be fixed
				temp = new StringBuffer("Vocabulary,Synonym or explanation,Example,,,Export By Java\n");
			}
			System.out.println(vocList[i]);
			temp.append(vocList[i] + "\n");
			if (count == 22 || i == vocList.length - 1) {
				System.out.println("");
				WriteFile.write("C:/Users/Mars/Desktop/results/", stuList[i / 22] + ".csv", temp.toString());
				count = 0;
			}
		}
		System.out.println("**All Done**");
	}
}
