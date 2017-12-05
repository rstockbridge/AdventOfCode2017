import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

final class Main {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/rebecca/Desktop/Dropbox/documents/coding/AdventOfCode2017/day04/src/main/java/input.txt"))) {
            final List<List<String>> storedInputFile = new ArrayList<>();

            for (String line; (line = bufferedReader.readLine()) != null; ) {
                storedInputFile.add(Arrays.stream(line.split(" ")).collect(Collectors.toList()));
            }

            System.out.format("Part I: the number of valid passphrases is %d.\n", calculateNumberOfValidPassPhrasesPartI(storedInputFile));
            System.out.format("Part II: the number of valid passphrases is %d.\n", calculateNumberOfValidPassPhrasesPartII(storedInputFile));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateNumberOfValidPassPhrasesPartI(final List<List<String>> storedInputFile) {
        int result = 0;

        for (final List<String> passphrase : storedInputFile) {
            final Set<String> reducedSet = new HashSet<>(passphrase);

            if (passphrase.size() == reducedSet.size()) {
                result++;
            }
        }

        return result;
    }

    private static int calculateNumberOfValidPassPhrasesPartII(final List<List<String>> storedInputFile) {
        int result = 0;

        for (final List<String> passphrase : storedInputFile) {
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