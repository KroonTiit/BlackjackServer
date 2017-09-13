package com.blackjack.tiit.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blackjack.tiit.player.Player;

public class Card {
	private Logger logger = LoggerFactory.getLogger(Player.class);
	private Suit suit;
	private int value;
	
	public Card() {}
	public Card(Suit suit, int value) {
		if(value >=1 && value <= 13) {
			this.setValue(value);
		} else {
			logger.error(value + " ei ole aktsepteeritav kaardi väärtus!");
			System.exit(1);
		}
		
		this.setSuit(suit);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
}
