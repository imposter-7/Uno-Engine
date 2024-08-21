public class ActionCard extends Card {
    public enum Action {
        REVERSE, SKIP, DRAW_TWO
    }

    private Action action;

    public ActionCard(Color color, Action action) {
        super(color);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return color + " " + action;
    }

    @Override
    public void applyAction(Game game) {
        // Implementation for applying action card effects
    }
}