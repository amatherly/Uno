import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MainGame {
    private int currentPlayers;

    public static void main(String args[]) {
        Deck deck = new Deck();
        Stack<Card> discard = new Stack<>();

        deck.shuffle();
//        deck.print();

        boolean gameNotWon = true;
        int playersHandSize = 0;


        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Number of Players:");
            int numOfPlayers = scanner.nextInt();
//            Player player[] = new Player[numOfPlayers];

            PlayerList<Player> player = new PlayerList<>();

            for (int i = 0; i < numOfPlayers; i++) {
                System.out.println("Player " + (i + 1) + "'s name: ");
                Player ply = new Player(scanner.next());
                player.add(ply);
                deck.deal(player.get(i));
            }

            discard.push(deck.deck.pop());
            System.out.println("creating deck:");

            //TODO: there needs to be a loop here
            while (gameNotWon) {

                System.out.println();
                System.out.println();

                for (int p = 0; p < player.size(); p++) {

                    playersHandSize = player.get(p).getSize();

                    System.out.println(playersHandSize);

                    System.out.println(player.get(p).name + "'s hand");
                    player.get(p).hand();

                    System.out.println("Player " + player.get(p).name + "'s turn: ");
                    System.out.println("What card will you play?");
                    System.out.println("Top Card: " + discard.peek());


                    //ask for play, determine if valid
                    int input = scanner.nextInt();

                    Card playerDiscard = player.get(p).canPlay(input);

                    // for easier reading
                    String PlayerCardColor = playerDiscard.getColor();
                    int playerCardNumber = playerDiscard.getNumber();


                    if (discard.peek().color == "wild") {
                        // remove the card from the players hand
                        player.get(p).discard(input);

                        //add the chosen card to the discard pile
                        discard.push(playerDiscard);
                    }


                    //check for special cards
                    if (checkForSpecialCards(playerDiscard)) {

                        int drawCards = 0;

                        // If current player discarded a card that requires the next player to draw
                        if (playerCardNumber == 13 || playerCardNumber == 14)
                            drawCards = 2;
                        else if (playerCardNumber == 15)
                            drawCards = 4;
                        else
                            drawCards = 0;



                        if(playerCardNumber == 11){
                            // TODO:skip logic
                        }
                        if(playerCardNumber == 12){

                            // remove the card from the players hand
                            player.get(p).discard(input);

                            //add the chosen card to the discard pile
                            discard.push(playerDiscard);
                            player.reverse();
                            p++;
                            break;
                        }


                        // adds number of cards for the special card
                        for (int i = 0; i < drawCards; i++) {
                            //check if the next player is the first player in the array
                            if (p + 1 > player.size() - 1)
                                player.get(0).add(deck.deck.pop());
                            else
                                player.get(p+1).add(deck.deck.pop());
                        }


                        System.out.println(player.get(p).name + " added " + drawCards + "to the next players hand!!!!");
                        // remove the card from the players hand
                        player.get(p).discard(input);

                        //add the chosen card to the discard pile
                        discard.push(playerDiscard);
                    }

                    // if the discarded cards color matches the discard piles top card OR it matches the number
                    else if (PlayerCardColor == discard.peek().getColor() || playerCardNumber == discard.peek().getNumber()) {

                        // remove the card from the players hand
                        player.get(p).discard(input);

                        //add the chosen card to the discard pile
                        discard.push(playerDiscard);
                    } else {
                        System.out.println("ATTENTION:");
                        System.out.println("That was not a valid card, drew one from the deck and added it to your hand.");
                        System.out.println();
                        player.get(p).add(deck.deck.pop());
                    }

                    if (playersHandSize == 1) {
                        System.out.println(player.get(p).name + " has UNO!!!");
                    } else if (playersHandSize == 0) {
                        System.out.println(player.get(p).name + " has won the game!");
                        gameNotWon = false;
                        break;
                    }
                }
            }
        }
    }

    public static boolean checkForSpecialCards(Card playerDiscard){
        if(playerDiscard.getNumber() > 10 && playerDiscard.getNumber() < 16){
            return true;
        }
        return false;
    }
}
String SpecialCard = "[";	// Creating the special card String that will will uses to later call the "Speical card"
		
		
        // Each Special Card such as "Skip,Reverse,Draw,Wild,Wild Draw 4" will be its own String
		// However, those strings will be attached to an integer case number (10,11,12,13,14)
		// As when those cases are called that String will activate.

        switch(this.value)
        {
            default: SpecialCard = String.valueOf(this.value); 
                break;             
                
            case 10: SpecialCard = "Skip"; 
                break; // When integer 10 is called this will activite the skip 
                
            case 11: SpecialCard += "Reverse"; 
                break; // When integer 11 is called this will activite the Reverse
                
            case 12: SpecialCard += "Draw 2"; 
                break;// When integer 12 is called this will activite the draw 
          
            case 13: SpecialCard += "Draw 4"; 
            break;// When integer 12 is called this will activite the draw 
                
            case 14: SpecialCard += "Wild"; 
                break;// When integer 12 is called this will activite the wild card
           
        }
	}
}
// Logical Of How Special Cards Will Work
boolean playerTurn; // this boolean value will keep try who turn it is, its essential for the skip special card
if(Card.value)=10{ 
playerTurn=false;
switch(Card.value) {
// This is for the Card Value 10 which is a special Card (skip) for this the logical will be 
// For which ever players turn it is (we can use there string name to identify them) we will make the
// boolean value to false which will skip them and finally we will switch the Card value
//as well to give the next player a new set of card from the pile or we can use our shuffle method to do this

case 12: // Draw 2
    System.out.println("Drawing 2 cards...");
    draw(2,deck);
    break;
    // This is for the Special Case 12 which is the draw 2 scenario in this case will we will 
    // simply draw 2 from the from our preexisting deck class
    
   
    if (Card.value == 14) // Draw 4
    {
        System.out.println("Drawing 4 cards...");
        draw(4,deck);
     // This is for the Special Case 14 which is the draw 2 scenario in this case will we will 
        // simply draw 4 from the from our preexisting deck class
    }
    break;

    case 14: // this case allows the user to choice whatever card they would like for this 
    	// a scanner is needed for there input to be added 
    while // We want to keep doing this untill the users types it exactly correct
    {
        System.out.print("What color card do you want?"
        		+ "Options (Type Exactly As Seen!):\"Blue\", \"Red\", \"Green\", \"Yellow\", ");
        input = new Scanner(System.in);
    }  
    if (input.hasNext("Blue") )
        Color = "Blue";
    else if (input.hasNext("Red") )
        Color = "Red";
    else if (input.hasNext("Green") )
        Color = "Green";
    else if (input.hasNext("Yellow") )
        Color = "Yellow";

    System.out.println("You chose: " + Color);
}
}
}
}
// Then all those numbers should prompt our preexisting colors.


