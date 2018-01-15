import java.util.ArrayList;
import java.util.List;

final class Bridge {
    private List<Component> links;
    private int strength;

    Bridge(final List<Component> inputBridge) {
        links = new ArrayList<>(inputBridge);
        strength = calculateStrength();
    }

    Bridge(final Component head, final Bridge inputBridge) {
        links = new ArrayList<>(inputBridge.getLinks());
        links.add(0, head);
        strength = calculateStrength();
    }

    private int calculateStrength() {
        int result = 0;

        for (final Component component : links) {
            result += component.getStrength();
        }

        return result;
    }

    private List<Component> getLinks() {
        return links;
    }

    int getStrength() {
        return strength;
    }

    int getSize() {
        return links.size();
    }
}

