import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomUnoGame extends Game {
    private Player winner;

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public CustomUnoGame(List<Player> players) {
        super(players, new StandardUnoRules()); // Using standard rules
    }

    @Override
    public void play() {
        initializeGame();
        boolean allowDraw = true;


        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            notifyPlayerTurn(currentPlayer);
            currentPlayer.printHand();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose a card to play (or -1 to draw): ");
            int cardIndex = scanner.nextInt();

            List<Card> playerHand = currentPlayer.getHand();

            // Check for valid card index and move
            boolean isValidCardIndex = cardIndex < playerHand.size() && cardIndex >= 0;
            boolean nonValidMove = isValidCardIndex && !gameRules.isValidMove(playerHand.get(cardIndex), discardPile.getTopCard());
            boolean nonValidDraw = cardIndex == -1 && !allowDraw;

            if (nonValidDraw || nonValidMove){
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (cardIndex == -1) {
                //Handle drawing a card
                allowDraw = false;
                currentPlayer.drawCard(deck);
                notifyCardDrawn(currentPlayer);
                Card drawnCard = playerHand.get(playerHand.size() - 1);

                if (gameRules.isValidMove(drawnCard, discardPile.getTopCard()))
                    continue;

                System.out.println("No valid cards are drawn.");

            } else {
                // Handle playing a card
                Card cardToPlay = playerHand.get(cardIndex);
                currentPlayer.playCard(cardToPlay, discardPile);
                notifyCardPlayed(currentPlayer,cardToPlay);

                // Handle action cards
                if (cardToPlay instanceof ActionCard actionCard) {
                    switch (actionCard.getAction()) {
                        case REVERSE:
                            reversed = !reversed;
                            System.out.println("Game direction reversed!");
                            break;
                        case SKIP:
                            nextPlayer();
                            System.out.println("Next player skipped!");
                            break;
                        case DRAW_TWO:
                            nextPlayer();
                            Player nextPlayer = players.get(currentPlayerIndex);
                            for (int i = 0; i < 2; i++) {
                                nextPlayer.drawCard(deck);
                            }
                            System.out.println("Next player draws 2 cards!");
                            break;
                    }
                }

                else if (cardToPlay instanceof WildCard wildCard) {
                    switch (wildCard.getWildType()) {
                        case WILD:
                            break;
                        case WILD_DRAW_FOUR:
                            nextPlayer();
                            Player nextPlayer = players.get(currentPlayerIndex);
                            for (int i = 0; i < 4; i++) {
                                nextPlayer.drawCard(deck);
                            }

                            System.out.println("Next player draws 4 cards!");
                            break;
                    }
                }
            }

            if (isGameOver(currentPlayer)){
                setWinner(currentPlayer);
                break;
            }

            allowDraw = true;
            nextPlayer();
        }

        notifyGameOver(winner);
    }
}