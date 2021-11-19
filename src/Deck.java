
import java.util.*;


// ASh
public class Deck {

    Stack<Card> deck = new Stack<>();

    public Deck () {
        String[] colors = {"blue", "red", "green", "yellow"};
        // Numbered Color Cards
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 14; j++) {
                Card card = new Card(j, colors[i]);
                deck.add(card);
            }
        }
        // Wild Cards
        for (int i = 14; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i, null);
                deck.add(card);
            }
        }


    }

    public void shuffle (){
        // built in method!
        Collections.shuffle(deck);
    }

    public void deal(Player player){

        for (int i = 0; i < 8; i++) {

            // will add 7 cards to each player with .pop
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
