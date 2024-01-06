package algorithms.insertion_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] array = {9, 1, 8, 2, 7, 3, 6, 4, 5};

        System.out.println(Arrays.toString(selectionSort(array)));

    }

    private static int[] selectionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }

}
