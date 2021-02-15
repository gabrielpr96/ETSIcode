package testutils.algoritmos;

public class Mergesort {

    private static int[] numbers;
    private static int[] helper;

    private static int number;

    public static void ordena(int[] values) {
        numbers = values;
        number = values.length;
        helper = new int[number];
        mergesort(0, number - 1, 0);
    }

    private static void mergesort(int low, int high, int level) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle, level+1);
            // Sort the right side of the array
            mergesort(middle + 1, high, level+1);
            // Combine them both
            System.out.println("Nivel: "+level);
            merge(low, middle, high);
        }
    }

    private static void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        System.out.print("[");
        for (int i = low; i <= middle; i++) {
            System.out.print(helper[i] + ", ");
        }
        System.out.print("] + [");
        for (int i = middle + 1; i <= high; i++) {
            System.out.print(helper[i] + ", ");
        }
        System.out.print("] = [");

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                System.out.print(helper[i] + ", ");
                i++;
            } else {
                numbers[k] = helper[j];
                System.out.print(helper[i] + ", ");
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            System.out.print(helper[i] + ", ");
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.

        System.out.println("]");

    }
}
