import java.util.ArrayList;
import java.util.List;


public class DiscardPile {
    private static DiscardPile instance;
    private List<Card> cards;

    private DiscardPile() {
        cards = new ArrayList<>();
    }

    public static DiscardPile getInstance() {
        if (instance == null) {
            instance = new DiscardPile();
        }
        return instance;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.get(cards.size() - 1);
    }
}