public class NumberedCard extends Card {
    private final int number;

    public NumberedCard(Color color, int number) {
        super(color);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return color + " " + number;
    }

    @Override
    public void applyAction(Game game) {
        // Implementation for applying number card effects (if any)
    }
}