import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        final String parsedInput = storedInput.get(0);

        System.out.format("Part I: the solution is %d.\n", solveCaptcha(parsedInput, 1));
        System.out.format("Part II: the solution to is %d.\n", solveCaptcha(parsedInput, parsedInput.length() / 2));
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

    private static int solveCaptcha(final String data, final int offset) {
        int sum = 0;

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == data.charAt((i + offset) % data.length())) {
                sum += Character.getNumericValue(data.charAt(i));
            }
        }

        return sum;
    }
}