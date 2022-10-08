package com.fastforward.cardsapi.service;

import com.fastforward.cardsapi.controller.request.CardRequest;
import com.fastforward.cardsapi.exception.EntityNotFoundException;
import com.fastforward.cardsapi.model.Card;
import com.fastforward.cardsapi.repository.CardOriginRepository;
import com.fastforward.cardsapi.repository.CardRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  private final CardRepository cardRepository;

  private final CardOriginRepository cardOriginRepository;

  @Autowired
  public CardService(CardRepository cardRepository, CardOriginRepository cardOriginRepository) {
    this.cardRepository = cardRepository;
    this.cardOriginRepository = cardOriginRepository;
  }

  public Card findById(long id){
    return this.cardRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Card is ["+ id+ "] not found." ));
  }

  public Card createCard(CardRequest cardRequest){

    var cardOrigin = cardOriginRepository.findById(cardRequest.getOriginId())
        .orElseThrow(() -> new EntityNotFoundException("Card origin is ["+ cardRequest.getOriginId() + "] not found." ));

    var card = new Card();

    card.setName(cardRequest.getName());
    card.setDescription(cardRequest.getDescription());
    card.setImageUrl(cardRequest.getImageUrl());
    card.setStrength(cardRequest.getStrength());
    card.setSpeed(cardRequest.getSpeed());
    card.setIntellect(cardRequest.getIntellect());
    card.setGear(cardRequest.getGear());
    card.setSkill(cardRequest.getSkill());
    card.setOrigin(cardOrigin);

    card.setCreatedAt(LocalDateTime.now());
    card.setUpdatedAt(LocalDateTime.now());

    return cardRepository.save(card);
  }
}
