package com.blackjack.tiit.game;

import com.blackjack.tiit.cards.Card;
import com.blackjack.tiit.player.Player;

public class Hand {
	private Player player;
	private Card[] hand= new Card[10];;
	private int numCards;
	private int handSum;
	
	public Hand() {}
	
	public Hand(Player player) {
		this.emptyHand(player);
	}

	public void emptyHand(Player player) {
		this.player=player;
		for(int c=0; c < 10; c++) {
			this.hand[c]=null;
		}
		this.numCards=0;
	}
	public boolean addCard(Card aCard) {
		this.hand[this.numCards]=aCard;
		this.numCards++;
		return true;
	}
	public void setHandSum() {
		int handSum=0;
		int cardNum;
		int numAces = 0;
		for(int c=0; c < this.numCards; c++) {
			cardNum=this.hand[c].getValue();
			if(cardNum == 1) {
				numAces++;
				handSum += 11;
			} else if(cardNum > 10) {
				handSum += 10;
			} else {
				handSum += cardNum;
			}
		}
		while (handSum > 21 && numAces > 0) {
			handSum -= 10;
			numAces--;
		}
		this.handSum=handSum;
	}
	public void printHand(boolean showFirstCard) {
		System.out.printf("%s kaardid:\n", this.player.getName());
		for(int c = 0; c < this.numCards; c++) {
			if(c == 0 && !showFirstCard){
				System.out.println(" [peidetud]");
			} else {
				System.out.printf(" %s\n", this.hand[c].toString());
			}
		}
	}
	public int getHandSum() {
		return handSum;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getNumCards() {
		return numCards;
	}

	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}
}

