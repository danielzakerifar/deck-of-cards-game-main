package com.Interview.games.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.Interview.games.entity.Card;

/**

 */
@Component
public class CardListConvertor {

	/**
	 *
	 */
	public List<CardRepresentation> listOfResource(Map<Card, Integer> cardsCount) {
		List<CardRepresentation> cardFace = new ArrayList<>(cardsCount.size());
		for (Entry<Card, Integer> entry : cardsCount.entrySet()) {
			cardFace.add(new CardRepresentation(entry.getKey(), entry.getValue()));
		}
		return cardFace;
	}
}
