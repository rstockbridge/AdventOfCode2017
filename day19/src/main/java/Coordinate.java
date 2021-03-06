import java.util.Objects;

final class Coordinate {
    private int x;
    private int y;

    Coordinate(final int inputX, final int inputY) {
        x = inputX;
        y = inputY;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}