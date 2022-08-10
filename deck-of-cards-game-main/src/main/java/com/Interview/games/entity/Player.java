package com.Interview.games.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player implements Comparable<Player> {

	private String name;
	private List<Card> cards = new ArrayList<Card>();
	private Integer totalValue = 0; // Holds total added value of all the cards each player holds

	public Player(String name) {
		this.name = name;
	}

	/**
	 *
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalValue += card.getValue();
	}

	public List<Card> getCards() {
		return cards;
	}

	public String getName() {
		return name;
	}

	public Integer getTotalValue() {
		return totalValue;
	}


	public int compareTo( Player p2) {
		return this.getTotalValue().compareTo(p2.getTotalValue());
	}

}