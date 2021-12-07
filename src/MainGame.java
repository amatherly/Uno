import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    /**
     * @param args
     */
	
	public static boolean game = true;
	public static int numOfPlayers = 0;
	
    public static void main(String args[]) {
        Deck deck = new Deck();
        Stack<Card> discard = new Stack<>();

        deck.shuffle();

        while (game) {

            Scanner scanner = new Scanner(System.in);
            int numOfPlayers = 0;
//////////////////
            int input = 0;
            boolean isNumber;
            while(true) {
            	System.out.print("Number of Players: ");
                if (scanner.hasNextInt()) {
                	numOfPlayers = scanner.nextInt();
                    if (numOfPlayers >= 2 && numOfPlayers <= 8) {
                        input = numOfPlayers;
                        break;
                    } else {
                        System.out.println("Number of players must be between 2 and 8.");
                    }

                } else {
                    System.out.println("Invalid input type! Must be a number.");
                    isNumber = false;
                    scanner.next();
                }
            } 
////////////////
            Player player[] = new Player[numOfPlayers];


            for (int i = 0; i < numOfPlayers; i++) {
                System.out.print("Player " + (i + 1) + "'s name: ");
                Player ply = new Player(scanner.next());
                player[i] = ply;
                deck.deal(player[i]);
            }

            Player[] temp = new Player[player.length];

            for (int i = 0; i < player.length; i++) {
                temp[i] = player[i];
            }


            discard.push(deck.deck.pop());
            System.out.println("");
            System.out.println("Creating deck... ");
            System.out.println("");

          
            while (!isUno(player)) {

                for (int p = 0; p < player.length; p++) {

                   // System.out.println(playersHandSize);

                    System.out.println(player[p].name + "'s hand");
                    System.out.println("");
                    player[p].hand();

                    System.out.println("");
                    System.out.println("");
                    System.out.println("Player " + player[p].name + "'s turn: ");
                    System.out.println("What card will you play?");
                    System.out.println("Top Card: " + discard.peek());

                    input = 0;
                    //ask for play, determine if valid
                    while (true) {
                        try {
                            input = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid Index.");
                        }
                    }
                    
                    Card playerDiscard = player[p].canPlay(input);

                    // for easier reading
                    String PlayerCardColor = playerDiscard.getColor();
                    int playerCardNumber = playerDiscard.getNumber();


                    //check for special cards
                    if (checkForSpecialCards(playerDiscard)) {

                        int drawCards = 0;

                        // If current player discarded a card that requires the next player to draw
                        if (playerCardNumber == 13 || playerCardNumber == 14) {
                        	//TODO: check for color
                            drawCards = 2;
                         // remove the card from the players hand
                            player[p].discard(input);

                            //add the chosen card to the discard pile
                            discard.push(playerDiscard);
                            
                            System.out.println(player[p].name + " added " + drawCards + " to the next players hand!!!!");
                        } else if (playerCardNumber == 15) {
                            drawCards = 4;
                         // remove the card from the players hand
                            player[p].discard(input);

                            //add the chosen card to the discard pile
                            discard.push(playerDiscard);
                            
                            System.out.println(player[p].name + " added " + drawCards + " to the next players hand!!!!");
                        } 
                        
                     
                        // adds number of cards for the special card
                        for (int i = 0; i < drawCards; i++) {
                            //check if the next player is the first player in the array
                            if (p + 1 > player.length - 1)
                                player[0].add(deck.deck.pop());
                            else
                                player[p + 1].add(deck.deck.pop());
                        }
                    }

                    // if the discarded cards color matches the discard piles top card OR it matches the number
                    else if (PlayerCardColor == discard.peek().getColor() || playerCardNumber == discard.peek().getNumber()) {

                        // remove the card from the players hand
                        player[p].discard(input);

                        //add the chosen card to the discard pile
                        discard.push(playerDiscard);
                    } else {
                        System.out.println("ATTENTION:");
                        System.out.println("That was not a valid card, drew one from the deck and added it to your hand.");
                        System.out.println();
                        player[p].add(deck.deck.pop());
                    }
                }
            }
        }
    }

    public static void checkForSpecialCards(Card playerDiscard) {
       
    	switch(playerDiscard.getNumber()) {
    	case 11: // some skip method
      
    	case 12: // some reverse method
    		
    	case 13: // draw 2 color
    		
    	case 14: // draw 2 wild
    		
    	case 15: // draw 4 wild
    	
    	}
    }
    
    public static boolean isUno(Player[] player) {
    	for(int i=0; i<player.length; i++) {
    		if (player[i].size == 1) {
                System.out.println(player[i].name + " has UNO!!!");
            } else if (player[i].size == 0) {
                System.out.println(player[i].name + " has won the game!");
                return true;
            }
    	}
    	return false;
    }
    
    public static boolean canMove(Card discard, Player player, Card topCard) {
    	if(discard.getColor() != topCard.getColor() || discard.getNumber() != topCard.getNumber() && discard.getNumber() != 14 && discard.getNumber() != 15) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public static void skip(Player player, Card discard, int index) {
 
    	
    	System.out.println(player.name + " played a skip card! " + player.name + " 's turn was skipped.");
    	// remove the card from the players hand
    	player.discard(index);

    	//add the chosen card to the discard pile
    	discard.push(discard);
                
    	if(p+1 > numOfPlayers) {
    		p = 0;
    	} 
    	p++;
    }
           
    }
    
    
}
