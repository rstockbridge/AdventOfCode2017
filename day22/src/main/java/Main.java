import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile("src/main/java/input.txt");

        VirusCarrier carrierI = new VirusCarrierPartI(storedInput);
        VirusCarrier carrierII = new VirusCarrierPartII(storedInput);

        System.out.format("Part I: The number of bursts causing infections is %d.\n", solvePuzzle(carrierI, 10000));
        System.out.format("Part II: The number of bursts causing infections is %d.\n", solvePuzzle(carrierII,
                10000000));
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

    private static int solvePuzzle(final VirusCarrier carrier, final int numberOfBursts) {
        for(int i = 0; i < numberOfBursts; i++) {
            carrier.runBurst();
        }

        return carrier.getNumberOfBurstsCausingInfection();
    }
}