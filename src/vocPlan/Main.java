package vocPlan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	static Locale locale = new Locale("en", "US");
	static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);

	static boolean status1(int[] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i] != 5)
				return true;
		}
		return false;
	}

	static boolean status2(int[][] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i][1] != 5)
				return true;
		}
		return false;
	}

	public static boolean ifWeekend(int y, int m, int d, int day) throws ParseException {
		String date = y + "-" + m + "-" + (d + day - 1);
		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Type in the number of lists");
		final int listNum = sc.nextInt();

		System.out.println("Type in Mode");
		switch (sc.nextInt()) {
		// Everyday new list
		case (1): {
			System.out.println("\n\nHere is your voc plan:\n");
			int[] list = new int[listNum + 1];
			for (int i = 1; i <= listNum; i++) {
				list[i] = 0;
			}
			int day = 1;
			while (status1(list)) {
				System.out.print("Day" + day + ": ");
				if (day <= listNum) {
					System.out.print("(new)" + day + ", ");
				}
				for (int i = 1; i <= listNum; i++) {
					if (day - i == 1 || day - i == 2 || day - i == 4 || day - i == 7 || day - i == 14) {
						System.out.print(i + ", ");
						list[i]++;
					}
				}
				System.out.println();
				day++;
			}
			break;
		}

			// Weekends without new lists
		case (2): {
			int[][] list = new int[listNum + 1][2];//
			for (int i = 1; i <= listNum; i++) {
				list[i][1] = 0;
				list[i][0] = 0;
			}
			System.out.println("Type in the first day\nYYYY\nMM\nDD");
			final int y = sc.nextInt(), m = sc.nextInt(), d = sc.nextInt();

			System.out.println("\n\nHere is your voc plan:\n");
			Date bdate = format1.parse(y + "-" + m + "-" + d);
			Calendar cal = Calendar.getInstance();
			cal.setTime(bdate);

			int day = 1, listProgress = 0;
			while (status2(list)) {
				if ((cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
						&& cal.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
						&& cal.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)))
					System.out.print("**Today: ");
				else
					System.out.print("\t");
				System.out.print(fullDateFormat.format(cal.getTime()) + ": \t");
				if (!ifWeekend(y, m, d, day) && listProgress < listNum) {
					listProgress++;
					list[listProgress][0] = day;
					System.out.print("(new!)" + listProgress + ", ");
				}

				for (int i = 1; i <= listNum; i++) {
					if (list[i][0] != 0 && (day - list[i][0] == 1 || day - list[i][0] == 2 || day - list[i][0] == 4
							|| day - list[i][0] == 7 || day - list[i][0] == 14)) {
						System.out.print(i + ", ");
						list[i][1]++;
					}
				}
				System.out.println();
				cal.add(Calendar.DAY_OF_MONTH, 1);
				day++;
			}
			break;
		}
		default:
			System.out.println("Wrong Input!");
		}

	}
}
