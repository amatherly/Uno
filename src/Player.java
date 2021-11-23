import java.util.ArrayList;

public class Player {
	// each player will have a "hands" of an ArrayList that contain Card class object
	private ArrayList<Card> player;
	public String name;



	// create a player with empty ArrayList of Card objects
	public Player(String name) {
		this.player = new ArrayList<Card>();
		this.name = name;
	}

	/**
	 *  add a card into the "hands"
	 *  
	 * @param card
	 */
	public void add(Card card) {
		this.player.add(card);
	}
	
	/**
	 *  play a card out of the "hands"
	 *  this is zero-based for now
	 *  
	 * @param i
	 */
	public void play(int i) {
		this.player.remove(i);
	}
	
	
	/**
	 * This will show all the card in a player hand
	 */
	public void hand() {
		System.out.print("[");
		for (int i = 0; i < player.size(); i++) {
			if(i == player.size() - 1) {
				System.out.print(player.get(i));
			}
			else {
				System.out.print(player.get(i) + ", ");
			}
		}
		System.out.print("]");
	}
}
