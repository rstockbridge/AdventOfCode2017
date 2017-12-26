import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day11/src/main/java/input" +
                        ".txt");

        String[] parsedInput = storedInput.get(0).split(",");
        Output output = solvePuzzle(parsedInput);

        System.out.format("Part I: The fewest number of steps away is %d.\n", output.getNumberOfSteps());
        System.out.format("Part II: The furthest number of steps every away is %d.\n", output.getMaxNumberOfSteps());
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

    private static int calculateNumberOfSteps(final int x, final int y) {
        return Math.max(Math.max(Math.abs(x), Math.abs(y)), Math.abs(x - y));
    }

    private static Output solvePuzzle(final String[] directions) {
        int hexX = 0;
        int hexY = 0;
        int numberOfSteps = 0;
        int maxNumberOfSteps = 0;

        for (String direction : directions) {
            switch (direction) {
                case "n":
                    hexY++;
                    break;
                case "ne":
                    hexX++;
                    hexY++;
                    break;
                case "se":
                    hexX++;
                    break;
                case "s":
                    hexY--;
                    break;
                case "sw":
                    hexX--;
                    hexY--;
                    break;
                case "nw":
                    hexX--;
                    break;
            }
            numberOfSteps = calculateNumberOfSteps(hexX, hexY);
            if (numberOfSteps > maxNumberOfSteps) {
                maxNumberOfSteps = numberOfSteps;
            }
        }

        return new Output(numberOfSteps, maxNumberOfSteps);
    }
}