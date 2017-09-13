package com.blackjack.tiit.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Player")
public class Player {
	@Id
	@GeneratedValue
	private	long id;
	@Column(nullable = false)
	private int cash;
	@Column(nullable = false)
	private String name;
	private Integer bet;
	
	
	public Player() {}
	
	public Player(String name, int cash) {
		this.name = name;	
		if(cash >= 0) {
			this.cash=cash;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public long getId() {
		return id;
	}

	public Integer getBet() {
		return bet;
	}

	public void setBet(Integer bet) {
		this.bet = bet;
	}
}
