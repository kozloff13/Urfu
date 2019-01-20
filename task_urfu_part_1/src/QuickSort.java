/**
 * Быстрая сортировка
 */

public class QuickSort {

    public void quickSort(int[] array, int low, int high) {

        //счетчик для рекурсии
        int counter = 0;

        //выбор опорного элемента
        int middle = low + (high - low) / 2;
        //System.out.println("mid = " + middle);
        int pillar = array[middle];
        //System.out.println("pillar = " + pillar);

        int i = low;
        int j = high;

        while (i <= j) {
            while (array[i] < pillar) {
                i++;
                System.out.println("sort (" + pillar + ", " + i + "), recursion counter " + counter);
            }
            while (array[j] > pillar) {
                j--;
                System.out.println("sort (" + pillar + ", " + j + "), recursion counter " + counter);
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
            counter++;
            System.out.println("sort (" + pillar + ", " + low + "), recursion counter " + counter);
        }

        if (high > i) {
            quickSort(array, i, high);
            counter++;
            System.out.println("sort (" + pillar + ", " + high + "), recursion counter " + counter);
        }

    }
}