import java.util.HashSet;
import java.util.Set;

final class Coordinate {
    private int x;
    private int y;
    private int manhattanDistance;

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