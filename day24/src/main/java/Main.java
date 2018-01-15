import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day24/src/main/java/input" +
                        ".txt");

        final List<Component> components = parseInput(storedInput);
        List<Bridge> validBridges = findValidBridges(0, components);

        System.out.format("Part I: The maximum strength is %d.\n", calculateMaxStrength(validBridges));
        System.out.format("Part II: The maximum strength of the longest bridge is %d.\n", calculateMaxStrengthForLongest
                (validBridges));
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

    private static List<Component> parseInput(final List<String> storedInput) {
        final List<Component> result = new ArrayList<>();

        for (String line : storedInput) {
            String[] ports = line.split("/");
            result.add(new Component(Integer.parseInt(ports[0]), Integer.parseInt(ports[1])));
        }

        return result;
    }

    private static List<Bridge> findValidBridges(final int lastRightPort, final List<Component> restOfComponents) {
        final List<Bridge> result = new ArrayList<>();

        for (final Component head : restOfComponents) {
            final List<Component> restOfComponentsCopy = new ArrayList<>(restOfComponents);
            restOfComponentsCopy.remove(head);

            if (head.contains(lastRightPort)) {
                if (head.getRightPort() == lastRightPort) {
                    head.flip();
                }

                for (final Bridge bridge : findValidBridges(head.getRightPort(), restOfComponentsCopy)) {
                    result.add(new Bridge(head, bridge));
                }

                result.add(new Bridge(Collections.singletonList(head)));
            }
        }

        return result;
    }

    private static int calculateMaxStrength(final List<Bridge> validBridges) {
        int result = 0;

        for (final Bridge bridge : validBridges) {
            if (bridge.getStrength() > result) {
                result = bridge.getStrength();
            }
        }

        return result;
    }

    private static int calculateMaxStrengthForLongest(final List<Bridge> validBridges) {
        int maxLength = 0;
        int result = 0;

        for (final Bridge bridge : validBridges) {
            if (bridge.getSize() >= maxLength) {
                maxLength = bridge.getSize();

                if (bridge.getStrength() > result) {
                    result = bridge.getStrength();
                }
            }
        }

        return result;
    }
}