package algorithms.selection_sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] array = {9, 1, 8, 2, 7, 3, 6, 4, 5};

        System.out.println(Arrays.toString(selectionSort(array)));

    }

    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[min] > array[j])
                    min = j;
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        return array;
    }

}
