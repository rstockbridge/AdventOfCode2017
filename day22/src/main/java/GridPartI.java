import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class GridPartI {
    private Map<Coordinate, NodePartI> nodes = new HashMap<>();

    GridPartI(final List<String> input) {
        parseInput(input);
    }

    Map<Coordinate, NodePartI> getNodes() {
        return nodes;
    }

    private void parseInput(final List<String> input) {
        int xShift = input.get(0).length() / 2;
        int yShift = input.size() / 2;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);

            for (int j = 0; j < line.length(); j++) {
                boolean nodeIsInfected = (line.charAt(j) == '#');
                addNode(new Coordinate(j - xShift, yShift - i), nodeIsInfected);
            }
        }
    }

    void addNode(final Coordinate coordinate, final boolean nodeIsInfected) {
        nodes.put(coordinate, new NodePartI(nodeIsInfected));
    }

    boolean containsNode(final Coordinate location) {
        return nodes.containsKey(location);
    }

    boolean nodeIsInfected(final Coordinate location) {
        return nodes.get(location).isInfected();
    }

    void switchNodeInfectionStatus(final Coordinate location) {
        nodes.get(location).updateState();
    }
}
