package fibonacci;

/**
 * С помощьюю массива с "кэшем".
 * Перед вычислением нового значения мы будем проверять, не вычисляли ли его раньше.
 */

public class ArrayCache {

    int cache[] = new int[100];

    public int fibo(int n) {
        if (cache[n] == 0) {
            if (n == 1 || n == 2) {
                cache[n] = 1;
            } else {
                cache[n] = fibo(n - 1) + fibo(n - 2);
            }
        }
        return cache[n];
    }
}
