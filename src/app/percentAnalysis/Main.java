/**
 * 
 */
package app.percentAnalysis;

import java.util.Scanner;

/**
 * @author Mars
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int temp;
		int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;
		sc.next();
		for (int i = 0; i < 273; i++) {
			temp = sc.nextInt();
			switch (temp) {
			case (1): {
				c1++;
				break;
			}
			case (2): {
				c2++;
				break;
			}
			case (3): {
				c3++;
				break;
			}
			case (4): {
				c4++;
				break;
			}
			case (5): {
				c5++;
				break;
			}
			}
		}
		System.out.print(c1 / 273.0);
		System.out.println("%");
		System.out.print(c2 / 273.0);
		System.out.println("%");
		System.out.print(c3 / 273.0);
		System.out.println("%");
		System.out.print(c4 / 273.0);
		System.out.println("%");
		System.out.print(c5 / 273.0);
		System.out.println("%");
	}

}
