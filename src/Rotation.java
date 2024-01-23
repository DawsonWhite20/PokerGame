public class Rotation {
// Circular Linked List used for the turn-based part of the game
    private Player head;
    private Player tail;

    public void add(Player newPlayer) {
        if(head == null) {
            head = newPlayer;
            tail = newPlayer;
        }
        else {
            tail.next = newPlayer;
            newPlayer.next = head;
            tail = newPlayer;
        }
    }
}
