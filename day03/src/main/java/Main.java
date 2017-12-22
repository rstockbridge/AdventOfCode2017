import java.util.*;

final class Main {

    public static void main(String[] args) {
        final int finalSquareIndex = 325489;
        final Spiral spiral = new Spiral(finalSquareIndex);

        System.out.format("Part I: The Manhattan distance from square %d to square 1 is %d.\n", finalSquareIndex, spiral.getFinalSquareDistance());
        System.out.format("Part II: The first spiral value larger than %d is %d.\n", finalSquareIndex, calculatePartIIFinalValue(spiral.getLocations(), finalSquareIndex));
    }

    private static int calculatePartIIFinalValue(final List<Coordinate> locations, final int finalSquareIndex) {
        final Map<Coordinate, Integer> values = new HashMap<>();

        int squareIndex = 0;
        values.put(locations.get(squareIndex), 1);
        squareIndex++;

        do {
            int sum = 0;

            for (final Coordinate neighborLocation : locations.get(squareIndex).get8Neighbors()) {
                if (values.containsKey(neighborLocation)) {
                    sum += values.get(neighborLocation);
                }
            }

            values.put(locations.get(squareIndex), sum);
            squareIndex++;
        } while (values.get(locations.get(squareIndex - 1)) < finalSquareIndex);

        return values.get(locations.get(squareIndex - 1));
    }

}
