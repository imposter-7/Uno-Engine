public class WildCard extends Card {
    public enum WildType {
        WILD, WILD_DRAW_FOUR
    }

    private WildType wildType;

    public WildCard(WildType wildType) {
        super(Color.WILD); // Wild cards always have color WILD
        this.wildType = wildType;
    }

    public WildType getWildType() {
        return wildType;
    }

    @Override
    public String toString() {
        return wildType.toString();
    }

    @Override
    public void applyAction(Game game) {

    }
}