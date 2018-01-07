final class NodePartII {
    private State state;

    NodePartII(final State inputState) {
        state = inputState;
    }

    State getState() {
        return state;
    }

    void updateState() {
        switch (state) {
            case CLEAN:
                state = State.WEAKENED;
                break;
            case WEAKENED:
                state = State.INFECTED;
                break;
            case INFECTED:
                state = State.FLAGGED;
                break;
            case FLAGGED:
                state = State.CLEAN;
                break;
        }
    }
}
