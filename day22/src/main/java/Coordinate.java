import java.util.Objects;

final class Coordinate {
    private int x;
    private int y;

    Coordinate(final int inputX, final int inputY) {
        x = inputX;
        y = inputY;
    }

    Coordinate(final Coordinate inputCoordinate) {
        x = inputCoordinate.getX();
        y = inputCoordinate.getY();
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(final int inputX) {
       x = inputX;
    }

    void setY(final int inputY) {
        y = inputY;
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