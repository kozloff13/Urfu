import fibonacci.ArrayCache;
import fibonacci.Iteration;
import fibonacci.Recursion;

public class Main {

    public static void main(String[] args) {

        //вычисление числа Фибоначчи
        Recursion recursion = new Recursion();
        System.out.println(recursion.fibo(7));

        ArrayCache arrayCache = new ArrayCache();
        System.out.println(arrayCache.fibo(15));

        Iteration iteration = new Iteration();
        System.out.println(iteration.fibo(46));

        System.out.println();

        //быстрая сортировка
        int[] unsorted = {4, 2, 8, 1, 3, 7, 6, 5};
        for (int i = 0; i < unsorted.length; i++) {
            System.out.print(unsorted[i] + " ");
        }

        System.out.println();

        QuickSort quickSort = new QuickSort();
        int low = 0;
        int high = unsorted.length - 1;
        quickSort.quickSort(unsorted, low, high);
        for (int i = 0; i < unsorted.length; i++) {
            System.out.print(unsorted[i] + " ");
        }

    }
}