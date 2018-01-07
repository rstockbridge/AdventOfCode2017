import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class GridPartII {
    private Map<Coordinate, NodePartII> nodes = new HashMap<>();

    GridPartII(final List<String> input) {
        parseInput(input);
    }

    private void parseInput(final List<String> input) {
        int xShift = input.get(0).length() / 2;
        int yShift = input.size() / 2;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);

            for (int j = 0; j < line.length(); j++) {
                State state;
                if (line.charAt(j) == '#') {
                    state = State.INFECTED;
                } else {
                    state = State.CLEAN;
                }
                addNode(new Coordinate(j - xShift, yShift - i), state);
            }
        }
    }

    void addNode(final Coordinate coordinate, final State state) {
        nodes.put(coordinate, new NodePartII(state));
    }

    boolean containsNode(final Coordinate location) {
        return nodes.containsKey(location);
    }

    State getNodeState(final Coordinate location) {
        return nodes.get(location).getState();
    }

    void updateNodeState(final Coordinate location) {
        nodes.get(location).updateState();
    }
}
