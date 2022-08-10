package com.Interview.games.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Interview.games.cach.GameRepository;

import com.Interview.games.entity.Card;
import com.Interview.games.entity.Deck;
import com.Interview.games.entity.Game;
import com.Interview.games.entity.Player;
import com.Interview.games.entity.Suit;
import com.Interview.games.resource.CardListConvertor;
import com.Interview.games.resource.CardRepresentation;

/**
 *
 */
@Component
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository repository;
	@Autowired
	private CardListConvertor cardListAssembler;

	@Override
	public Game createGame(Game game) {
		return repository.createGame(game);
	}

	@Override
	public Deck createDeck() {
		return repository.createDeck();
	}



	@Override
	public boolean removeGame(Long gameId) {
		Game game = findGame(gameId);
		return repository.removeGame(game);
	}

	@Override
	public Game findGame(Long id) {
		Game game = repository.findGame(id);
		if (game == null)
			throw new RuntimeException();
		return game;
	}

	@Override
	public void addDeckToGame(Game game, Deck deck) {
		game.addDeck(deck);
	}

	@Override
	public Deck findDeck(Long id) {
		Deck deck = repository.findDeck(id);
		if (deck == null)
			throw new RuntimeException();
		return deck;
	}

	@Override
	public Player getPlayer(Game game, String playerLogin) {
		Player player = game.getPlayer(playerLogin);
		if (player == null)
			throw new RuntimeException();
		return player;
	}

	@Override
	public Player addPlayer(Game game, String playerLogin) {
		Player player = game.getPlayer(playerLogin);
		if (player != null)
			throw new RuntimeException();
		return game.addPlayer(playerLogin);
	}

	@Override
	public void removePlayer(Game game, String playerLogin) {
		Player player = game.getPlayer(playerLogin);
		game.removePlayer(player);
	}

	@Override
	public Card dealCard(Game game, String playerLogin) {
		Card dealtCard = game.dealCard(playerLogin);
		if (dealtCard == null)
			throw new RuntimeException();
		return dealtCard;
	}

	@Override
	public void shuffleDeck(Game game) {
		game.shuffleDeck();
	}

	@Override
	public List<Player> getPlayersSortedByTotalValue(Game game) {
		return game.getPlayersSortedByTotalValue();
	}

	@Override
	public Map<Suit, Integer> getUndealtCardsCountBySuit(Game game) {
		return game.getUndealtCardsCountBySuit();
	}

	@Override
	public List<CardRepresentation> getUndealtCardsCountBySuitAndValue(Game game) {
		Map<Card, Integer> cardsCount = game.getUndealtCardsCountBySuitAndValue();
		List<CardRepresentation> cardList = cardListAssembler.listOfResource(cardsCount);
		return cardList;
	}
}
