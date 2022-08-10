package com.Interview.games.resource;

import java.io.Serializable;

import com.Interview.games.entity.Card;
import com.Interview.games.entity.Suit;

/**
 *
 *
 */
public class CardRepresentation implements Serializable {



	private Suit suit;
	private String faceValue;
	private int count;

	/**

	 */
	public CardRepresentation(Card card, int count) {
		super();
		this.suit = card.getSuit();
		this.faceValue = toFaceValue(card.getValue());
		this.count = count;
	}

	/*
	 *
	 */
	private String toFaceValue(int value) {
		String cardFace = null;
		if (value >= 2 && value <= 10) {
			cardFace = String.valueOf(value);
		}
		else if (value == 1) {
			cardFace = "Ace";
		}
		else if (value == 11) {
			cardFace =  "Jack";
		}
		else if (value == 12) {
			cardFace =  "Queen";
		}
		else if (value == 13) {
			cardFace = "King";
		}

        return cardFace;
	}




}
