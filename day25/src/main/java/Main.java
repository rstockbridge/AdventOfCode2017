import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day25/src/main/java/input" +
                        ".txt");

        String[] splitLine0 = parseLine(storedInput.get(0));
        String[] splitLine1 = parseLine(storedInput.get(1));

        final char initialStateName = splitLine0[splitLine0.length - 1].charAt(0);
        final int numberOfSteps = Integer.parseInt(splitLine1[splitLine1.length - 2]);

        final TuringMachine machine = new TuringMachine(createStates(storedInput), initialStateName);

        for (int i = 0; i < numberOfSteps; i++) {
            machine.runStep();
        }

        System.out.format("Part I: The diagnostic checksum is %d.\n", machine.calculateChecksum());
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

    private static List<State> createStates(final List<String> storedInput) {
        final List<State> result = new ArrayList<>();

        for (int i = 3; i < storedInput.size(); i++) {
            String[] splitLine = parseLine(storedInput.get(i));
            final char name = splitLine[splitLine.length - 1].charAt(0);
            i += 2;

            splitLine = parseLine(storedInput.get(i));
            final int valueToMoveIf0 = Integer.parseInt(splitLine[splitLine.length - 1]);
            i++;

            splitLine = parseLine(storedInput.get(i));
            final String directionToMoveIf0 = splitLine[splitLine.length - 1];
            i++;

            splitLine = parseLine(storedInput.get(i));
            final char newStateIf0 = splitLine[splitLine.length - 1].charAt(0);
            i += 2;

            splitLine = parseLine(storedInput.get(i));
            final int valueToMoveIf1 = Integer.parseInt(splitLine[splitLine.length - 1]);
            i++;

            splitLine = parseLine(storedInput.get(i));
            final String directionToMoveIf1 = splitLine[splitLine.length - 1];
            i++;

            splitLine = parseLine(storedInput.get(i));
            final char newStateIf1 = splitLine[splitLine.length - 1].charAt(0);
            i++;

            result.add(new State(name, valueToMoveIf0, directionToMoveIf0, newStateIf0, valueToMoveIf1, directionToMoveIf1, newStateIf1));
        }

        return result;
    }

    private static String[] parseLine(final String line) {
        return line.replaceAll("[^a-zA-Z0-9 ]", "").split(" ");
    }
}