import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> storedInput = readInputFile
                ("/Users/rebecca/Desktop/Dropbox/documents/coding/AdventOfCode2017/day08/src/main/java/input.txt");

        Map<String, Integer> registers = populateRegisters(storedInput);
        List<Instruction> instructions = parseInstructions(storedInput);

        Output output = solvePuzzle(registers, instructions);
        System.out.println(output.getHighestValueAtCompletion());
        System.out.println(output.getHighestValueEver());
    }

    private static List<String> readInputFile(final String inputFilePath) throws IOException {
        final List<String> result = new ArrayList<>();

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                result.add(line);
            }
        }

        return result;
    }

    private static Map<String, Integer> populateRegisters(final List<String> storedInput) {
        final Map<String, Integer> result = new HashMap<>();

        for (final String line : storedInput) {
            result.put(line.split(" ")[0], 0);
        }

        return result;
    }

    private static List<Instruction> parseInstructions(final List<String> storedInput) {
        final List<Instruction> result = new ArrayList<>();

        for (final String line : storedInput) {
            result.add(new Instruction(line));
        }

        return result;
    }

    final static class Instruction {
        String registerToModify;
        String directionToModify;
        int amountToModify;
        String registerInCondition;
        String requirementInCondition;
        int amountInCondition;

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

    private static boolean conditionIsTrue(final Instruction instruction, final int amount) {
        final int amountInCondition = instruction.getAmountInCondition();

        switch (instruction.getRequirementInCondition()) {
            case ">":
                return amount > amountInCondition;
            case "<":
                return amount < amountInCondition;
            case "==":
                return amount == amountInCondition;
            case "<=":
                return amount <= amountInCondition;
            case ">=":
                return amount >= amountInCondition;
            case "!=":
                return amount != amountInCondition;
        }

        throw new IllegalStateException("This line should not be reached.");
    }

    final static class Output {
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

    private static Output solvePuzzle(final Map<String, Integer> registers, List<Instruction> instructions) {
        int maxValue = 0;

        for (final Instruction instruction : instructions) {
            if (conditionIsTrue(instruction, registers.get(instruction.getRegisterInCondition()))) {
                if (instruction.getDirectionToModify().equals("inc")) {
                    registers.put(instruction.getRegisterToModify(), registers.get(instruction.getRegisterToModify())
                            + instruction.getAmountToModify());
                } else {
                    registers.put(instruction.getRegisterToModify(), registers.get(instruction.getRegisterToModify()) - instruction.getAmountToModify());
                }

                if (registers.get(instruction.getRegisterToModify()) > maxValue) {
                    maxValue = registers.get(instruction.getRegisterToModify());
                }
            }
        }

        return new Output(Collections.max(registers.values()), maxValue);
    }
}