package basicExercise.percentage;

public class DistributionPercent {

	private static int getPossible(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++)
			result *= i;
		return result;
	}

	public static void main(String[] args) {
		System.out.println("Distribution Percent");

		int times = 12; 
		// Number of students
		
		for (int time = 1; time <= times; time++) {
			System.out.println("Number: " + time);
			int possible = getPossible(time);
			// System.out.println(possible);
			int hit = 0;
			int[][] sel = AllSort.genList(time);
			//AllSort.printList(sel);
			for (int pos = 0; pos < possible; pos++) {
				for (int loc = 0; loc < time; loc++) {
					if (sel[pos][loc] == loc) {
						hit++;
						break;
					}
				}
			}
			System.out.println(hit + " / " + possible);
		}
	}

}
