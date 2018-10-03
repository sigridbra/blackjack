package blackjack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game implements Runnable {
	
	ArrayList<Card> sam;
	ArrayList<Card> dealer;
	ArrayList<Card> deck; 
	Scanner scanner;
	
	
	public Game() {
		this.sam  = new ArrayList<Card>();
		this.dealer = new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		
		}
	
	public void buildDeck(boolean askUser) {
		if(askUser) {
			this.scanner = new Scanner(System.in); 	
			String fileUrl = this.ask("Please provide an url to a file containing a deck of cards. \nPress enter to generate a new deck of cards");
			
			while(!fileUrl.isEmpty()) {
				try {
					FileReader fileReader = new FileReader(fileUrl);
					Scanner fileScanner = new Scanner(fileReader);
					String line = fileScanner.nextLine();//Assumes correct format of card file
					this.setDeck( cardStringToArray(line));
					this.scanner.close();
					fileScanner.close();
					return;
				} catch (FileNotFoundException e) {
					fileUrl = this.ask("File url invalid. Please try again or press enter to generate cards.");
					}
				}
		}
		
		this.scanner.close();
		this.setDeck(generateNewDeck());
	}
	
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck; 
	}
	
	public String ask(String message) {
		System.out.println(message);
		
		return this.scanner.nextLine();
	}
	
	public static ArrayList<Card> generateNewDeck(){
		System.out.println("Generates a new deck of shuffled cards.");
		
		final String deck = "SA, S2, S3, S4, S5, S6, S7, S8, S9, S10, SJ, SQ, SK, HA, H2, H3, H4, H5, H6, H7, H8, H9, H10, HJ, HQ, HK, CA, C2, C3, C4, C5, C6, C7, C8, C9, C10, CJ, CQ, CK, DA, D2, D3, D4, D5, D6, D7, D8, D9, D10, DJ, DQ, DK";
		ArrayList<Card> newDeck = cardStringToArray(deck);
		Collections.shuffle(newDeck);
		return newDeck;
	}
	
	public static ArrayList<Card> cardStringToArray(String cardString){
		ArrayList<Card> newDeck = new ArrayList<Card>();
		String[] cards = cardString.split(", ");
		for(int i=0; i < cards.length; i++) {
			newDeck.add(new Card(cards[i]));
		}
		return newDeck;
	}
	
	public int calculateValue(ArrayList<Card> deck) {
		int sum = 0;
		for(Card c: deck) {
			sum += c.getValue();
		}
		return sum;
	}
	
	public int checkForWinner(boolean isInitial) {
		//Checks if there is a winner and who it is, 1 for Sam and 2 for dealer. 0 if none. 
		int valueOfSam = this.calculateValue(this.sam);
		int valueOfDealer = this.calculateValue(this.dealer);
		
		if(valueOfSam < 21) {
			if(valueOfDealer < 21 ) {
				if(isInitial) {
					return 0;
				}else if(valueOfDealer < valueOfSam) {
					return 1;
				}else {
					return 2;
				}
			}else if(valueOfDealer > 21) {
				return 1;
			}else {
				return 2;
			}
		}else if(valueOfSam == 21) { 
			//Assumes Sam always wins if he gets 21 
			return 1;
		}else {
			//Dealer wins if both have over 21
			return 2; 
		}
	}
	
	private void printWinner(int winner) {
		if( winner == 1) {
			System.out.println("sam");
		}else if(winner == 2){
			System.out.println("dealer");
		}
		System.out.println("sam: " + sam.toString().replace("[", "").replace("]", ""));
		System.out.println("dealer: " + dealer.toString().replace("[", "").replace("]", ""));
		
	}
	
	public void init() {
		
		//Hands out the initial two cards to each player
		this.sam.add(this.deck.remove(0));
		this.dealer.add(this.deck.remove(0));
		this.sam.add(this.deck.remove(0));
		this.dealer.add(this.deck.remove(0));
		
	}
	
	public void handOutMoreCards() {
		while(this.calculateValue(sam) < 17) {
			this.sam.add(this.deck.remove(0));
		}
		
		while(this.calculateValue(dealer) <= this.calculateValue(sam)) {
			this.dealer.add(this.deck.remove(0));
		}
	}
	
	public void run() {
		this.buildDeck(true);
		this.init();
		this.handOutMoreCards();
		//Check if anyone won with the intial cards
		int winner = this.checkForWinner(true);
		if(winner > 0) {
			this.printWinner(winner);
			return;
		}
		
		winner = this.checkForWinner(false);
		this.printWinner(winner);
		return;

	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

}
