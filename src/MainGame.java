public class MainGame {
  private int currentPlayers;
	private String[] PlayerNames;
	
	private UnoDeck deck;// For deck class that needs to be implemented
	private ArrayList<ArrayList<UnoCard>>CardsPlayer;
	private ArrayList<UnoCard>CardPile;
	
	private UnoCard.Color Color;
	private UnoCard.Value Value;
	
	boolean cardDirection;
	
	public Game(String[]names)
	{
		deck=new UnoDeck();
		deck.random();
		CardPile= new ArrayList<UnoCard>();
		PlayerNames=names;
		currentPlayers=0;
		cardDirection=false;
		
	}
}
}
