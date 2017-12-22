import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day06/src/main/java/input" +
                        ".txt");

        final List<Integer> parsedInput = Arrays.stream(storedInput.get(0).split("\t")).map(Integer::valueOf)
                .collect(Collectors.toList());

        Output output = solvePuzzle(parsedInput);

        System.out.format("Part I: The number of redistribution cycles is %d.\n", output.getNumberOfRedistributionCycles
                ());
        System.out.format("Part II: The number of cycles is %d.\n", output.getNumberOfCycles());
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

    private static Output solvePuzzle(List<Integer> data) {
        final List<String> statesObserved = new ArrayList<>();
        int numberOfRedistributionCycles = 0;
        boolean keepGoing = true;
        String stateAsString;

        do {
            final int maxIndex = data.indexOf(Collections.max(data));
            final int numberOfBlocks = data.get(maxIndex);

            data.set(maxIndex, 0);

            for (int i = 0; i < numberOfBlocks; i++) {
                data.set((maxIndex + 1 + i) % data.size(), data.get((maxIndex + 1 + i) % data
                        .size()) + 1);
            }

            stateAsString = data.stream().map(Object::toString).collect(Collectors.joining(""));

            if (statesObserved.contains(stateAsString)) {
                keepGoing = false;
            } else {
                statesObserved.add(stateAsString);
                numberOfRedistributionCycles++;
            }
        } while (keepGoing);

        return new Output(numberOfRedistributionCycles, numberOfRedistributionCycles - statesObserved
                .indexOf(stateAsString));
    }
}