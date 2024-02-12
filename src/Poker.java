import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Poker implements HandType {
    public static void dealCards(ArrayList<Player> playerList, DeckOfCards deck) {
        for(int i = 0;i < 2;i++) {
            for(int j = 0;j < playerList.size();j++) {
                playerList.get(j).getHand()[i] = deck.getNextCard();
            }
        }
    }

    public static void organizeBets(Player bettingPlayer, int bettingAmount, Pot pot) {
        bettingPlayer.subtractMoney(bettingAmount);
        pot.addMoney(bettingAmount);
        System.out.println(bettingPlayer.getName() + " has bet $" + bettingAmount + " making the pot now $" + pot.getMoney() + ".");
        System.out.println();
    }

    public static void displayCardsAndMoney(Player requestedPlayer) {
        requestedPlayer.displayPlayerInformation();
        System.out.println();
    }

    public static void raise(Scanner scanner, int betAmount, Player requestedPlayer, Pot pot) {
        System.out.print("How much would you like to raise: ");
        int raiseAmount = scanner.nextInt();
        if(raiseAmount >= betAmount) {
            organizeBets(requestedPlayer, raiseAmount + betAmount, pot);
            betAmount += raiseAmount;
            System.out.println("The new highest bet is $" + betAmount + ".\n");
        }
        else if(raiseAmount < betAmount ) {
            System.out.println("You must raise at least $" + betAmount + ".\n");
        }
    }

    public static void fold(Player requestedPlayer, Player[] foldedPlayers, int position, ArrayList<Player> players) {
        System.out.println(requestedPlayer.getName() + " has folded.");
        foldedPlayers[position] = players.remove(position);
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeckOfCards deck = new DeckOfCards();
        Pot pot = new Pot();
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
        ArrayList<Player> players = new ArrayList<Player>(numPlayers);
        scanner.nextLine();

        // Players are given names and added to a rotation
        System.out.println("Please give a name to each player.");
        for(int i = 0;i < numPlayers;i++) {
            int currentPlayer = i + 1;
            System.out.print("Enter player " + currentPlayer + "'s name: ");
            String playerName = scanner.nextLine();
            Player newPlayer = new Player(playerName);
            players.add(newPlayer);
        }
        Player[] foldedPlayers = new Player[players.size()]; // Holds players that have folded to be added into the round after
        System.out.println();

        // Game starts here and loops for each round the player wants to play
        int timesPlayed = 0; // Used to keep track of blinds for each round
        int bigBlind = timesPlayed + 2;
        int playerChoice;
        do {
            if(players.size() > 2) {
                System.out.print(players.get(bigBlind).getName() + ", you are the Big Blind this round. How much would you like to bet: ");
                int initialBet = scanner.nextInt();
                pot.setCurrentBet(initialBet);
                organizeBets(players.get(bigBlind), pot.getCurrentBet(),pot); // Find a more efficient way to update the pot
                System.out.println();
                System.out.println(players.get(bigBlind - 1).getName() + ", you are the Small Blind this round, so you must bet half of what " + players.get(bigBlind).getName() + " bet.");
                organizeBets(players.get(bigBlind - 1), pot.getCurrentBet() / 2, pot);
            }

            System.out.println();
            deck.shuffleDeck();
            dealCards(players, deck);

            if (bigBlind + 1 >= players.size()) {
                bigBlind = -1;
            }
            for(int i = bigBlind + 1;i != 1;i++) { // Figure out how to do rotation after coding actions; first round actions for loop
                while (true) {
                    try {
                        System.out.println(players.get(i).getName() + ", it is your turn. What would you like to do?\n----------------"); // Make into method using boolean to add check option for subsequent rounds
                        System.out.println("""
                                (1) Display hand and money
                                (2) Call
                                (3) Raise
                                (4) Fold"""); // First round actions
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

                switch (playerChoice) {
                    case 1 -> displayCardsAndMoney(players.get(i));
                    case 2 -> organizeBets(players.get(i), pot.getCurrentBet(), pot);
                    case 3 -> raise(scanner, pot.getCurrentBet(), players.get(i), pot);
                    case 4 -> {
                        fold(players.get(i), foldedPlayers, i, players);
                        i--;
                    }
                }
                if (i + 1 >= players.size()) { // Loops back to the beginning of the ArrayList; doesn't work in the case of players folding
                    i = -1;
                }
            }

            Player.getCommunityCards()[0] = deck.getNextCard();
            Player.getCommunityCards()[1] = deck.getNextCard();
            Player.getCommunityCards()[2] = deck.getNextCard();
            System.out.println("The flop cards are: " + Player.getCommunityCards()[0] + ", " + Player.getCommunityCards()[1] + ", " + Player.getCommunityCards()[2] + ".\n");
            for(int j = 0;j < 4;j++) {
                for(int k = 0;k < players.size();k++) {
                    while (true) {
                        try {
                            System.out.println(players.get(k).getName() + ", it is your turn. What would you like to do?\n----------------");
                            System.out.println("""
                                (1) Display hand and money
                                (2) Call
                                (3) Raise
                                (4) Fold
                                (5) Check"""); // First round actions
                            playerChoice = scanner.nextInt();
                            if (playerChoice < 1 || playerChoice > 5) {
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

                    switch (playerChoice) {
                        case 1 -> displayCardsAndMoney(players.get(k));
                        case 2 -> organizeBets(players.get(k), pot.getCurrentBet(), pot);
                        case 3 -> raise(scanner, pot.getCurrentBet(), players.get(k), pot);
                        case 4 -> fold(players.get(k), foldedPlayers, k, players);
                        case 5 -> {
                            System.out.println(players.get(k).getName() + " has checked.");
                            System.out.println();
                        }
                    }
                }
                // Checks if the turn or river card should be given out
                if (j == 0) {
                    Player.getCommunityCards()[3] = deck.getNextCard();
                    System.out.println("The turn card is " + Player.getCommunityCards()[3] + ".\n");
                }
                else if (j == 1) {
                    Player.getCommunityCards()[4] = deck.getNextCard();
                    System.out.println("The river card is " + Player.getCommunityCards()[4] + ".\n");
                }
            }
            playerChoice = 6; // Ends loop immediately for testing purposes
        } while (playerChoice != 6);
    }
}