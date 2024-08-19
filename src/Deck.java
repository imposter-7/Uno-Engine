import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Deck.java
public class Deck implements IDeck {
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
                    cards.add(CardFactory.createCard(color, number, null, null)); // Use CardFactory
                    if (number != 0) {
                        cards.add(CardFactory.createCard(color, number, null, null)); // Use CardFactory
                    }
                }
            }
        }

        // Create action cards
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.WILD) {
                for (ActionCard.Action action : ActionCard.Action.values()) {
                    cards.add(CardFactory.createCard(color, -1, action, null)); // Use CardFactory
                    cards.add(CardFactory.createCard(color, -1, action, null)); // Use CardFactory
                }
            }
        }

        // Create wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(CardFactory.createCard(Card.Color.WILD, -1, null, WildCard.WildType.WILD)); // Use CardFactory
            cards.add(CardFactory.createCard(Card.Color.WILD, -1, null, WildCard.WildType.WILD_DRAW_FOUR)); // Use CardFactory
        }
    }

    @Override
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }


}