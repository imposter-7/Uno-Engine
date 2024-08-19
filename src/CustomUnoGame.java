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
        boolean allowDraw = true;

        while (true) {
            printGameState();
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.printHand();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose a card to play (or -1 to draw): ");
            int cardIndex = scanner.nextInt();

            List<Card> playerHand = currentPlayer.getHand();

            boolean isValidCardIndex = cardIndex < playerHand.size() && cardIndex >= 0;
            boolean nonValidMove = isValidCardIndex && !gameRules.isValidMove(playerHand.get(cardIndex), discardPile.getTopCard());
            boolean nonValidDraw = cardIndex == -1 && !allowDraw;

            if (nonValidDraw || nonValidMove){
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (cardIndex == -1) {
                allowDraw = false;
                currentPlayer.drawCard(deck);
                Card drawnCard = playerHand.get(playerHand.size() - 1);

                if (gameRules.isValidMove(drawnCard, discardPile.getTopCard()))
                    continue;

            } else {
                Card cardToPlay = playerHand.get(cardIndex);
                currentPlayer.playCard(cardToPlay, discardPile);

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
                            nextPlayer.drawCard(deck);
                            nextPlayer.drawCard(deck);
                            System.out.println("Next player draws two cards!");
                            break;
                    }
                }
            }

            if (isGameOver()) break;
            allowDraw = true;

            nextPlayer();
        }

        System.out.println("Game Over! " + players.get(currentPlayerIndex).getName() + " wins!");
    }
}