import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day13/src/main/java/input" +
                        ".txt");

        final Map<Integer, Integer> parsedInput = parseInput(storedInput);

        System.out.format("Part I: %s.\n", runFirewall(parsedInput, 0).getSeverity());
        System.out.format("Part II: %d.\n", calculateMinDelay(parsedInput));
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

    private static Map<Integer, Integer> parseInput(final List<String> storedInput) {
        final Map<Integer, Integer> result = new HashMap<>();
        for (final String line : storedInput) {
            final String[] splitLine = line.split(": ");
            result.put(Integer.valueOf(splitLine[0]), Integer.valueOf(splitLine[1]));
        }

        return result;
    }

    private static Output runFirewall(final Map<Integer, Integer> parsedInput, final int delay) {
        int severity = 0;
        boolean wasCaught = false;

        for (final Integer depth : parsedInput.keySet()) {
            final int range = parsedInput.get(depth);

            if ((depth + delay) % (2 * (range - 1)) == 0) {
                wasCaught = true;
                severity += depth * range;
            }
        }

        return new Output(severity, wasCaught);
    }

    private static int calculateMinDelay(final Map<Integer, Integer> parsedInput) {
        int result = 0;
        boolean wasCaught;

        do {
            result++;
            wasCaught = runFirewall(parsedInput, result).getWasCaught();
        } while (wasCaught);

        return result;
    }
}