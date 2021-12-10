
import java.util.Scanner;
import java.util.Stack;

// To run the game:
// run the main method in the Main Class
// To play a card, use it's index as the input for the scanner

public class Main {
    public static boolean game = true;
    public static int numOfPlayers = 0;

    public static void main(String args[]) {

/////////////////////////            CREATING AND SHUFFLING DECK           ////////////////////////////////////////

        Deck deck = new Deck();
        Stack<Card> discard = new Stack<>();
        discard.push(deck.deck.pop());
        deck.shuffle();

/////////////////////////            INITIALIZING MAIN GAME LOOP           ////////////////////////////////////////

        while (game) {

            Scanner scanner = new Scanner(System.in);
            int drawCards = 0;
            int specialEffect = 0;
            Player iCallReverse = null;

/////////////////////////            GETTING NUMBER OF PLAYERS FROM USER           ////////////////////////////////////////

            int input = 0;

            while (true) {
                System.out.print("Number of Players: ");
                if (scanner.hasNextInt()) {
                    numOfPlayers = scanner.nextInt();
                    if (numOfPlayers >= 2 && numOfPlayers <= 8) {
                        break;
                    } else
                        System.out.println("Number of players must be between 2 and 8.");
                }
                else {
                    System.out.println("Invalid input type! Must be a number.");
                    scanner.next();
                }
            }
/////////////////////////            INITIALIZING PLAYER ARRAY           ////////////////////////////////////////

            Player player[] = new Player[numOfPlayers];

            for (int i = 0; i < numOfPlayers; i++) {
                System.out.print("Player " + (i + 1) + "'s name: \n");
                Player ply = new Player(scanner.next());
                player[i] = ply;
                // DEAL 7 CARDS TO EACH PLAYER
                deck.deal(player[i]);
            }

            // ADDING A CARD TO THE TOP OF THE DISCARD PILE
            // Wild cards cannot be the first discarded card
            while (deck.deck.peek().number != 14 || deck.deck.peek().number == 15)
                discard.push(deck.deck.pop());

            System.out.println("\nCreating deck...\n ");

/////////////////////////            GAME ROUND           ////////////////////////////////////////

            while (!isUno(player)) {

                // Shuffle the discarded cards back into the deck if the deck is empty
                if (deck.deck.size() < 2) {
                    for (int i = 0; i < discard.size(); i++)
                        deck.deck.add(discard.get(i));
                    deck.shuffle();
                    discard.push(deck.deck.pop());
                }

/////////////////////////            CHECK FOR SPECIAL CARD EFFECTS          ////////////////////////////////////////

                for (int p = 0; p < player.length; p++) {
                    // Player taking specialEffect
                    // Draw card 	: 1
                    // Skip			: 2
                    // Reverse		: 3
                    if (specialEffect == 1) {
                        //Draw number of drawCards
                        for (int i = 0; i < drawCards; i++) {
                            player[p].add(deck.deck.pop());
                        }
                        specialEffect = 0;
                        drawCards = 0;
                    }
                    if (specialEffect == 2) {
                        System.out.println(player[p].name + " got SKIPPED!");
                        specialEffect = 0;
                        continue;
                    }
                    if (specialEffect == 3) {
                        System.out.println("Player order was Reversed!");
                        specialEffect = 0;
                        reverse(player);
                        // find the one who play reverse
                        for (int i = 0; i < player.length; i++) {
                            if (player[i] == iCallReverse) {
                                // i is the one who play reverse
                                if (i == player.length - 1)
                                    p = 0;
                                else
                                    p = i + 1;
                            }
                        }
                    }

/////////////////////////            SHOWING CURRENT PLAYERS HAND AND ASKING FOR PLAY CARD           ////////////////////////////////////////

                    System.out.println(player[p].name + "'s hand: ");
                    player[p].hand();
                    System.out.println("\n\nPlayer " + player[p].name + "'s turn: ");
                    System.out.println("What card will you play?");
                    System.out.println("Top Card: " + discard.peek());

                    input = 0;
                    Card playerDiscard = null;

                    //ask for play, determine if valid
                    while (true) {
                        if (scanner.hasNextInt()) {
                            input = scanner.nextInt();
                            if (input < player[p].size && input > -1) {
                                playerDiscard = player[p].canPlay(input);
                                break;
                            } else
                                System.out.println("Please enter a valid index.");
                            System.out.println("What card will you play:");

                        }
                        else {
                            System.out.println("Invalid input type! Must be a number.");
                            System.out.println("What card will you play:");
                            scanner.next();
                        }
                    }

                    // for easier reading
                    String playerCardColor = playerDiscard.getColor();
                    int playerCardNumber = playerDiscard.getNumber();


                    // if the discarded cards color matches the discard piles top card OR it matches the number
                    if (playerCardColor == discard.peek().getColor() || playerCardNumber == discard.peek().getNumber() || discard.peek().color.equalsIgnoreCase("wild") || playerCardColor.equalsIgnoreCase("wild")) {


                        /////////////////////////            SPECIAL CARDS           ////////////////////////////////////////
                        if (playerDiscard.getNumber() > 10 && playerDiscard.getNumber() < 16) {

                            // If current player discarded a card that requires the next player to draw
                            if (playerCardNumber == 13 || playerCardNumber == 14) {
                                drawCards = 2;
                                specialEffect = 1;
                                System.out.println("\n" + player[p].name + " added " + drawCards + " to the next players hand!!!!");
                            }

                            // If Card is Draw 4 Wild
                            else if (playerCardNumber == 15) {
                                drawCards = 4;
                                specialEffect = 1;
                                System.out.println(player[p].name + " added " + drawCards + " to the next players hand!!!!");

                            }
                            else if (playerCardNumber == 11) {
                                specialEffect = 2;

                            }
                            else if (playerCardNumber == 12) { // reverse
                                iCallReverse = player[p];
                                specialEffect = 3;
                            }
                        }

                        // PLAY THE CARD!!
                        // remove the card from the players hand
                        player[p].discard(input);
                        //add the chosen card to the discard pile
                        discard.push(playerDiscard);

                        // adds number of cards for the special card
                        // only adds if there are cards to add
                        if (drawCards != 0) {
                            for (int i = 0; i < drawCards; i++) {
                                //check if the next player is the first player in the array
                                if (p + 1 > player.length - 1)
                                    player[0].add(deck.deck.pop());
                                else
                                    player[p + 1].add(deck.deck.pop());
                            }
                            drawCards = 0;
                        }
                    }
                    else {
                        System.out.println("ATTENTION:");
                        System.out.println("That was not a valid card, drew one from the deck and added it to your hand. \n");
                        player[p].add(deck.deck.pop());
                    }
                }
            }
        }
    }

    public static boolean isUno(Player[] player) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].size == 1)
                System.out.println(player[i].name + " has UNO!!!");

            else if (player[i].size == 0) {
                System.out.println(player[i].name + " has won the game!");
                return true;
            }
        }
        return false;
    }

    public static void reverse(Player[] arrayPlayer) {
        int firstIndex = 0;
        int lastIndex = arrayPlayer.length - 1;
        while (firstIndex < lastIndex) {
            Player temp = arrayPlayer[firstIndex];
            arrayPlayer[firstIndex] = arrayPlayer[lastIndex];
            arrayPlayer[lastIndex] = temp;
            firstIndex = firstIndex + 1;
            lastIndex = lastIndex - 1;
        }
    }
}
