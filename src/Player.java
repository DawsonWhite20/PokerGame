import java.util.ArrayList;

public class Player {

    private int money;
    private String name;
    private static String[] communityCards = new String[5];
    private ArrayList<String> hand = new ArrayList<String>();

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
        money -= amount;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getHand() {
        return hand;
    }
}
