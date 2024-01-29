import java.util.ArrayList;

public class Player {

    private int money;
    private String name;
    private static String[] communityCards = new String[5];
    /*
    Hand is split up so that the turn, flop, and river will be more efficiently given out, and it comes together to check for hand types at the end of game.
     */
    private String[] individualCards = new String[2];
    private String[] hand = {individualCards[0], individualCards[1], communityCards[0], communityCards[1], communityCards[2], communityCards[3], communityCards[4]};
    Player next; // Delete if not useful

    public Player(String name) {
        money = 500;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        if (money - amount < 0) {
            System.out.println("You do not have enough money to bet that much.");
        }
        money -= amount;
    }

    public String getName() {
        return name;
    }

    public String[] getHand() {
        return hand;
    }

    @Override
    public String toString() {
        System.out.println("----------------");
        System.out.println("Current Cards:");
        for(int i = 0;i < hand.length;i++) {
            if (hand[i] == null) {
                break;
            }
            System.out.println(hand[i]);
        }
        return "\nMoney: $" + money + "\n----------------\n";
    }
}
