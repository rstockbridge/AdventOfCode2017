import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day09/src/main/java/input" +
                        ".txt");

        final ParsingOutput output = new ParsingOutput(storedInput.get(0));

        System.out.format("Part I: The total score is %d.\n", calculateScore(output.getStream()));
        System.out.format("Part II: The number of non-canceled characters within the garbage is %d.\n", output.getNumberOfNonCanceledCharacters());
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

    private static int calculateScore(final String parsedStream) {
        int result = 0;
        int level = 1;

        int i = 0;
        while (i < parsedStream.length() - 1) {
            if (parsedStream.charAt(i) == '{' && parsedStream.charAt(i + 1) == '}') {
                result += level;
            } else if (parsedStream.charAt(i) == '{') {
                result += level;
                level++;
            } else if (parsedStream.charAt(i) == '}' && parsedStream.charAt(i + 1) == '}') {
                level--;
            }
            i++;
        }

        return result;
    }
}