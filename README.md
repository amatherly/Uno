# Uno The Calculators 
Team Project Plan - UNO

We are recreating Uno with the original rules from the classic card game. The project will consist of 4 main Classes or parts that will work with each other. One will control the deck of 108 Uno cards. The Card Class will control the cards in the deck and keep track of the color and number of each card. The Player Class will essentially be each player's “hand” which will be shown to the player on their turn. The final part is the main game, which will include the logic and rules of Uno. The first player to play their last card wins the round. The remaining players will tally up their points and they will be given to the player who won the round. The first player to reach 500 pts wins the entire game!!



RULES 
Each player starts with 7 cards
The rest goes into the draw pile
empty discard pile

The first player will flip a card from the draw pile to the discard pile (we should do this automatically
at the start of each round)

If that card is a wild card, it must be put into the MIDDLE of the draw pile and another card needs to be
drawn

The next player's turn:
- Must play either a :
	-matching color
	-matching number
-matching word (reverse, skip, draw 2)/ matching number associated with that special card
	-wild card

- If the player doesn't have a playable card:
	- they draw one card
		- if playable, play the card
		- else next player's turn

- Play until someone plays all of their cards


Scoring
At the end of each round, the winner will get:
- face value of every other players number cards sum
- 20 pts for each Draw 2/ Reverse/ Skip cards
- 50 pts for any kind of Wild Cards

First person to reach 500 pts wins

Player Class - Minh
Player class will create and keep track of the “hand” of each player
Each player’s card object will be stored in an array
Player plays by add and remove card from hand
Player’s cards will be show at the beginning of each player’s turn


Deck Class - Ash

Needs to implement Player Class and Card Class

Will need (at least):
.draw(int numberOfCards, Player player)
Moves number of cards to Player

.shuffle(Deck deck)
Shuffles either entire deck or discard pile

.deal(int number of players)
Will deal 7 cards to each Player object

Will need to store Card objects in a Stack of 108 Cards.
Will need to check if there are enough cards in the deck left to draw, and if not, shuffle the discard pile (second Deck object in Main Class)
This will be expanded upon once we start programming and find out what we need.

Deck and Cards:

- Deck has 108 cards total
-19 Blue
-19 Yellow
-19 Red
-19 Green
- 8 Skip
- 8 Reverse
- 8 Draw Two
- 4 Wild
- 4 Wild Draw 4

Card Class: Ben M.

The card class will contain various properties of a card object, such as type (determined by number), color(determined by a color parameter, and maybe even how the cards interact with each other based on the need to implement that here or elsewhere. That way when “new Card” is called we can enter the parameters we want such as the number and color (9, Blue). Since the cards only go from 0-9 we can set the special cards to the numbers 10-14, that way we don’t have to create something else entirely. The card class itself will be basic since each individual card will have it’s own function and properties within the game. Cards will be a single object but can be stored in arrays or stacks for the player's hand or the deck.


Section 3: Implementation Plan
The way we plan to collaborate as a group is to all independently work on a singular part of the project and then eventually come together as one and make a singular large scale code that will incorporate all our separate methods.
Main Frame Of The Game- Linus K
Linus- “I will be responsible for creating the beginning mainframe to the game and design all the needed sections that will be required in order to make the game work. My goal throughout the week is to have this finished and present to the groups thus we are able to begin incorporating and remodeling the code. the data structures that i'm planning to utilize will be arrays and possible stacks in order to allow the users to continue drawing from the deck of cards when needed.” 
