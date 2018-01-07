import java.util.List;

final class VirusCarrierPartII implements VirusCarrier {
    private Orientation orientation;
    private Coordinate location;
    private GridPartII grid;

    private int numberOfBurstsCausingInfection;

    VirusCarrierPartII(final List<String> input) {
        grid = new GridPartII(input);
        orientation = Orientation.NORTH;
        location = new Coordinate(0, 0);
    }

    public void runBurst() {
        if (!grid.containsNode(location)) {
            grid.addNode(new Coordinate(location), State.CLEAN);
        }

        switch(grid.getNodeState(location)) {
            case CLEAN:
                turnLeft();
                break;
            case WEAKENED:
                break;
            case INFECTED:
                turnRight();
                break;
            case FLAGGED:
                turnRight();
                turnRight();
                break;
        }

        grid.updateNodeState(location);
        if(grid.getNodeState(location) == State.INFECTED) {
            numberOfBurstsCausingInfection++;
        }

        advance();
    }

    private void turnRight() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    private void turnLeft() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
        }
    }

    private void advance() {
        switch (orientation) {
            case NORTH:
                location.setY(location.getY() + 1);
                break;
            case SOUTH:
                location.setY(location.getY() - 1);
                break;
            case EAST:
                location.setX(location.getX() + 1);
                break;
            case WEST:
                location.setX(location.getX() - 1);
                break;
        }
    }

    public int getNumberOfBurstsCausingInfection() {
        return numberOfBurstsCausingInfection;
    }
}
