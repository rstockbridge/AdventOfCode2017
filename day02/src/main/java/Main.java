import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        final List<List<Integer>> parsedInput = new ArrayList<>();
        for (String line : storedInput) {
            parsedInput.add(Arrays.stream(line.split("\t")).map(Integer::valueOf).collect(Collectors.toList()));
        }

        System.out.format("Part I: the checksum is %d.\n", calculatePartIChecksum(parsedInput));
        System.out.format("Part II: the checksum is %d.\n", calculatePartIIChecksum(parsedInput));
    }

    private static List<String> readInputFile(final String inputFilePath) throws IOException {
        final List<String> result = new ArrayList<>();

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                result.add(line);
            }
        }

        return result;
    }

    private static int calculatePartIChecksum(final List<List<Integer>> data) {
        int checksum = 0;

        for (final List<Integer> lineValues : data) {
            checksum += Collections.max(lineValues) - Collections.min(lineValues);
        }

        return checksum;
    }

    private static int calculatePartIIChecksum(final List<List<Integer>> inputData) {
        int checksum = 0;

        for (final List<Integer> values : inputData) {
            for (int i = 0; i < values.size() - 1; i++) {
                for (int j = i + 1; j < values.size(); j++) {
                    if (values.get(i) % values.get(j) == 0) {
                        checksum += values.get(i) / values.get(j);
                    } else if (values.get(j) % values.get(i) == 0) {
                        checksum += values.get(j) / values.get(i);
                    }
                }
            }
        }

        return checksum;
    }
}