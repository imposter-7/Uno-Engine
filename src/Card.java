public abstract class Card {
    public enum Color {
        RED, YELLOW, GREEN, BLUE, WILD
    }

    protected Color color;

    public Card(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract String toString();

    public abstract void applyAction(Game game);
}
