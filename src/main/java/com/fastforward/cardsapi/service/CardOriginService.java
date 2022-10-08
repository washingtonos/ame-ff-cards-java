package com.fastforward.cardsapi.service;

import com.fastforward.cardsapi.controller.request.CardOriginRequest;
import com.fastforward.cardsapi.exception.EntityNotFoundException;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.repository.CardOriginRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardOriginService {

  private final CardOriginRepository cardOriginRepository;

  @Autowired
  public CardOriginService(CardOriginRepository cardOriginRepository) {
    this.cardOriginRepository = cardOriginRepository;
  }

  public CardOrigin findById(long id){
    return this.cardOriginRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Card origin id ["+id+"] not found"));
  }

  public List<CardOrigin> listAll(){
  return this.cardOriginRepository.findAll();
  }

  public CardOrigin createCardOrigin(CardOriginRequest cardOriginRequest){
    var cardOrigin = new CardOrigin();

    cardOrigin.setName(cardOriginRequest.getName());
    cardOrigin.setDescription(cardOriginRequest.getDescription());
    cardOrigin.setCreator(cardOriginRequest.getCreator());

    cardOrigin.setCreatedAt(LocalDateTime.now());
    cardOrigin.setUpdatedAt(LocalDateTime.now());

    return cardOriginRepository.save(cardOrigin);
  }

  public CardOrigin update(long id, CardOriginRequest cardOriginRequest){
    var cardOrigin = this.findById(id);

    cardOrigin.setName(cardOriginRequest.getName());
    cardOrigin.setDescription(cardOriginRequest.getDescription());
    cardOrigin.setCreator(cardOriginRequest.getCreator());
    cardOrigin.setUpdatedAt(LocalDateTime.now());

    return cardOriginRepository.save(cardOrigin);
  }

  public void delete(long id){
    var cardOrigin = this.findById(id);
    cardOriginRepository.delete(cardOrigin);
  }
}
