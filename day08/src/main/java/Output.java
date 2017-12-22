final class Output {
    private int highestValueAtCompletion;
    private int highestValueEver;

    Output(final int inputHighestValueAtCompletion, final int inputHighestValueEver) {
        highestValueAtCompletion = inputHighestValueAtCompletion;
        highestValueEver = inputHighestValueEver;
    }

    int getHighestValueAtCompletion() {
        return highestValueAtCompletion;
    }

    int getHighestValueEver() {
        return highestValueEver;
    }
}