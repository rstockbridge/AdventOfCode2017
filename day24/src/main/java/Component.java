final class Component {
    private int leftPort;
    private int rightPort;

    Component(final int inputPort1, final int inputPort2) {
        leftPort = inputPort1;
        rightPort = inputPort2;
    }

    int getRightPort() {
        return rightPort;
    }

    int getStrength() {
        return leftPort + rightPort;
    }

    boolean contains(final int i) {
        return leftPort == i || rightPort == i;
    }

    void flip() {
        final int temp = leftPort;
        leftPort = rightPort;
        rightPort = temp;
    }
}
