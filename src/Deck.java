import java.util.HashMap;

public class Deck {

    HashMap<Integer, Integer> deck = new HashMap(108);


    public Deck (Card card) {

            for (int i = 0; i < 108; i++) {

                deck.put(i, card.data);
            }

        //constructor
        //populate a list of all the cards in the deck with the Card class
    }

    public void shuffle(){
        // take the list of cards in the deck

    }

    public void deal(Player player){
        // pop 7 cards off the top
        // for(player : MainGame)?

    }

    public void draw (int numberOfCards, Player player){
        // pop one card off the deck

    }
}
