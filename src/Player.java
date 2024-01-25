import java.util.ArrayList;

public class Player {

    private int money;
    private String name;
    private static String[] communityCards = new String[5];
    private String[] hand = new String[7];
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
}
