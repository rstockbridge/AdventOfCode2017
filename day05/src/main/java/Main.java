import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Main {

    public static void main(String[] args) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/rebecca/Desktop/Dropbox/documents/coding/AdventOfCode2017/day05/src/main/java/input.txt"))) {
            final List<Integer> storedInputFile = new ArrayList<>();

            for (String line; (line = bufferedReader.readLine()) != null; ) {
                storedInputFile.add(Integer.parseInt(line));
            }

            System.out.format("Part I: The number of steps taken is %d.\n", calculateNumberOfStepsPartI(storedInputFile));
            System.out.format("Part I: The number of steps taken is %d.\n", calculateNumberOfStepsPartII(storedInputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateNumberOfStepsPartI(final List<Integer> storedInputFile) {
        final List<Integer> instructions = new ArrayList<>(storedInputFile);
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

    private static int calculateNumberOfStepsPartII(final List<Integer> storedInputFile) {
        final List<Integer> instructions = new ArrayList<>(storedInputFile);
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