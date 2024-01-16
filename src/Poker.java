import java.util.Scanner;

public class Poker implements HandType {
    public static void dealCards(Player[] playerList, DeckOfCards deck) {
        for(int i = 0;i < 2;i++) {
            for(int j = 0;j < playerList.length;j++) {
                playerList[j].getHand().add(deck.getNextCard());
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();
        int playerChoice = 0;

        // Research and add try/catch blocks
        // Asks user for number of players
        System.out.print("Welcome to your Poker game!\n\nHow many players do you have: ");
        int numPlayers = scanner.nextInt();
        System.out.println();
        Player[] players = new Player[numPlayers];
        scanner.nextLine();

        // Player enters user names
        System.out.println("Please give a name to each player.");
        for(int i = 0;i < numPlayers;i++) {
            int currentPlayer = i + 1;
            System.out.print("Enter player " + currentPlayer + "'s name: ");
            String playerName = scanner.nextLine();
            Player newPlayer = new Player(playerName);
            players[i] = newPlayer;
        }
        System.out.println();

        // Game starts here and loops for each round the player wants to play
        do{
            // Make method to shuffle deck next
            dealCards(players, deck);
            playerChoice = 5; // Ends loop immediately for testing purposes
        }while (playerChoice != 5);

    }
}