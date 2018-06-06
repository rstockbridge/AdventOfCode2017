import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        final List<Integer> parsedInput = new ArrayList<>();
        for (String line : storedInput) {
            parsedInput.add(Integer.parseInt(line));
        }

        System.out.format("Part I: The number of steps taken is %d.\n", calculateNumberOfStepsPartI(parsedInput));
        System.out.format("Part II: The number of steps taken is %d.\n", calculateNumberOfStepsPartII(parsedInput));
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

    private static int calculateNumberOfStepsPartI(final List<Integer> data) {
        final List<Integer> instructions = new ArrayList<>(data);
        int i = 0;
        int result = 0;

        while (i >= 0 && i < instructions.size()) {
            final int instruction = instructions.get(i);

            instructions.set(i, instruction + 1);

            i += instruction;
            result++;
        }

        return result;
    }

    private static int calculateNumberOfStepsPartII(final List<Integer> inputData) {
        final List<Integer> instructions = new ArrayList<>(inputData);
        int i = 0;
        int result = 0;

        while (i >= 0 && i < instructions.size()) {
            final int instruction = instructions.get(i);

            if (instruction >= 3) {
                instructions.set(i, instruction - 1);
            } else {
                instructions.set(i, instruction + 1);
            }

            i += instruction;
            result++;
        }

        return result;
    }
}