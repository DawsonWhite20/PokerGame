public class Player {

    private int money;
    private String name;
    private String[] hand = new String[7];

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

    public void displayPlayerInformation() {
        System.out.println("----------------");
        System.out.println("Current Cards:");
        for(int i = 0;i < hand.length;i++) {
            if (hand[i] == null) {
                break;
            }
            System.out.println(hand[i]);
        }
        System.out.print("\nMoney: $" + money + "\n----------------\n");
    }
}
