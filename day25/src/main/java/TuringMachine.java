import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class TuringMachine {
    private Map<Integer, Integer> tape;
    private int cursorLocation;
    private List<State> states;
    private char currentStateName;

    TuringMachine(final List<State> inputStates, final char inputCurrentStateName) {
        cursorLocation = 0;
        tape = new HashMap<>();
        tape.put(cursorLocation, 0);
        states = inputStates;
        currentStateName = inputCurrentStateName;
    }

    void runStep() {
        final State state = getCurrentState();

        if (tape.get(cursorLocation) == 0) {
            tape.put(cursorLocation, state.getValueToWriteIf0());

            if (state.getDirectionToMoveIf0().equals("right")) {
                cursorLocation++;
            } else {
                cursorLocation--;
            }

            if (!tape.containsKey(cursorLocation)) {
                tape.put(cursorLocation, 0);
            }

            currentStateName = state.getNewStateIf0();
        } else {
            tape.put(cursorLocation, state.getValueToWriteIf1());

            if (state.getDirectionToMoveIf1().equals("right")) {
                cursorLocation++;
            } else {
                cursorLocation--;
            }

            if (!tape.containsKey(cursorLocation)) {
                tape.put(cursorLocation, 0);
            }

            currentStateName = state.getNewStateIf1();
        }
    }

    private State getCurrentState() {
        for (final State state : states) {
            if (currentStateName == state.getName()) {
                return state;
            }
        }

        throw new IllegalStateException("This line will never be reached.");
    }

    int calculateChecksum() {
        int result = 0;

        for (int i : tape.keySet()) {
            result += tape.get(i);
        }

        return result;
    }
}
