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
    private final List<GameObserver> observers = new ArrayList<>();

    public Game(List<Player> players, GameRules gameRules) {
        this.players = players;
        this.deck = Deck.getInstance();
        this.discardPile = DiscardPile.getInstance();
        this.gameRules = gameRules;
        this.currentPlayerIndex = 0;
        this.reversed = false;

        for (Player player : players) {
            registerObserver(player);
        }
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

    protected boolean isGameOver( Player player) {
        return player.getHand().isEmpty();
    }

    protected void printGameState() {
        System.out.println("-------------------------------------");
        System.out.println("Top Card: " + discardPile.getTopCard());
        System.out.println("Current Player: " + players.get(currentPlayerIndex).getName());
        System.out.println("-------------------------------------");
    }


    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(GameObserver observer) {
        observers.remove(observer);
    }

    protected void notifyCardPlayed(Player player, Card card) {
        for (GameObserver observer : observers) {
            observer.onCardPlayed(player, card);
        }
    }

    protected void notifyPlayerTurn(Player player) {
        Card topCard = discardPile.getTopCard();
        for (GameObserver observer : observers) {
            observer.onPlayerTurn(player, topCard);
        }

    }

    protected void notifyGameOver(Player winner) {
        for (GameObserver observer : observers) {
            observer.onGameOver(winner);
        }
    }

    protected void notifyCardDrawn(Player player) {
        for (GameObserver observer : observers) {
            observer.onCardDrawn(player);
        }
    }

}