/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.sorting;

/**
 *
 * @author Mars
 */
public class Main {

    public static int[] SelectionSort(int[] list) {
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
        return list;
    }

    public static int[] InsertionSort(int[] list) {
        loop:
        {
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
        return list;
    }

    public int[] MergeSort(int[] list, int s,int length) {
        
        return list;
    }

    public static void MergeArray(int[] a, int A, int[] b, int B, int[] ab) {
        int i = 0, j = 0, k = 0; // Point of a, b, c

        while (i < A && j < B) {
            if (a[i] < b[j]) {
                ab[k++] = a[i++];
            } else {
                ab[k++] = b[j++];
            }
        }

        while (i < A) {
            ab[k++] = a[i++];
        }

        while (j < B) {
            ab[k++] = b[j++];
        }

    }

    public static void main(String agrs[]) {
        /*
         * int list[] = {1, 5, 7, 0, 2, 54, -100, 4095, 0, 45, 7, -8789, 897,
         * 789, -789, -7890, 864, 57, -7670, -9, 878, -97, -7, 4}; for (int o :
         * list) { System.out.print(o + "\t"); } System.out.println(); list =
         * InsertionSort(list); for (int o : list) { System.out.print(o + "\t");
         * }
         */

        int list1[] = {1, 3, 5, 7, 11}, list2[] = {-5, 2, 2, 5, 7};
        int list12[] = new int[10];
        MergeArray(list1, 5, list2, 5, list12);
        for (int o : list12) {
            System.out.print(o + "\t");
        }
    }
}
