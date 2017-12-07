import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("/Users/rebecca/Desktop/Dropbox/documents/coding/AdventOfCode2017/day04/src/main/java/input.txt");

        final List<List<String>> parsedInput = new ArrayList<>();
        for (String line : storedInput) {
            parsedInput.add(Arrays.stream(line.split(" ")).collect(Collectors.toList()));
        }

        System.out.format("Part I: the number of valid passphrases is %d.\n", calculateNumberOfValidPassPhrasesPartI(parsedInput));
        System.out.format("Part II: the number of valid passphrases is %d.\n", calculateNumberOfValidPassPhrasesPartII(parsedInput));
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

    private static int calculateNumberOfValidPassPhrasesPartI(final List<List<String>> data) {
        int result = 0;

        for (final List<String> passphrase : data) {
            final Set<String> reducedSet = new HashSet<>(passphrase);

            if (passphrase.size() == reducedSet.size()) {
                result++;
            }
        }

        return result;
    }

    private static int calculateNumberOfValidPassPhrasesPartII(final List<List<String>> inputData) {
        int result = 0;

        for (final List<String> passphrase : inputData) {
            final Set<String> reducedSet = new HashSet<>();

            for (final String word : passphrase) {
                reducedSet.add(sortString(word));
            }

            if (passphrase.size() == reducedSet.size()) {
                result++;
            }
        }

        return result;
    }

    private static String sortString(String inputString) {
        final char[] stringAsChars = inputString.toCharArray();
        Arrays.sort(stringAsChars);
        return new String(stringAsChars);
    }
}