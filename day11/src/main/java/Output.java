final class Output {
    private int numberOfSteps;
    private int maxNumberOfSteps;

    Output(final int inputNumberOfSteps, final int inputMaxNumberOfSteps) {
        numberOfSteps = inputNumberOfSteps;
        maxNumberOfSteps = inputMaxNumberOfSteps;
    }

    int getNumberOfSteps() {
        return numberOfSteps;
    }

    int getMaxNumberOfSteps() {
        return maxNumberOfSteps;
    }
}