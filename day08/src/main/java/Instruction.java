final class Instruction {
    private String registerToModify;
    private String directionToModify;
    private int amountToModify;
    private String registerInCondition;
    private String requirementInCondition;
    private int amountInCondition;

    Instruction(final String inputString) {
        final String[] parsedInstruction = inputString.split(" ");

        registerToModify = parsedInstruction[0];
        directionToModify = parsedInstruction[1];
        amountToModify = Integer.parseInt(parsedInstruction[2]);
        registerInCondition = parsedInstruction[4];
        requirementInCondition = parsedInstruction[5];
        amountInCondition = Integer.parseInt(parsedInstruction[6]);
    }

    String getRegisterToModify() {
        return registerToModify;
    }

    String getDirectionToModify() {
        return directionToModify;
    }

    int getAmountToModify() {
        return amountToModify;
    }

    String getRegisterInCondition() {
        return registerInCondition;
    }

    String getRequirementInCondition() {
        return requirementInCondition;
    }

    int getAmountInCondition() {
        return amountInCondition;
    }
}