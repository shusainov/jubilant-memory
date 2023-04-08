package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomFile {
    public static void writeTableValuesToFile(String path, List<Map<String, String>> table) {
        try {
            Files.write(Path.of(path), table.stream()
                            .map(row -> row.entrySet().stream().reduce("", (str, entry) -> str + "," + entry.getValue(), (x, y) -> x + y))
                            .map(str -> str.replaceFirst(",", ""))
                            .collect(Collectors.toList()),
                    new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.WRITE});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
