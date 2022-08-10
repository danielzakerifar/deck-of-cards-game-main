package com.Interview.games.service;

import java.util.List;
import java.util.Map;

import com.Interview.games.entity.Card;
import com.Interview.games.entity.Deck;
import com.Interview.games.entity.Game;
import com.Interview.games.entity.Player;
import com.Interview.games.entity.Suit;
import com.Interview.games.resource.CardRepresentation;

/**
 *
 */
public interface GameService {

	public Game createGame(Game game);

	public Deck createDeck();
	
	public void addDeckToGame(Game game, Deck deck);

	public boolean removeGame(Long gameId);

	public Game findGame(Long id);

	public Deck findDeck(Long id);

	public Player getPlayer(Game game, String playerLogin);

	public Player addPlayer(Game game, String playerLogin);

	public void removePlayer(Game game, String playerLogin);

	public Card dealCard(Game game, String playerLogin);
	
	public void shuffleDeck(Game game);

	public List<Player> getPlayersSortedByTotalValue(Game game);

	public Map<Suit, Integer> getUndealtCardsCountBySuit(Game game);

	public List<CardRepresentation> getUndealtCardsCountBySuitAndValue(Game game);
}
