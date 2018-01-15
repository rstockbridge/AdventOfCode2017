final class Main {

    public static void main(String[] args) {
        final Generator A = new Generator(883, 16807, 4);
        final Generator B = new Generator(879, 48271, 8);

        System.out.format("Part II: %d.\n", calculatePartICount(A, B, 40000000));

        A.reset();
        B.reset();

        System.out.format("Part II: %d.\n", calculatePartIICount(A, B, 5000000));
    }

    private static int calculatePartICount(final Generator A, final Generator B, final int numberOfPairs) {
        int result = 0;

        for (int i = 0; i < numberOfPairs; i++) {
            if (A.nextValuePartI().equals(B.nextValuePartI())) {
                result++;
            }
        }

        return result;
    }

    private static int calculatePartIICount(final Generator A, final Generator B, final int numberOfPairs) {
        int result = 0;

        for (int i = 0; i < numberOfPairs; i++) {
            if (A.nextValuePartII().equals(B.nextValuePartII())) {
                result++;
            }
        }

        return result;
    }
}