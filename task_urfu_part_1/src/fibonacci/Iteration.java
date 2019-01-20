package fibonacci;

/**
 * С помощью массива, в итерационном виде
 */

public class Iteration {

    int cache[] = new int[100];

    public int fibo(int n) {
        cache[0] = 1;
        cache[1] = 1;

        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n - 1];
    }
}
