import java.util.*;

final class Main {

    public static void main(String[] args) {
        final int finalSquareIndex = 325489;
        final Spiral spiral = new Spiral(finalSquareIndex);
        System.out.format("Part I: The Manhattan distance from square %d to square 1 is %d.\n", finalSquareIndex, spiral.getFinalSquareDistance());
        System.out.format("Part II: The first spiral value larger than %d is %d.\n", finalSquareIndex, calculatePartIIFinalValue(spiral.getLocations(), finalSquareIndex));
    }

    final static class Spiral {

        private int finalSquareIndex;
        private int finalBoxIndex;

        private int currentSquareIndex;
        private int currentBoxIndex;
        private int currentHalfBoxLength;

        private int numberOfSquaresRemaining;

        private List<Coordinate> locations;

        Spiral(final int inputFinalSquare) {
            finalSquareIndex = inputFinalSquare;
            finalBoxIndex = calculateBox(finalSquareIndex);

            currentSquareIndex = 1;
            currentBoxIndex = calculateBox(currentSquareIndex);
            currentHalfBoxLength = calculateHalfBox(currentBoxIndex);

            locations = new ArrayList<>();
            locations.add(new Coordinate(0, 0));

            numberOfSquaresRemaining = finalSquareIndex - 1;

            populateSpiral();
        }

        private static int calculateBox(final int square) {
            return (int) Math.ceil(Math.sqrt(square));
        }

        private static int calculateHalfBox(final int box) {
            return box / 2 + 1;
        }

        private int getPreviousSquare() {
            return currentSquareIndex - 1;
        }

        private void advanceSquare() {
            currentSquareIndex++;
            numberOfSquaresRemaining--;
        }

        private void advanceBox() {
            currentBoxIndex += 2;
            currentHalfBoxLength = calculateHalfBox(currentBoxIndex);
        }

        List<Coordinate> getLocations() {
            return locations;
        }

        private void populateSpiral() {
            while (currentBoxIndex < finalBoxIndex) {
                moveRightOnBox();
                moveUpOnBox();
                moveLeftOnBox();
                moveDownOnBox();
                moveRightOnBox();

                advanceBox();
            }
        }

        private void moveRightOnBox() {
            do {
                advanceSquare();
                locations.add(new Coordinate(locations.get(getPreviousSquare() - 1).getX() + 1, locations.get(getPreviousSquare() - 1).getY()));
            } while (locations.get(currentSquareIndex - 1).getX() < currentHalfBoxLength && numberOfSquaresRemaining > 0);
        }

        private void moveLeftOnBox() {
            do {
                advanceSquare();
                locations.add(new Coordinate(locations.get(getPreviousSquare() - 1).getX() - 1, locations.get(getPreviousSquare() - 1).getY()));
            } while (locations.get(currentSquareIndex - 1).getX() > -currentHalfBoxLength && numberOfSquaresRemaining > 0);
        }

        private void moveUpOnBox() {
            do {
                advanceSquare();
                locations.add(new Coordinate(locations.get(getPreviousSquare() - 1).getX(), locations.get(getPreviousSquare() - 1).getY() + 1));
            } while (locations.get(currentSquareIndex - 1).getY() < currentHalfBoxLength && numberOfSquaresRemaining > 0);
        }

        private void moveDownOnBox() {
            do {
                advanceSquare();
                locations.add(new Coordinate(locations.get(getPreviousSquare() - 1).getX(), locations.get(getPreviousSquare() - 1).getY() - 1));
            } while (locations.get(currentSquareIndex - 1).getY() > -currentHalfBoxLength && numberOfSquaresRemaining > 0);
        }

        int getFinalSquareDistance() {
            return locations.get(finalSquareIndex - 1).getManhattanDistanceFromOrigin();
        }
    }

    private static int calculatePartIIFinalValue(final List<Coordinate> locations, final int finalSquareIndex) {
        final Map<Coordinate, Integer> values = new HashMap<>();

        int squareIndex = 0;
        values.put(locations.get(squareIndex), 1);
        squareIndex ++;

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


    final static class Coordinate {
        int x;
        int y;
        int manhattanDistance;

        Coordinate(final int inputX, final int inputY) {
            x = inputX;
            y = inputY;

            manhattanDistance = Math.abs(x) + Math.abs(y);
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        int getManhattanDistanceFromOrigin() {
            return manhattanDistance;
        }

        Set<Coordinate> get8Neighbors() {
            int x = getX();
            int y = getY();

            final Set<Coordinate> result = new HashSet<>();
            result.add(new Coordinate(x - 1, y - 1));
            result.add(new Coordinate(x - 1, y));
            result.add(new Coordinate(x - 1, y + 1));
            result.add(new Coordinate(x, y - 1));
            result.add(new Coordinate(x, y + 1));
            result.add(new Coordinate(x + 1, y - 1));
            result.add(new Coordinate(x + 1, y));
            result.add(new Coordinate(x + 1, y + 1));

            return result;
        }

        @Override
        public int hashCode() {
            return (x * 31) ^ y;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof Coordinate)) {
                return false;
            }

            final Coordinate other = (Coordinate) o;
            return (x == other.x && y == other.y);
        }
    }
}
