package com.Interview.games.cach;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Interview.games.entity.Deck;
import com.Interview.games.entity.Game;

/**
 *
 */
@Repository
public class GameArchiveImpl extends MemoryArchive<Game> implements GameRepository {

	@Autowired
	private Dynamo deckDynamo;

	private Map<Long, Deck> decks = new ConcurrentHashMap<Long, Deck>();
	
	@Override
	public Game createGame(Game game) {
		return save(game);
	}

	@Override
	public Deck createDeck() {
		Deck deck = new Deck(deckDynamo.getCoolId());
		decks.put(deck.getId(), deck);
		return deck;
	}

	@Override
	public boolean removeGame(Game game) {
		return remove(game.getId());
	}

	@Override
	public Game findGame(Long id) {
		Game game = findById(id);
		return game;
	}

	@Override
	public Deck findDeck(Long id) {
		return decks.get(id);
	}
}
