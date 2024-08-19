import java.util.ArrayList;
import java.util.List;


// Game.java (Abstract Class)
public abstract class Game {
    protected List<Player> players;
    protected Deck deck;
    protected DiscardPile discardPile;
    protected GameRules gameRules;
    protected int currentPlayerIndex;
    protected boolean reversed;

    public Game(List<Player> players, GameRules gameRules) {
        this.players = players;
        this.deck = Deck.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.gameRules = gameRules;
        this.currentPlayerIndex = 0;
        this.reversed = false;
    }

    public abstract void play();

    protected void initializeGame() {
        deck.shuffle();
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
        discardPile.addCard(deck.drawCard());
    }

    protected void nextPlayer() {
        if (reversed) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    protected boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    protected void printGameState() {
        System.out.println("-------------------------------------");
        System.out.println("Top Card: " + discardPile.getTopCard());
        System.out.println("Current Player: " + players.get(currentPlayerIndex).getName());
        System.out.println("-------------------------------------");
    }
}