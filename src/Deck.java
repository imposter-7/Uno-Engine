import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Deck.java
public class Deck {
    private static Deck instance;
    private List<Card> cards;

    private Deck() {
        cards = new ArrayList<>();
        createDeck();
    }

    public static Deck getInstance() {
        if (instance == null){
            instance = new Deck();
        }
        return instance;
    }

    private void createDeck() {
        // Create numbered cards
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.WILD) {
                for (int number = 0; number <= 9; number++) {
                    cards.add(new NumberedCard(color, number));
                    if (number != 0) {
                        cards.add(new NumberedCard(color, number));
                    }
                }
            }
        }

        // Create action cards
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.WILD) {
                for (ActionCard.Action action : ActionCard.Action.values()) {
                    cards.add(new ActionCard(color, action));
                    cards.add(new ActionCard(color, action));
                }
            }
        }

        // Create wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard(WildCard.WildType.WILD));
            cards.add(new WildCard(WildCard.WildType.WILD_DRAW_FOUR));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}