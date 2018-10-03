package blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
	private String value;
	private String suit; 
	
	public Card(String cardValue) {
		if(cardValue.length() > 3) {
			System.out.println("Wrong format of card value");
		}//Assumes meaningful values (2-10, J, O, K or A) 
		this.value = cardValue.substring(1);
		this.suit = cardValue.substring(0, 1);
		
	}
	public int getValue() {
		ArrayList<String> specials = new ArrayList<String> (Arrays.asList("A", "J", "Q", "K"));
		if(specials.contains(this.value) ) {
			if(specials.indexOf(this.value) == 0) {
				return 11;
			}
			return 10;
		}
		return Integer.valueOf(value);
	}
	
	public String toString() {
		return this.suit + this.value;
	}

}

