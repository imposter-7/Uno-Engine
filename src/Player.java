import java.util.ArrayList;
import java.util.List;

// Player.java
public class Player implements GameObserver{
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        if (card != null) {
            hand.add(card);
        }
    }

    public void playCard(Card card, DiscardPile discardPile) {
        hand.remove(card);
        discardPile.addCard(card);
    }

    public void printHand() {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }

    @Override
    public void onCardPlayed(Player player, Card card) {
        System.out.println(player.getName() + " played " + card.toString());
    }

    @Override
    public void onPlayerTurn(Player player, Card topCard) {
        if (player == this) {
            System.out.println("-------------------------------");
            System.out.println("It's your turn, " + name + "!");
            System.out.println("Top Card: " + topCard);
        }
    }

    @Override
    public void onGameOver(Player winner) {
        System.out.println("You won, " + name + "!");
    }

    @Override
    public void onCardDrawn(Player player) {
        if (player == this) {
            System.out.println(player.getName()+" drew a card.");
        } else {
            System.out.println(player.getName() + " drew a card.");
        }
    }
}