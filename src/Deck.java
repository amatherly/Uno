import java.util.HashSet;
import java.util.Set;

public class Deck implements{

    Set<Card> deck = new HashSet<Card>();


    public Deck () {
       // will populate deck with each card
        for (int i = 0; i < 108; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i, j);
                deck.add(card);
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
