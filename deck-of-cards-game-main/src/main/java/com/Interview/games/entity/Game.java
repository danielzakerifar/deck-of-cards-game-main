package com.Interview.games.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.Interview.games.cach.Trackable;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 */
public class Game implements Trackable {



	private Long id;
	private Map<String, Player> players = new ConcurrentHashMap<String, Player>();
	private Map<Long, Deck> decks = new ConcurrentHashMap<Long, Deck>();

	/**
	 *
	 */
	public Player addPlayer(String playerLogin) {
		Player newPlayer = new Player(playerLogin);
		players.put(playerLogin, newPlayer);
		return newPlayer;
	}

	/**
	*
	 *
	 */
	public void removePlayer(Player player) {
		// When a player is removed from a game, all their cards should be returned to the game deck.
		for (Card card : player.getCards()) {
			Deck cardDeck = decks.get(card.getDeckId());
			cardDeck.returnCard(card);
		}
		players.remove(player.getName()); // Removes a player from the game players list.
	}

	/**
	 *
	 */
	public Player getPlayer(String playerLogin) {
		Player player = players.get(playerLogin);
		return player;
	}

	/**
	 *
	 *
	 */
	public Card dealCard(String playerLogin) {
		Card dealtCard = null;
		Player player = players.get(playerLogin);
		for (Deck deck : decks.values()) { // Iterate over game decks.
			dealtCard = deck.dealCard(player);
			if (dealtCard != null)
				break;
		}
		return dealtCard;
	}

	/**
	 *
	 * 
	 *
	 */
	public void addDeck(Deck deck) {
		decks.put(deck.getId(), deck);
	}

	/**
	 *
	 */
	@JsonIgnore
	public List<Player> getPlayersSortedByTotalValue() {
		List<Player> listPlayers = new ArrayList<Player>(players.values());

		Collections.sort(listPlayers, new PlayerComparator()); 
		return listPlayers;
	}

	/**
	 *
	 * 
	 *
	 */
	@JsonIgnore
	public Map<Suit, Integer> getUndealtCardsCountBySuit() {
		Map<Suit, Integer> totalSuitCount = new HashMap<>();
		for (Suit suit : Suit.values()) {
			totalSuitCount.put(suit, 0);
		}

		for (Deck deck : decks.values()) { // Iterate over game decks.
			Map<Suit, Integer> deckSuitCount = deck.getDealtCardsBySuit(); // Get current count of dealt cards by suit for selected deck.
			for (Entry<Suit, Integer> suitCount : deckSuitCount.entrySet()) {
				Integer currVal = totalSuitCount.get(suitCount.getKey());
				currVal += Deck.DECK_SIZE - suitCount.getValue(); // Subtract deck size by the count of dealt cards to obtain undealt count.
				totalSuitCount.put(suitCount.getKey(), currVal); // Update count in the result map.
			}
		}
		return totalSuitCount;
	}

	/**
	 *
	 * 
	 *
	 */
	@JsonIgnore
	public Map<Card, Integer> getUndealtCardsCountBySuitAndValue() {
		Map<Card, Integer> totalCardCount = new TreeMap<>();


		for (Deck deck : decks.values()) {
			List<Card> availableCards = deck.getAvailableCards();
			for (Card card : availableCards) {
				Integer currCount = totalCardCount.get(card);
				if (currCount == null) {
					currCount = 0;
				}

				currCount += 1;
				totalCardCount.put(card, currCount);
			}
		}
		return totalCardCount;
	}

	/**
	 *
	 */
	public void shuffleDeck() {
		for (Deck deck : decks.values()) {
			deck.shuffle();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
