import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/work/coding/AdventOfCode2017/day19/src/main/java/input" +
                        ".txt");

        final Grid grid = new Grid(storedInput);
        final Packet packet = new Packet(new Coordinate(grid.getInitialX(), grid.getLength() - 1), Orientation.SOUTH);

        System.out.format("Part I: The letters are %s.\n", solvePartI(grid, packet));
        System.out.format("Part II: The number of steps is %d.\n", packet.getNumberOfSteps());
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

    private static char getLocationChar(final Grid grid, final Coordinate position) {
        return grid.getValue(position);
    }

    private static boolean keepGoing(final Grid grid, final Coordinate position, final Orientation direction) {
        switch (direction) {
            case NORTH:
                if (grid.getValue(position) != '+') {
                    if (position.getY() == grid.getWidth()) {
                        return false;
                    } else if (grid.getValue(new Coordinate(position.getX(), position.getY() + 1)) == ' ') {
                        return false;
                    }
                }
                break;
            case SOUTH:
                if (grid.getValue(position) != '+') {
                    if (position.getY() == 0) {
                        return false;
                    } else if (grid.getValue(new Coordinate(position.getX(), position.getY() - 1)) == ' ') {
                        return false;
                    }
                }
                break;
            case EAST:
                if (grid.getValue(position) != '+') {
                    if (position.getX() == grid.getLength()) {
                        return false;
                    } else if (grid.getValue(new Coordinate(position.getX() + 1, position.getY())) == ' ') {
                        return false;
                    }
                }
                break;
            case WEST:
                if (grid.getValue(position) != '+') {
                    if (position.getX() == 0) {
                        return false;
                    } else if (grid.getValue(new Coordinate(position.getX() - 1, position.getY())) == ' ') {
                        return false;
                    }
                }
                break;
        }

        return true;
    }

    private static String solvePartI(final Grid grid, final Packet packet) {
        String result = "";

        do {
            final char currentChar = getLocationChar(grid, packet.getPosition());

            if (Character.isLetter(currentChar)) {
                result += currentChar;
            } else if (currentChar == '+') {
                packet.turn(grid);
            }

            packet.advance();
        } while (keepGoing(grid, packet.getPosition(), packet.getDirection()));

        final char currentChar = getLocationChar(grid, packet.getPosition());
        if (Character.isLetter(currentChar)) {
            result += currentChar;
        }

        return result;
    }
}