package basicExercise.cubic;

public class Main {

	static double increase(double n) {
		if (n > 0)
			return n * -1;
		else
			return (n - 1) * -1;
	}

	static boolean ifExist(int n) {
		int[] list = { 4, 5, 13, 14, 22, 23, 31, 32 };
		for (int o : list) {
			if (n == o)
				return false;
		}
		return true;
	}

	public static void cubic(int n) {
		if (ifExist(n) == true) {
			final double limit = 200;
			double a = 1, b = 1, c = 1;
			do
				loop: {
					while (a <= limit) {
						b = 1;
						while (b <= limit) {
							c = 1;
							while (c <= limit) {
								// System.out.println("\t\t" + a + "^3" + " + "
								// + b
								// + "^3" + " + " + c + "^3");
								if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == n) {
									System.out.println(n + ": " + (int) a + "^3" + " + " + (int) b + "^3" + " + "
											+ (int) c + "^3");
									break loop;
								}
								c = increase(c);
							}
							b = increase(b);
						}
						a = increase(a);
					}
					System.out.println(n + ": Can't find results");
					break;
				}
			while (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) != n);
		} else
			System.out.println(n + ": Non-existen");
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 50; i++) {
			cubic(i);
		}
	}

}
