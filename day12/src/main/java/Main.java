import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        Output output = solvePuzzle(storedInput);

        System.out.format("Part I: The number of programs in the program 0 group is %d.\n", output.getSizeOfGroupWith0());
        System.out.format("Part II: The total number of groups is %d.\n", output.getNumberOfGroups());
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

    private static Output solvePuzzle(final List<String> storedInput) {
        List<Set<String>> groups = new ArrayList<>();

        for (final String line : storedInput) {
            final Set<String> programsInLine = getProgramsInLine(line);

            processPrograms(groups, programsInLine);
            groups = combineGroups(groups);
        }

        int sizeOfGroupWith0 = 0;
        for (final Set<String> group : groups) {
            if (group.contains("0")) {
                sizeOfGroupWith0 = group.size();
            }
        }

        return new Output(sizeOfGroupWith0, groups.size());
    }

    private static Set<String> getProgramsInLine(final String line) {
        String[] splitLeftRight = line.split(" <-> ");
        String[] splitRightPrograms = splitLeftRight[1].split(", ");

        Set<String> programsInLine = new HashSet<>();
        programsInLine.add(splitLeftRight[0]);
        programsInLine.addAll(Arrays.asList(splitRightPrograms));

        return programsInLine;
    }

    private static void processPrograms(final List<Set<String>> groups, final Set<String> programs) {
        if (groups.size() == 0) {
            groups.add(programs);
        } else {
            boolean newGroup = true;

            for (final Set<String> group : groups) {
                if (!Collections.disjoint(group, programs)) {
                    group.addAll(programs);
                    newGroup = false;
                }
            }
            if (newGroup) {
                groups.add(programs);
            }
        }
    }

    private static List<Set<String>> combineGroups(final List<Set<String>> groups) {
        final List<Set<String>> combinedGroups = new ArrayList<>();

        for (final Set<String> group : groups) {
            boolean newReducedGroup = true;

            for (final Set<String> combinedGroup : combinedGroups) {
                if (!Collections.disjoint(combinedGroup, group)) {
                    combinedGroup.addAll(group);
                    newReducedGroup = false;
                }
            }

            if (newReducedGroup) {
                combinedGroups.add(group);
            }
        }

        return combinedGroups;
    }
}