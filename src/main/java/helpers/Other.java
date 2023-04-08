package helpers;

import java.util.function.Supplier;

public class Other {
    public static long fibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void waitTillTrue(Supplier<Boolean> supplier, long retryInterval, long timeout) {
        long startTime = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        while ((time - startTime) < timeout && !supplier.get()) {
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
