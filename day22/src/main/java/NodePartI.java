final class NodePartI {
    private boolean isInfected;

    NodePartI(final boolean inputIsInfected) {
        isInfected = inputIsInfected;
    }

    boolean isInfected() {
        return isInfected;
    }

    void updateState() {
        isInfected = !isInfected;
    }
}
