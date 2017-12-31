import java.util.*;

final class ProgramPartI {
    private Map<String, Long> registers;
    private List<Instruction> instructions;
    private int i = 0;
    private long lastSoundPlayed;

    ProgramPartI(final List<String> input) {
        registers = populateRegisters(input);
        instructions = parseInstructions(input);
    }

    private Map<String, Long> populateRegisters(final List<String> storedInput) {
        final Map<String, Long> result = new HashMap<>();

        for (final String line : storedInput) {
            String possibleRegister = line.split(" ")[1];
            if (!isInteger(possibleRegister)) {
                result.put(possibleRegister, (long) 0);
            }
        }

        return result;
    }

    private List<Instruction> parseInstructions(final List<String> storedInput) {
        final List<Instruction> result = new ArrayList<>();

        for (final String line : storedInput) {
            result.add(new Instruction(line));
        }

        return result;
    }

    long solvePartI() {
        while (i >= 0 && i < instructions.size()) {
            final Instruction instruction = instructions.get(i);
            final String action = instruction.getAction();
            final String leftText = instruction.getLeftText();
            final long leftValue = getValue(leftText);
            // rightValue can only be calculated for some instruction types

            switch (action) {
                case "snd":
                    lastSoundPlayed = getValue(instruction.getLeftText());
                    i++;
                    break;
                case "rcv":
                    if (registers.get(leftText) != 0) {
                        return lastSoundPlayed;
                    }
                    i++;
                    break;
                case "set":
                    registers.put(leftText, getValue(instruction.getRightText()));
                    i++;
                    break;
                case "add":
                    registers.put(leftText, leftValue + getValue(instruction.getRightText()));
                    i++;
                    break;
                case "mul":
                    registers.put(leftText, leftValue * getValue(instruction.getRightText()));
                    i++;
                    break;
                case "mod":
                    registers.put(leftText, leftValue % getValue(instruction.getRightText()));
                    i++;
                    break;
                case "jgz":
                    if (registers.get(leftText) > 0) {
                        i += getValue(instruction.getRightText());
                    } else {
                        i++;
                    }
                    break;
            }
        }

        throw new IllegalStateException("This line should not be reached.");
    }

    private static boolean isInteger(final String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private long getValue(final String text) {
        if (isInteger(text)) {
            return (long) Integer.parseInt(text);
        } else {
            return registers.get(text);
        }
    }
}
