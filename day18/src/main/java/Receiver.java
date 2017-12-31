interface Receiver {
    void addToReceived(final long value);

    long getNextReceived();

    int getReceivedSize();
}
