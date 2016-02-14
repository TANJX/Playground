package basicExercise.percentage;

//http://blog.csdn.net/snow0123/article/details/5424145

public class AllSort {
	public static int[][] result;
	private static int count;

	public static void main(String[] args) {
		int buf[] = { 1, 2, 3 };
		result = new int[getPossible(buf.length)][buf.length];
		perm(buf, 0, buf.length - 1);
		printList(result);
	}

	public static int[][] genList(int time) {
		int buf[] = createList(time);
		result = new int[getPossible(buf.length)][buf.length];
		count = 0;
		perm(buf, 0, buf.length - 1);
		//printList(result);
		return result;
	}

	private static int[] createList(int num) {
		int[] list = new int[num];
		for (int i = 0; i < num; i++) {
			list[i] = i;
		}
		return list;
	}

	public static void printList(int[][] list) {
		System.out.print(count + "\t");
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++)
				System.out.print(list[i][j] + " ");
			System.out.print("\t");
		}
		System.out.println("");
	}

	private static int getPossible(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++)
			result *= i;
		return result;
	}

	private static void perm(int[] buf, int start, int end) {
		if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可（特殊情况）
			for (int i = 0; i <= end; i++) {
				// System.out.print(buf[i]);
				result[count][i] = buf[i];
			}
			// System.out.println("\t" + count);
			count++;
		} else {// 多个字母全排列（普遍情况）
			for (int i = start; i <= end; i++) {// （让指针start分别指向每一个数）
				int temp = buf[start];// 交换数组第一个元素与后续的元素
				buf[start] = buf[i];
				buf[i] = temp;

				perm(buf, start + 1, end);// 后续元素递归全排列

				temp = buf[start];// 将交换后的数组还原
				buf[start] = buf[i];
				buf[i] = temp;
			}
		}
	}
}
