final class Output {
    private int numberOfRedistributionCycles;
    private int numberOfCycles;

    Output(final int inputNumberOfRedistributionCycles, final int inputNumberOfCycles) {
        numberOfRedistributionCycles = inputNumberOfRedistributionCycles;
        numberOfCycles = inputNumberOfCycles;
    }

    int getNumberOfRedistributionCycles() {
        return numberOfRedistributionCycles;
    }

    int getNumberOfCycles() {
        return numberOfCycles;
    }
}