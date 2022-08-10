package com.Interview.games.cach;

import com.Interview.games.entity.Deck;
import com.Interview.games.entity.Game;

/**
 *
 *
 */
public interface GameRepository {

	public Game createGame(Game game);

	public Deck createDeck();

	public boolean removeGame(Game game);

	public Game findGame(Long id);

	public Deck findDeck(Long id);
}
