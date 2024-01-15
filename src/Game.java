import java.util.Scanner;

public class Game implements Hand {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Research and add try/catch blocks
        // Asks user for number of players
        System.out.print("Welcome to your Poker game!\n\nHow many players do you have: ");
        int numPlayers = scanner.nextInt();
        System.out.println();
        String[] players = new String[numPlayers];
        scanner.nextLine();

        // Player enters user names
        System.out.println("Please give a name to each player.");
        for(int i = 0;i < numPlayers;i++) {
            int currentPlayer = i + 1;
            System.out.print("Enter player " + currentPlayer + "'s name: ");
            String playerName = scanner.nextLine();
            players[i] = playerName;
        }
        System.out.println();


    }
}