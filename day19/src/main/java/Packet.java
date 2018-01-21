final class Packet {
    private Coordinate position;
    private Orientation direction;
    private int numberOfSteps;

    Packet(final Coordinate inputPosition, final Orientation inputDirection) {
        position = inputPosition;
        direction = inputDirection;
        numberOfSteps = 1;
    }

    Coordinate getPosition() {
        return position;
    }

    Orientation getDirection() {
        return direction;
    }

    int getNumberOfSteps() {
        return numberOfSteps;
    }

    void advance() {
        switch (direction) {
            case NORTH:
                position = new Coordinate(position.getX(), position.getY() + 1);
                break;
            case SOUTH:
                position = new Coordinate(position.getX(), position.getY() - 1);
                break;
            case EAST:
                position = new Coordinate(position.getX() + 1, position.getY());
                break;
            case WEST:
                position = new Coordinate(position.getX() - 1, position.getY());
                break;
        }

        numberOfSteps++;
    }

    void turn(final Grid grid) {
        switch (direction) {
            case NORTH:
                if (grid.getValue(new Coordinate(position.getX() - 1, position.getY())) == ' ') {
                    turnRight();
                } else {
                    turnLeft();
                }
                break;
            case SOUTH:
                if (grid.getValue(new Coordinate(position.getX() + 1, position.getY())) == ' ') {
                    turnRight();
                } else {
                    turnLeft();
                }
                break;
            case EAST:
                if (grid.getValue(new Coordinate(position.getX(), position.getY() + 1)) == ' ') {
                    turnRight();
                } else {
                    turnLeft();
                }
                break;
            case WEST:
                if (grid.getValue(new Coordinate(position.getX(), position.getY() - 1)) == ' ') {
                    turnRight();
                } else {
                    turnLeft();
                }
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Orientation.EAST;
                break;
            case SOUTH:
                direction = Orientation.WEST;
                break;
            case EAST:
                direction = Orientation.SOUTH;
                break;
            case WEST:
                direction = Orientation.NORTH;
                break;
        }
    }

    private void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Orientation.WEST;
                break;
            case SOUTH:
                direction = Orientation.EAST;
                break;
            case EAST:
                direction = Orientation.NORTH;
                break;
            case WEST:
                direction = Orientation.SOUTH;
                break;
        }
    }
}
