final class Output {
    private int severity;
    private boolean wasCaught;

    Output(final int inputSeverity, final boolean inputWasCaught) {
        severity = inputSeverity;
        wasCaught = inputWasCaught;
    }

    int getSeverity() {
        return severity;
    }

    boolean getWasCaught() {
        return wasCaught;
    }
}