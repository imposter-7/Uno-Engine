public class CardFactory {
    public static Card createCard(Card.Color color, int number, ActionCard.Action action, WildCard.WildType wildType) {
        if (color != Card.Color.WILD && number >= 0) {
            return new NumberedCard(color, number);
        } else if (color != Card.Color.WILD && action != null) {
            return new ActionCard(color, action);
        } else if (color == Card.Color.WILD && wildType != null) {
            return new WildCard(wildType);
        } else {
            return null;
        }
    }
}