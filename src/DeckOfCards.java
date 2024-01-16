import java.util.ArrayList;

public class DeckOfCards {

    private ArrayList<String> deck = new ArrayList<String>();

    public DeckOfCards() {
        String[] suite = new String[] {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] value = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for(int i = 0;i < 4;i++) {
            for(int j = 0;j < 13;j++) {
                String newCard = value[j] + " of " + suite[i];
                deck.add(newCard);
            }
        }
    }

    public String getNextCard() {
        String nextCard = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return nextCard;
    }
}
