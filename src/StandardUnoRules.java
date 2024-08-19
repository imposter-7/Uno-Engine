// StandardUnoRules.java (Implements GameRules)
public class StandardUnoRules implements GameRules {
    @Override
    public boolean isValidMove(Card card, Card topCard) {
        if (topCard instanceof WildCard) {
            return true; // Any card can be played on a Wild Card
        }

        if (card.getColor() == topCard.getColor()) {
            return true; // Matching colors
        }

        if (card instanceof NumberedCard && topCard instanceof NumberedCard) {
            NumberedCard numberedCard = (NumberedCard) card;
            NumberedCard topNumberedCard = (NumberedCard) topCard;
            return numberedCard.getNumber() == topNumberedCard.getNumber(); // Matching numbers
        }

        if (card instanceof ActionCard && topCard instanceof ActionCard) {
            ActionCard actionCard = (ActionCard) card;
            ActionCard topActionCard = (ActionCard) topCard;
            return actionCard.getAction() == topActionCard.getAction(); // Matching actions
        }


        if (card instanceof WildCard) {
            return true; // Wild cards can always be played
        }

        return false; // Invalid move
    }

    @Override
    public int getNumberOfCardsToDraw() {
        return 1;
    }
}