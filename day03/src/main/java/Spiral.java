import java.util.ArrayList;
import java.util.List;

final class Spiral {

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