package com.Interview.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


/**


 */
//@Entity
public class Deck  {

	public static final int DECK_SIZE = 52;


	private Long id;

	private CopyOnWriteArrayList<Card> cards = new CopyOnWriteArrayList<Card>();

	private AtomicInteger dealtCards = new AtomicInteger(0);

	private Map<Suit, AtomicInteger> dealtCardsBySuit = new ConcurrentHashMap<Suit, AtomicInteger>();


	public Long getId() {
		return id;
	}

	/**
	 *
	 */
	public Deck(Long id) {
		this.id = id;
		init();
	}

	/*
	 *
	 */
	private void init() {
        for (Suit suit : Suit.values()) {
            for (int value = Card.ACE; value <= Card.KING; value++) {
                cards.add(new Card(suit, value, this.id));
            }
            dealtCardsBySuit.put(suit, new AtomicInteger(0));
        }
	}

	/**
	 *
	 */
	public Card dealCard(Player player) {
		Card card = null;
		if (dealtCards.get() < DECK_SIZE) {
			card = cards.get(dealtCards.getAndIncrement());
			if (card != null) {
				player.addCard(card);
				AtomicInteger cardsBySuit = dealtCardsBySuit.get(card.getSuit());
				cardsBySuit.getAndIncrement();
			}
		}
		return card;
	}

	/**
	 *
	 * 
	 *
	 */
	@JsonIgnore
	public List<Card> getAvailableCards() {
		List<Card> availableCards = new ArrayList<>();
		for (int i = dealtCards.get(); i < Deck.DECK_SIZE; i++) {
			Card card = cards.get(i);
			availableCards.add(card);
		}
		return availableCards;
	}
	
	/**
	 *
	 */
	public void returnCard(Card card) {
		dealtCards.getAndDecrement();
		AtomicInteger cardsBySuit = dealtCardsBySuit.get(card.getSuit());
		cardsBySuit.getAndDecrement();
	}

	/**
	 *
	 */
	public void shuffle() {
		Random random = new Random();
		int dealtCards = this.dealtCards.get();
		for (int i = dealtCards; i < Deck.DECK_SIZE; i++) {
	   	int randomNumber = dealtCards + random.nextInt((Deck.DECK_SIZE - dealtCards));
            Card tempCard = cards.get(i);
            cards.set(i, cards.get(randomNumber));
            cards.set(randomNumber, tempCard);
        }
	}

	/**
	 *
	 */
	public Map<Suit, Integer> getDealtCardsBySuit() {
		Map<Suit, Integer> dealtCardsCount = new HashMap<Suit, Integer>();
		for (Entry<Suit, AtomicInteger> entry : dealtCardsBySuit.entrySet()) {
			dealtCardsCount.put(entry.getKey(), entry.getValue().get());
		}
		return dealtCardsCount;
	}


	public void setId(Long id) {
		this.id = id;
	}
}
