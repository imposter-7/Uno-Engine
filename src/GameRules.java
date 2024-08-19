public interface GameRules {
    boolean isValidMove(Card card, Card topCard);
    int getNumberOfCardsToDraw();
}
