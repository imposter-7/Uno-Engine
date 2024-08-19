import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomUnoGame extends Game {
    public CustomUnoGame(List<Player> players) {
        super(players, new StandardUnoRules()); // Using standard rules
    }

    @Override
    public void play() {
        initializeGame();


        while (!isGameOver()) {
            printGameState();
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.printHand();

            // Get player input (replace with your actual input mechanism)
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose a card to play (or -1 to draw): ");
            int cardIndex = scanner.nextInt();

            if (cardIndex == -1) {
                currentPlayer.drawCard(deck);
            } else {
                Card cardToPlay = currentPlayer.getHand().get(cardIndex);
                if (gameRules.isValidMove(cardToPlay, discardPile.getTopCard())) {
                    currentPlayer.playCard(cardToPlay, discardPile);

                    // Handle action cards
                    if (cardToPlay instanceof ActionCard) {
                        ActionCard actionCard = (ActionCard) cardToPlay;
                        switch (actionCard.getAction()) {
                            case REVERSE:
                                reversed = !reversed;
                                System.out.println("Game direction reversed!");
                                break;
                            case SKIP:
                                nextPlayer(); // Skip the next player's turn
                                System.out.println("Next player skipped!");
                                break;
                            case DRAW_TWO:
                                nextPlayer();
                                Player nextPlayer = players.get(currentPlayerIndex);
                                nextPlayer.drawCard(deck);
                                nextPlayer.drawCard(deck);
                                System.out.println("Next player draws two cards!");
                                break;
                        }
                    }

                } else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            }

            nextPlayer();
        }

        System.out.println("Game Over! " + players.get(currentPlayerIndex).getName() + " wins!");
    }
}