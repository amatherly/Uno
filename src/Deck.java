
import java.util.List;
import java.util.Map;
import java.util.Stack;


// ASh
public class Deck {

    Card[] deck = new Card[108];


    public Deck () {
       // will populate deck with each card
        for (int i = 0; i < 108; i++) {
            for (int j = 0; j < 20; j++) {
                deck[j] = new Card(j, "blue");
            }
        }

        /*
        Deck has 108 cards total
        -19 Blue
        -19 Yellow
        -19 Red
        -19 Green
        8 Skip
        8 Reverse
        8 Draw Two
        4 Wild
        4 Wild Draw 4
        */

        //constructor
        //populate a list of all the cards in the deck with the Card class
    }

    public void deal(Player player){
        // pop 7 cards off the top
        // for(player : MainGame)?

    }

    public void draw (int numberOfCards, Player player){
        // pop one card off the deck

    }

    public static void main(String[] args) {
        Deck deck = new Deck();
    }
}
