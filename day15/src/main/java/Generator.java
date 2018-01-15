final class Generator {
    private static final long DIVISOR = 2147483647;
    private long startValue;
    private long factor;
    private long previousValue;
    private long partIIDivisor;

    Generator(final long inputStartValue, final long inputFactor, final long inputPartIIDivisor) {
        startValue = inputStartValue;
        previousValue = startValue;
        factor = inputFactor;
        partIIDivisor = inputPartIIDivisor;
    }

    void reset() {
        previousValue = startValue;
    }

    String nextValuePartI() {
        long currentValue = (previousValue * factor) % DIVISOR;

        String fullBinary = Long.toBinaryString(currentValue);
        String shortBinary = fullBinary.substring(Math.max(0, fullBinary.length() - 16), fullBinary.length());

        previousValue = currentValue;

        return shortBinary;
    }

    String nextValuePartII() {
        long currentValue = (previousValue * factor) % DIVISOR;

        String fullBinary = Long.toBinaryString(currentValue);
        String shortBinary = fullBinary.substring(Math.max(0, fullBinary.length() - 16), fullBinary.length());

        previousValue = currentValue;

        if (currentValue % partIIDivisor == 0) {
            return shortBinary;
        } else {
            return nextValuePartII();
        }
    }
}
