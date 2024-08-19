public class NumberedCard extends Card {
    private int number;

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

    }
}