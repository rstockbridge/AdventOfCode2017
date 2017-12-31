final class Instruction {
    private String action;
    private String leftText;
    private String rightText;

    Instruction(final String inputString) {
        final String[] parsedInstruction = inputString.split(" ");

        action = parsedInstruction[0];
        leftText = parsedInstruction[1];

        if (parsedInstruction.length == 3) {
            rightText = parsedInstruction[2];
        }
    }

    String getAction() {
        return action;
    }

    String getLeftText() {
        return leftText;
    }

    String getRightText() {
        return rightText;
    }
}