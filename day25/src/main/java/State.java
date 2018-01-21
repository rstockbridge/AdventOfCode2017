final class State {
    private char name;

    private int valueToWriteIf0;
    private String directionToMoveIf0;
    private char newStateIf0;

    private int valueToWriteIf1;
    private String directionToMoveIf1;
    private char newStateIf1;

    State(final char inputName, final int inputValueToWriteIF0, final String inputDirectionToMoveIf0, final char inputNewStateIf0, final int inputValueToWriteIf1, final String inputDirectionToMoveIf1, final char inputNewStateIf1) {
        name = inputName;

        valueToWriteIf0 = inputValueToWriteIF0;
        directionToMoveIf0 = inputDirectionToMoveIf0;
        newStateIf0 = inputNewStateIf0;

        valueToWriteIf1 = inputValueToWriteIf1;
        directionToMoveIf1 = inputDirectionToMoveIf1;
        newStateIf1 = inputNewStateIf1;
    }

    char getName() {
        return name;
    }

    int getValueToWriteIf0() {
        return valueToWriteIf0;
    }

    String getDirectionToMoveIf0() {
        return directionToMoveIf0;
    }

    char getNewStateIf0() {
        return newStateIf0;
    }

    int getValueToWriteIf1() {
        return valueToWriteIf1;
    }

    String getDirectionToMoveIf1() {
        return directionToMoveIf1;
    }

    char getNewStateIf1() {
        return newStateIf1;
    }
}
