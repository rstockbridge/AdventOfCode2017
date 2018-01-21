import java.util.List;

final class Grid {
    private char[][] values;
    private int length;
    private int width;
    private int initialX;
    
    Grid (final List<String> storedInput) {
        length = storedInput.get(0).length();
        width = storedInput.size();
        
        fillValues(storedInput);
        setInitialX();
    }

    int getLength() {
        return length;
    }

    int getWidth() {
        return width;
    }

    int getInitialX() {
        return initialX;
    }
    
    char getValue(final Coordinate position) {
        return values[position.getX()][position.getY()];
    }

    private void setInitialX() {
        for (int x = 0; x < width; x++) {
            if (values[x][length - 1] != ' ') {
                initialX = x;
            }
        }
    }
    
    private void fillValues(final List<String> storedInput) {
        values = new char[length][width];

        for (int y = 0; y < length; y++) {
            final String line = storedInput.get((y));

            for (int x = 0; x < width; x++) {
                values[x][length - y - 1] = line.charAt(x);
            }
        }
    }
}
