import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        System.out.format("Part I: The value of the recovered frequency is %d.\n", new ProgramPartI(storedInput).solvePartI());
        System.out.format("Part II: The number of times Program 1 sends a value is %d.\n", solvePuzzlePartII(storedInput));
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

    private static int solvePuzzlePartII(final List<String> storedInput) {
        int numberOfInstructions = storedInput.size();

        ProgramPartII program0 = new ProgramPartII(storedInput);
        ProgramPartII program1 = new ProgramPartII(storedInput);

        program0.addToRegisters("p", 0);
        program1.addToRegisters("p", 1);

        while (program0.getIndex() >= 0 && program0.getIndex() < numberOfInstructions && program1.getIndex() >= 0 &&
                program1.getIndex() < numberOfInstructions && !(program0.isWaiting() && program1.isWaiting())) {
            program0.processInstruction(program1);
            program1.processInstruction(program0);
        }

        return program1.getSendCount();
    }
}