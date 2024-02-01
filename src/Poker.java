import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Poker implements HandType {
    public static void dealCards(Player[] playerList, DeckOfCards deck) {
        for(int i = 0;i < 2;i++) {
            for(int j = 0;j < playerList.length;j++) {
                playerList[j].getHand()[i] = deck.getNextCard();
            }
        }
    }

    public static int organizeBets(Player bettingPlayer, int bettingAmount, int pot) {
        bettingPlayer.subtractMoney(bettingAmount);
        pot += bettingAmount;
        System.out.println(bettingPlayer.getName() + " has bet $" + bettingAmount + " making the pot now $" + pot + ".");
        return pot;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();
        int numPlayers = 0;

        System.out.println("Welcome to your Poker Game!\n");
        // Asks user for number of players
        while (true) {
            try {
                System.out.print("How many players are playing? (minimum of 2; maximum of 10): ");
                numPlayers = scanner.nextInt();
                if (numPlayers < 2 || numPlayers > 10) {
                    System.out.println("Player count must be between 2 and 10 players.");
                    continue;
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a number to indicate how many people are playing.");
                System.out.println();
                scanner.nextLine();
            }
        }
        System.out.println();
        Player[] players = new Player[numPlayers];
        scanner.nextLine();

        // Players are given names and added to a rotation
        System.out.println("Please give a name to each player.");
        for(int i = 0;i < numPlayers;i++) {
            int currentPlayer = i + 1;
            System.out.print("Enter player " + currentPlayer + "'s name: ");
            String playerName = scanner.nextLine();
            Player newPlayer = new Player(playerName);
            players[i] = newPlayer;
        }
        Player[] foldedPlayers = new Player[players.length]; // Holds players that have folded to be added into the round after
        System.out.println();

        // Game starts here and loops for each round the player wants to play
        int timesPlayed = 0; // Used to keep track of blinds for each round
        int bigBlind = timesPlayed + 2;
        int pot = 0;
        int playerChoice;
        int betAmount = 0;
        int numTimesRaised = 0; // Used to keep track of what the minimum raise is
        do {
            if(players.length > 2) {
                System.out.print(players[bigBlind].getName() + ", you are the Big Blind this round. How much would you like to bet: ");
                betAmount = scanner.nextInt();
                pot = organizeBets(players[bigBlind], betAmount, pot); // Find a more efficient way to update the pot
                System.out.println();
                System.out.println(players[bigBlind - 1].getName() + ", you are the Small Blind this round, so you must bet half of what " + players[bigBlind].getName() + " bet.");
                pot = organizeBets(players[bigBlind - 1], betAmount / 2, pot);
            }

            System.out.println();
            deck.shuffleDeck();
            dealCards(players, deck);

            if (bigBlind + 1 >= players.length) {
                bigBlind = -1;
            }
            for(int i = bigBlind + 1;i < players.length;i++) { // Figure out how to do rotation after coding actions
                while (true) {
                    try {
                        System.out.println(players[i].getName() + ", it is your turn. What would you like to do?\n----------------");
                        System.out.println("""
                                (1) Display hand and money
                                (2) Call
                                (3) Raise
                                (4) Fold"""); // First round actions
                        scanner.nextLine();
                        playerChoice = scanner.nextInt();
                        if (playerChoice < 1 || playerChoice > 4) {
                            System.out.println();
                            System.out.println("That is not a valid choice.\n");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println();
                        System.out.println("Please select a number corresponding with the action you want to perform.\n");
                    }
                }
                System.out.println();
                
                switch(playerChoice) {
                    case 1:
                        players[i].displayPlayerInformation();
                        break;
                    case 2:
                        pot = organizeBets(players[i], betAmount, pot);
                        break;
                    case 3:
                        System.out.print("How much would you like to raise: ");
                        int raiseAmount = scanner.nextInt();
                        if(raiseAmount >= betAmount) {
                            pot = organizeBets(players[i], raiseAmount + betAmount, pot);
                            numTimesRaised++;
                            betAmount += raiseAmount;
                            System.out.println("The new highest bet is $" + betAmount + ".\n");
                        }
                        else if(raiseAmount < betAmount ) {
                            System.out.println("You must raise at least $" + betAmount + ".\n");
                        }
                        break;
                    case 4:
                }
            }
            playerChoice = 5; // Ends loop immediately for testing purposes
        } while (playerChoice != 5);

    }
}