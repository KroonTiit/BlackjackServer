package com.blackjack.tiit.cards;

import java.util.Random;

public class Deck {
	private Card[] myCards;
	private int numCards;
	
	public Deck() {
		this(1,true);
	}
	
	public Deck(int numDeck,boolean shuffle) {
		this.numCards = numDeck * 52;
		this.myCards = new Card[this.numCards]; 
		int c=0;
		for(int deck = 0; deck < numDeck; deck++) {
			for(int suit = 0; suit < 4; suit++) {
				for(int number = 1; number <=13; number++) {
					this.myCards[c]= new Card(Suit.values()[suit],number);
					c++;
				}
			}
		}
	
		if(shuffle) {
			this.shuffle();
		}
	}
	
	public void shuffle() {
		Random rng= new Random();
		Card temp;
		int j;
		for(int i = 0; i < this.numCards; i++) {
			j=rng.nextInt(this.numCards);
			temp = this.myCards[i];
			this.myCards[i]= this.myCards[j];
			this.myCards[j]=temp;
		}
	}
	
	public Card dealNextCard() {
		Card top = this.myCards[0];
		for(int c = 1;c < this.numCards; c++) {
			this.myCards[c-1] = this.myCards[c];
		}
		this.myCards[this.numCards-1] =  null;
		this.numCards--;
		return top;
	}
	
	public String printDeck(int numToprint) {
		String string="";
		for(int c=0; c< numToprint; c++) {
			string=string +" "+ this.numCards +" "+ this.myCards[c].toString();
		}
		return string;
	}
}
