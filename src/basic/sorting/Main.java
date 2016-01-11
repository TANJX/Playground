
package basic.sorting;

import java.util.Random;

/**
 *
 * @author Mars
 */
public class Main {

	public static void SelectionSort(int[] list) {
		int max = 0;
		for (int i = 0; i < list.length; i++) {
			max = i;
			for (int o = i + 1; o < list.length; o++) {
				if (list[o] < list[max]) {
					max = o;
				}
			}
			int temp = list[max];
			list[max] = list[i];
			list[i] = temp;
		}
	}

	public static void InsertionSort(int[] list) {
		loop: {
			int o = 0;
			for (int i = 0; i < list.length; i++) {
				while (o < list.length - 1 && list[o] < list[o + 1]) {
					o++;
				}
				if (o != list.length - 1) {
					int min = o + 1;
					while (min > 0 && list[min] < list[min - 1]) {
						int temp = list[min - 1];
						list[min - 1] = list[min];
						list[min] = temp;
						min--;
					}
				} else {
					break loop;
				}
			}
		}
	}

	public static void MergeSort(int[] list) {
		MergeSort(list, 0, list.length - 1, new int[list.length]);
	}

	public static void MergeSort(int[] list, int start, int end, int[] temp) {
		if (start < end) {
			int mid = (start + end) / 2;
			MergeSort(list, start, mid, temp);
			MergeSort(list, mid + 1, end, temp);
			MergeArray(list, start, mid, end, temp);
		}
	}

	public static void MergeArray(int[] a, int start, int mid, int end, int[] ab) {
		int i = start, j = mid + 1, k = 0; // Points of a

		while (i <= mid && j <= end) {
			if (a[i] < a[j]) {
				ab[k++] = a[i++];
			} else {
				ab[k++] = a[j++];
			}
		}

		while (i <= mid) {
			ab[k++] = a[i++];
		}
		while (j <= end) {
			ab[k++] = a[j++];
		}

		for (i = 0; i < k; i++) {
			a[start + i] = ab[i];
		}

	}

	public static void main(String agrs[]) {

		Random g = new Random();
		int length = 10000;
		int list[] = new int[length];
		for (int i = 0; i < length; i++) {
			list[i] = g.nextInt(100000);
			System.out.print(list[i] + "\t");
		}
		System.out.println();
		// InsertionSort(list);
		// SelectionSort(list);
		MergeSort(list);
		for (int o : list) {
			System.out.print(o + "\t");
		}
		System.out.println();

	}
}
