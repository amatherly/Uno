import java.util.ArrayList;

public class Player {
    // each player will have a "hands" of an ArrayList that contain Card class object
    private ArrayList<Card> player;
    public String name;
    private int size;



    // create a player with empty ArrayList of Card objects
    public Player(String name) {
        this.player = new ArrayList<Card>();
        this.name = name;
    }

    /**
     * add a card into the "hands"
     *
     * @param card
     */
    public void add(Card card) {
        size++;
        this.player.add(card);
    }

    /**
     * play a card out of the "hands"
     * this is zero-based for now
     *
     * @param i
     */
    public Card canPlay(int i) {
        return this.player.get(i);
    }

    public void discard(int i) {
        size--;
        this.player.remove(i);
    }


    /**
     * This will show all the card in a player hand
     */
    public void hand() {
        System.out.print("[");
        String special = "";
        for (int i = 0; i < player.size(); i++) {

            if (player.get(i).number >= 11) {
                if (player.get(i).number == 11) {
                    special = "Skip";
                } else if (player.get(i).number == 12) {
                    special = "Reverse";
                } else if (player.get(i).number == 13) {
                    special = "Draw 2";
                } else if (player.get(i).number == 14) {
                    special = "Draw 2";
                } else if (player.get(i).number == 15) {
                    special = "Draw 4";
                }
            }
            else
                special = "";

            if (i == player.size() - 1) {
                System.out.print(player.get(i) + " " + special);
            } else {
                System.out.print(player.get(i) + " " + special + ", ");
            }

        }
        System.out.print("]");
    }

    public int getSize() {
        return size;
    }


}
