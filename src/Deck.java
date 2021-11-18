
import java.util.*;


// ASh
public class Deck {

    Stack<Card> deck = new Stack<>();

    public Deck () {
        String color = "";

        /*108 cards as follows
19 Blue cards – 0-9
19 Green cards – 0-9
19 Red cards – 0-9
19 Yellow cards – 0-9
8 Draw Two cards – 2 each in blue, green, red, and yellow
8 Reverse cards – 2 each in blue, green, red, and yellow
8 Skip cards – 2 each in blue, green, red, and yellow
4 Wild cards
4 Wild Draw Four cards */

        // BLUE CARDS
        color = "blue";
        for (int i = 0; i < 14; i++) {
            Card card = new Card(i, color);
            deck.add(card);
        }

        color = "green";
        for (int i = 0; i < 14; i++) {
            Card card = new Card(i, color);
            deck.add(card);
        }

        color = "yellow";
        for (int i = 0; i < 14; i++) {
            Card card = new Card(i, color);
            deck.add(card);
        }

        color = "red";
        for (int i = 0; i < 14; i++) {
            Card card = new Card(i, color);
            deck.add(card);
        }

        for (int i = 0; i < 4; i++) {
            Card card = new Card(14, null);
        }
        for (int i = 0; i < 4; i++) {
            Card card = new Card(15, null);
        }


    }

    public void shuffle (){
        // built in method!
        Collections.shuffle(deck);
    }

    public void deal(Player player){

        for (int i = 0; i < 8; i++) {


        }
        // pop 7 cards off the top
        // for(player : MainGame)?
    }

    public void print() {

        for (Card card : deck) {
                 System.out.println(card.toString());
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.print();
    }
}
