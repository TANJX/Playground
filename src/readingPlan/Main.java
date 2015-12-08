package readingPlan;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Input Number of pages");
		final int pages = sc.nextInt();
		System.out.println("Input Remaining days");
		final int days = sc.nextInt();
		System.out.println("Input finished pages");
		final int finished = sc.nextInt();
		double result = (pages - finished) / days;
		System.out.println((int) result + " page(s)/day");
		if (result % 1 != 0.0)
			System.out.println((pages - finished) % days + "pages left");

	}

}
