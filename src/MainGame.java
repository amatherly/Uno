import java.util.Scanner;
import java.util.Stack;

public class MainGame {
private int currentPlayers;

    public static void main(String args[]){
        Deck deck = new Deck();
        Stack<Card> discard = new Stack<>();
        deck.shuffle();


        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Number of Players:");
            int numOfPlayers = scanner.nextInt();
            Player player[] = new Player[numOfPlayers];

            for (int i = 0; i < numOfPlayers; i++) {
                System.out.println("Player " + (i+1) + "'s name: ");
                player[i] = new Player(scanner.next());
                deck.deal(player[i]);
            }



            System.out.println("creating deck:");

//            deck.print();

            System.out.println();
            System.out.println();
            System.out.println();

            //TODO: there needs to be a loop here

            System.out.println("player 1s hand");
            player[0].hand();


            System.out.println();
            System.out.println("player 2s hand");
            player[1].hand();



            System.out.println("Player 1's turn: ");
            System.out.println("Discard card:");
            System.out.println("What card will you play?");


            int input = scanner.nextInt();

            //TODO: loop through each player
            Card discardCard = player[0].play(input);
            if(discardCard.getColor() == discard.getColor());
            discard.push(discardCard);
            System.out.println(discardCard);
            //it has to be the same number OR the same color

            //change player
            // player i plays a card
            // loop until Player[i].size == 1
            // if they can play it they win



        }
        }

    }


