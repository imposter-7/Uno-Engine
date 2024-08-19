import java.util.ArrayList;
import java.util.List;

public class GameDriver {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        // Add players (can be obtained from user input)
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        Game game = new CustomUnoGame(players);
        game.play();
    }
}