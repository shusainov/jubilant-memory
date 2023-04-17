package helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
                time = System.currentTimeMillis();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Map<String, String>> convertDatesOnColumn(List<Map<String, String>> table, String columnName, String oldDateFormat, String newDateFormat, Locale locale) {
        return table.stream()
                .map(m -> {
                            Map<String, String> result = new HashMap<>(m);
                            result.put(columnName,
                                    CustomDate.convertDate(m.get("Date-Time"), oldDateFormat, locale,
                                            newDateFormat));
                            return result;
                        }
                ).collect(Collectors.toList());
    }
}
