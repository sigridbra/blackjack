package blackjack;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTests {
	
	@Test
	void Ace_should_give_value_11() {
		Card card = new Card("CA");
		assertEquals(card.getValue(), 11);
	}

	@Test
	void King_should_give_value_10() {
		Card card = new Card("HK");
		assertEquals(card.getValue(), 10);
	}
	
	@Test
	void Queen_should_give_value_10() {
		Card card = new Card("SQ");
		assertEquals(card.getValue(), 10);
	}
	
	@Test
	void Jack_should_give_value_10() {
		Card card = new Card("DJ");
		assertEquals(card.getValue(), 10);
	}
	
	@Test
	void Five_should_give_value_5() {
		Card card = new Card("S5");
		assertEquals(card.getValue(), 5);
	}
	
	@Test
	void HK_should_give_same_string() {
		Card card = new Card("HK");
		assertEquals(card.toString(), "HK");
	}
	
	@Test
	void D10_should_give_same_string() {
		Card card = new Card("D10");
		assertEquals(card.toString(), "D10");
	}
}
