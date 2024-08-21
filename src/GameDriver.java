import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter player " + i + "'s name: ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        Game game = new CustomUnoGame(players);
        game.play();
    }
}





