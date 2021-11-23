import java.util.Scanner;

public class MainGame {
private int currentPlayers;

    public static void main(String args[]){
        Deck deck = new Deck();

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

            deck.shuffle();
//            deck.print();

            System.out.println();
            System.out.println();
            System.out.println();

            System.out.println("player 1s hand");
            player[0].hand();


            System.out.println();
            System.out.println("player 2s hand");
            player[1].hand();



            System.out.println("Player 1's turn: ");
            System.out.println("Discard card:");
            System.out.println(deck.deck.pop());
        }

    }

}
