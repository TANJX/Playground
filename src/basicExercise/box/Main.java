package basicExercise.box;

import java.util.ArrayList;

public class Main {

	static int[] xy;

	static boolean outOfBound(int step) {
		if (!(xy[0] >= 1 && xy[0] <= 3 && xy[1] >= 1 && xy[1] <= 3))
			return true;

		switch (step) {
		case (8): {
			if (xy[0] == 2 && xy[1] == 3)
				return true;
		}
		case (7): {
			if (xy[0] == 2 && xy[1] == 2)
				return true;
		}
		case (6): {
			if (xy[0] == 3 && xy[1] == 2)
				return true;
		}
		case (5): {
			if (xy[0] == 1 && xy[1] == 3)
				return true;
		}
		case (4): {
			if (xy[0] == 2 && xy[1] == 1)
				return true;
		}
		case (3): {
			if (xy[0] == 1 && xy[1] == 1)
				return true;
		}
		case (2): {
			if (xy[0] == 1 && xy[1] == 2)
				return true;
		}
		case (1): {
			if (xy[0] == 3 && xy[1] == 1)
				return true;
		}
		}
		return false;
	}

	static boolean move(int orient, int step) {
		// 0Up 1Down 2Left 3Right

		if ((xy[0] >= 1 && xy[0] <= 3) && (xy[1] >= 1 && xy[1] <= 3)) {
			switch (orient) {
			case (0): {
				xy[1]--;
				if (outOfBound(step)) {
					xy[1]++;
					return false;
				} else
					return true;
			}
			case (1): {
				xy[1]++;
				if (outOfBound(step)) {
					xy[1]--;
					return false;
				} else
					return true;
			}
			case (2): {
				xy[0]--;
				if (outOfBound(step)) {
					xy[0]++;
					return false;
				} else
					return true;
			}
			case (3): {
				xy[0]++;
				if (outOfBound(step)) {
					xy[0]--;
					return false;
				} else
					return true;
			}
			}
		}
		return false;
	}

	static String getLocation() {
		return "X: " + xy[0] + ", Y: " + xy[1];
	}

	static ArrayList<String> results = new ArrayList<String>();

	static ArrayList<String> step1(int steps[]) {
		if (move(steps[0], 1))
			if (move(steps[1], 1))
				if (move(steps[2], 1))
					if (move(steps[3], 1))
						if (move(steps[4], 1))
							if (move(steps[5], 1))
								if (move(steps[6], 1)) {
									loop: {
										// System.out.println(true);
										for (int i = 0; i < results.size(); i++) {
											if (results.get(i).equals(getLocation())) {
												// System.out.println("Out");
												break loop;
											}
										}
										results.add(getLocation());
									}
								}
		return results;
	}

	static ArrayList<String> step2(int steps[]) {
		if (move(steps[0], 2))
			if (move(steps[1], 2))
				if (move(steps[2], 2)) {
					loop: {
						for (int i = 0; i < results.size(); i++) {
							if (results.get(i).equals(getLocation())) {
								// System.out.println("Out");
								break loop;
							}
						}
						results.add(getLocation());
					}
				}
		return results;
	}

	static ArrayList<String> step3(int steps[]) {
		if (move(steps[0], 3))
			if (move(steps[1], 3))
				if (move(steps[2], 3))
					if (move(steps[3], 3))
						if (move(steps[4], 3))
							if (move(steps[5], 3))
								if (move(steps[6], 3)) {
									loop: {
										for (int i = 0; i < results.size(); i++) {
											if (results.get(i).equals(getLocation())) {
												break loop;
											}
										}
										results.add(getLocation());
									}
								}
		return results;
	}

	static ArrayList<String> step4(int steps[]) {
		if (move(steps[0], 4))
			if (move(steps[1], 4))
				if (move(steps[2], 4))
					if (move(steps[3], 4))
						if (move(steps[4], 4)) {
							loop: {
								for (int i = 0; i < results.size(); i++) {
									if (results.get(i).equals(getLocation())) {
										break loop;
									}
								}
								results.add(getLocation());
							}
						}
		return results;
	}

	static ArrayList<String> step5(int steps[]) {
		if (move(steps[0], 5))
			if (move(steps[1], 5))
				if (move(steps[2], 5))
					if (move(steps[3], 5))
						if (move(steps[4], 5))
							if (move(steps[5], 5))
								if (move(steps[6], 5))
									if (move(steps[7], 5))
										if (move(steps[8], 5)) {
											loop: {
												for (int i = 0; i < results.size(); i++) {
													if (results.get(i).equals(getLocation())) {
														break loop;
													}
												}
												results.add(getLocation());
											}
										}
		return results;
	}

