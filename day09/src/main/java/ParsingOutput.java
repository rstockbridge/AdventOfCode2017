final class ParsingOutput {

    private String stream;
    private int numberOfNonCanceledCharacters;

    ParsingOutput(final String stream) {
        parse(stream);
    }

    String getStream() {
        return stream;
    }

    int getNumberOfNonCanceledCharacters() {
        return numberOfNonCanceledCharacters;
    }

    private void parse(final String inputStream) {
        final String streamNoCanceledCharacters = removeCanceledCharacters(inputStream);

        final int numberOfCharactersBeforeDeletion = streamNoCanceledCharacters.length();
        final int numberOfExclamationPoints = (int) streamNoCanceledCharacters.chars().filter(num -> num == '!').count();

        // need to calculate numberOfNonCanceledCharacters before removing '<', '>', and ','
        final String streamNoInnerGarbage = removeInnerGarbage(streamNoCanceledCharacters);
        stream = removeFinalCharacters(streamNoInnerGarbage);
        numberOfNonCanceledCharacters = numberOfCharactersBeforeDeletion - numberOfExclamationPoints - streamNoInnerGarbage.length();
    }

    private String removeCanceledCharacters(final String stream) {
        final StringBuilder streamSB = new StringBuilder(stream);

        int i = 0;
        while (i < streamSB.length() - 1) {
            if (streamSB.charAt(i) == '!') {
                streamSB.deleteCharAt(i + 1);
            }
            i++;
        }

        return streamSB.toString();
    }

    private String removeInnerGarbage(final String streamNoCanceledCharacters) {
        final StringBuilder streamSB = new StringBuilder(streamNoCanceledCharacters);

        int i = 0;
        while (i < streamSB.length()) {
            if (streamSB.charAt(i) == '<') {
                i++;
                while (streamSB.charAt(i) != '>') {
                    streamSB.deleteCharAt(i);
                }
            }
            i++;
        }

        return streamSB.toString();
    }

    private String removeFinalCharacters(final String streamNoInnerGarbage) {
        return streamNoInnerGarbage.replaceAll(",|<>", "");
    }
}