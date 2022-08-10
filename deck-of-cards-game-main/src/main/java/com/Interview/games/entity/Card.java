package com.Interview.games.entity;

import java.io.Serializable;
import java.util.Comparator;


/**
 *
 */
public class Card implements  Comparable<Card> {




	public final static int ACE = 1;
	public final static int JACK = 11; 
	public final static int QUEEN = 12;
	public final static int KING = 13;

	private Suit suit;
	private int value;

	private Long deckId;

	/**
	 *
	 */
	public Card(Suit suit, int value, Long deckId) {
		this.suit = suit;
		this.value = value;
		this.deckId = deckId;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}
	
	public Long getDeckId() {
		return deckId;
	}

	@Override
	public int hashCode() {
		final int num = 17;
		int total = 1;
		total = num * total + ((suit == null) ? 0 : suit.hashCode());
		total = num * total + value;
		return total;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;
		else {
			Card otherCard = (Card) otherObject;
			return (suit.equals(otherCard.suit) &&
					value == otherCard.value);
		}
	}
	@Override
	public int compareTo(Card c) {
			return Comparator.comparing(Card::getSuit)
				.thenComparing(Comparator.comparingInt(Card::getValue).reversed())
	            .compare(this, c);
	}
}
