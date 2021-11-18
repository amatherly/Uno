//General card class with the properties of an Uno card
public class Card {
	//Cards will have a number and color
	private int number;
	private String color;
	private static String Red;
	private static String Green;
	private static String Yellow;
	private static String Blue;
	
	//Constructor for the card
	public Card(int number, String color) {
		this.number = number;
		this.color = color;
	}
	
	//If you want to view what a card is
	public String toString() {
		return "Number: "+this.number+" and Color: "+this.color;
	}
	
	//If you want to know the value of a card
	public int getNumber() {
		return number;
	}
	
	//If you want to know the color of the card
	public String getColor() {
		return color;
	}
	
	//A set method if you want to create cards.
	public void setNumber(int num) {
		//Added a check to see if the number is valid for the game.
		if (num > 14 || num < 0) {
			System.out.println("Number must be between 0 and 14! (Inclusive)");
		} else { 
			number = num;
		}
	}
	
	//A set method for the color property of the card.
	public void setColor(String col) {
		//Added a check to see if the color is valid for the game.
		if (col != Blue || col != Yellow || col != Green || col != Red) {
			System.out.println(col+" is not a valid color! Colors available are Blue, Yellow, Green, and Red. (Case sensitive)");
		} else { 
			color = col;
		}
	}
	
	public static void main(String[] args) {
		Card one = new Card(0, Blue);
		Card two = new Card(1, Yellow);
		Card three = new Card(2, Green);
		Card four = new Card(3, Red);
		
		System.out.println(two);
		
	}
	
}
