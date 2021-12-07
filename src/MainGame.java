import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MainGame {
	private int currentPlayers;
	private  int drawCards = 0;

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

			while (gameNotWon) {

				int drawCards = 0;
				int specialEffect = 0;

				for (int p = 0; p < player.size(); p++) {

					//PHASE 1: Damage
					// Draw card 	: 1                	
					// Skip 		: 2
					if(specialEffect == 1) {
						//Draw number of drawCards
						for(int i = 0; i < drawCards; i++) {
							player.get(p).add(deck.deck.pop());
						}               		
						specialEffect = 0;
					}
					if(specialEffect == 2) {
						System.out.println("You got SKIP!");                     	
						specialEffect = 0;
						continue;
					}



					// PHASE 2: player play
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


						// If current player discarded a card that requires the next player to draw
						if (playerCardNumber == 13 || playerCardNumber == 14) {
							drawCards = 2;
							specialEffect = 1;
							// remove the card from the players hand
							player.get(p).discard(input);

							//add the chosen card to the discard pile
							discard.push(playerDiscard);
							System.out.println(player.get(p).name + " added " + drawCards + "to the next players hand!!!!");
						}
						else if (playerCardNumber == 15) {
							drawCards = 4;
							specialEffect = 1;
							// remove the card from the players hand
							player.get(p).discard(input);

							//add the chosen card to the discard pile
							discard.push(playerDiscard);
							System.out.println(player.get(p).name + " added " + drawCards + "to the next players hand!!!!");
						}
						else {
							drawCards = 0;
						}


						if(playerCardNumber == 11){
							// remove the card from the players hand
							player.get(p).discard(input);

							//add the chosen card to the discard pile
							discard.push(playerDiscard);
							specialEffect = 2;
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


//                        // adds number of cards for the special card
//                        for (int i = 0; i < drawCards; i++) {
//                            //check if the next player is the first player in the array
//                            if (p + 1 > player.size() - 1)
//                                player.get(0).add(deck.deck.pop());
//                            else
//                                player.get(p+1).add(deck.deck.pop());
//                        }
//
//
//                        System.out.println(player.get(p).name + " added " + drawCards + "to the next players hand!!!!");
//                        // remove the card from the players hand
//                        player.get(p).discard(input);
//
//                        //add the chosen card to the discard pile
//                        discard.push(playerDiscard);
						
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

