package com.fastforward.cardsapi.service;

import com.fastforward.cardsapi.controller.request.CardRequest;
import com.fastforward.cardsapi.model.Card;
import com.fastforward.cardsapi.repository.CardRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  private final CardRepository cardRepository;

  @Autowired
  public CardService(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  public Optional<Card> findById(int id){
    return this.cardRepository.findByID(id);
  }

  public Card createCard(CardRequest cardRequest){

    var card = new Card();

    card.setName(cardRequest.getName());
    card.setDescription(cardRequest.getDescription());
    card.setImageUrl(cardRequest.getImageUrl());

    card.setStrength(cardRequest.getStrength());
    card.setSpeed(cardRequest.getSpeed());
    card.setIntellect(cardRequest.getIntellect());
    card.setGear(cardRequest.getGear());
    card.setSkill(cardRequest.getSkill());

    card.setCreatedAt(LocalDateTime.now());
    card.setUpdatedAt(LocalDateTime.now());

    return cardRepository.save(card);
  }
}
