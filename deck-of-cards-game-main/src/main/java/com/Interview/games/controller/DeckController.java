package com.Interview.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Interview.games.entity.Deck;
import com.Interview.games.service.GameService;

/**
 *
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/decks", produces = "application/json")
public class DeckController {

	@Autowired
	private GameService service;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Deck> createGame() {
		Deck newDeck = service.createDeck();
		return new ResponseEntity<>(newDeck, HttpStatus.CREATED);
	}

}
