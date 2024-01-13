import java.util.Scanner;

public class Game implements DeckTypes{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Research and add try/catch blocks
        System.out.println("Welcome to your Poker game!\n\nHow many players do you have?");
        int numPlayers = scanner.nextInt();

        System.out.println("Please give a name to each player.");
        for(int i = 0;i < numPlayers;i++) {
            System.out.println("Enter player " + i+1+ "'s name: ");
        }
    }
}