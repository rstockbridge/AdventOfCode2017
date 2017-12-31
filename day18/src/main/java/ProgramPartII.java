import java.util.*;

final class ProgramPartII implements Receiver {
    private Map<String, Long> registers;
    private List<Instruction> instructions;
    private int i = 0;

    private Queue<Long> received = new LinkedList<>();
    private boolean isWaiting = false;
    private int sendCount = 0;

    ProgramPartII(final List<String> input) {
        registers = populateRegisters(input);
        instructions = parseInstructions(input);
    }

    int getIndex() {
        return i;
    }

    boolean isWaiting() {
        return isWaiting;
    }

    int getSendCount() {
        return sendCount;
    }

    public void addToReceived(final long value) {
        received.add(value);
    }

    public long getNextReceived() {
        return received.remove();
    }

    public int getReceivedSize() {
        return received.size();
    }

    void addToRegisters(final String text, final long value) {
        registers.put(text, value);
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

    void processInstruction(final Receiver otherProgram) {
        final Instruction instruction = instructions.get(i);
        final String leftText = instruction.getLeftText();
        final long leftValue = getValue(leftText);
        // rightValue can only be calculated for some instruction types

        switch (instruction.getAction()) {
            case "snd":
                otherProgram.addToReceived(getValue(instruction.getLeftText()));
                sendCount++;
                i++;
                break;
            case "set":
                registers.put(leftText, getValue(instruction.getRightText()));
                i++;
                break;
            case "add":
                registers.put(leftText, registers.get(leftText) + getValue(instruction.getRightText()));
                i++;
                break;
            case "mul":
                registers.put(leftText, registers.get(leftText) * getValue(instruction.getRightText()));
                i++;
                break;
            case "mod":
                registers.put(leftText, registers.get(leftText) % getValue(instruction.getRightText()));
                i++;
                break;
            case "rcv":
                if (getReceivedSize() > 0) {
                    registers.put(leftText, getNextReceived());
                    isWaiting = false;
                    i++;
                } else {
                    isWaiting = true;
                }
                break;
            case "jgz":
                if (leftValue > 0) {
                    i += getValue(instruction.getRightText());
                } else {
                    i++;
                }
                break;
        }
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