	static ArrayList<String> step6(int steps[]) {
		if (move(steps[0], 6))
			if (move(steps[1], 6))
				if (move(steps[2], 6)) {
					loop: {
						for (int i = 0; i < results.size(); i++) {
							if (results.get(i).equals(getLocation())) {
								break loop;
							}
						}
						results.add(getLocation());
					}
				}
		return results;
	}

	static ArrayList<String> step7(int steps[]) {
		if (move(steps[0], 7)) {
			loop: {
				for (int i = 0; i < results.size(); i++) {
					if (results.get(i).equals(getLocation())) {
						break loop;
					}
				}
				results.add(getLocation());
			}
		}
		return results;
	}

	static void printArray(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		list.clear();
	}

	public static void main(String[] args) {
		xy = new int[2];

		System.out.println("Step 1");
		System.out.println("Start from 3,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++) {
									xy[0] = 3;
									xy[1] = 2;
									step1(new int[] { a1, a2, a3, a4, a5, a6, a7 });
								}
		printArray(results);

		System.out.println("Start from 2,1");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++) {
									xy[0] = 2; // x
									xy[1] = 1;
									step1(new int[] { a1, a2, a3, a4, a5, a6, a7 });
								}
		printArray(results);

		System.out.println("Step 2");
		System.out.println("Start from 3,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 3;
					xy[1] = 3;
					step2(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Start from 2,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 2;
					xy[1] = 2;
					step2(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Start from 1,1");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 1;
					xy[1] = 1;
					step2(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Start from 1,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 1;
					xy[1] = 3;
					step2(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Step 3");
		System.out.println("Start from 3,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++) {
									xy[0] = 3;
									xy[1] = 2;
									step3(new int[] { a1, a2, a3, a4, a5, a6, a7 });
								}
		printArray(results);
		System.out.println("Start from 2,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++) {
									xy[0] = 2;
									xy[1] = 3;
									step3(new int[] { a1, a2, a3, a4, a5, a6, a7 });
								}
		printArray(results);
		System.out.println("Start from 2,1");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++) {
									xy[0] = 2;
									xy[1] = 1;
									step3(new int[] { a1, a2, a3, a4, a5, a6, a7 });
								}
		printArray(results);

		System.out.println("Step 4");
		System.out.println("Start from 3,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++) {
							xy[0] = 3;
							xy[1] = 3;
							step4(new int[] { a1, a2, a3, a4, a5 });
						}
		printArray(results);

		System.out.println("Start from 2,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++) {
							xy[0] = 2;
							xy[1] = 2;
							step4(new int[] { a1, a2, a3, a4, a5 });
						}
		printArray(results);

		System.out.println("Start from 1,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++) {
							xy[0] = 1;
							xy[1] = 3;
							step4(new int[] { a1, a2, a3, a4, a5 });
						}
		printArray(results);

		System.out.println("Step 5");
		System.out.println("Start from 3,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++)
									for (int a8 = 0; a8 < 4; a8++)
										for (int a9 = 0; a9 < 4; a9++) {
											xy[0] = 3;
											xy[1] = 2;
											step5(new int[] { a1, a2, a3, a4, a5, a6, a7, a8, a9 });
										}
		printArray(results);

		System.out.println("Start from 2,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++)
					for (int a4 = 0; a4 < 4; a4++)
						for (int a5 = 0; a5 < 4; a5++)
							for (int a6 = 0; a6 < 4; a6++)
								for (int a7 = 0; a7 < 4; a7++)
									for (int a8 = 0; a8 < 4; a8++)
										for (int a9 = 0; a9 < 4; a9++) {
											xy[0] = 2;
											xy[1] = 3;
											step5(new int[] { a1, a2, a3, a4, a5, a6, a7, a8, a9 });
										}
		printArray(results);

		System.out.println("Step 6");
		System.out.println("Start from 3,3");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 3;
					xy[1] = 3;
					step6(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Start from 2,2");
		for (int a1 = 0; a1 < 4; a1++)
			for (int a2 = 0; a2 < 4; a2++)
				for (int a3 = 0; a3 < 4; a3++) {
					xy[0] = 2;
					xy[1] = 2;
					step6(new int[] { a1, a2, a3 });
				}
		printArray(results);

		System.out.println("Step 7");
		System.out.println("Start from 2,3");
		for (int a1 = 0; a1 < 4; a1++) {
			xy[0] = 2;
			xy[1] = 3;
			step7(new int[] { a1 });
		}
		printArray(results);

	}

}
