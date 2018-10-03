package blackjack;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GameTests {
	


	@Test
	void ace_five_and_two_should_give_18() {
		Game game = new Game();
		String s = "CA, D5, H2";
		assertEquals(game.calculateValue(Game.cardStringToArray(s)), 18);
	}
	
	@Test
	void generateNewDeck_should_give_size_52() {
		assertEquals(Game.generateNewDeck().size(), 52);
	}
	
	@Test
	void none_should_win() {
		Game game = new Game();
		game.setDeck(Game.cardStringToArray("CA, D5, H9, HQ, S8"));
		game.init();
		assertEquals(game.checkForWinner(true), 0);
	}
	
	@Test
	void sam_should_win() {
		Game game = new Game();
		game.setDeck(Game.cardStringToArray("CA, D5, H9, HQ, S8"));
		game.init();
		game.handOutMoreCards();
		assertEquals(game.checkForWinner(false), 1);
	}
	
	
	
	
	

}
