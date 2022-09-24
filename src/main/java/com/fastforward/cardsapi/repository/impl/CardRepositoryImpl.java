package com.fastforward.cardsapi.repository.impl;

import com.fastforward.cardsapi.exception.InvalidEntityAttributeException;
import com.fastforward.cardsapi.model.Card;
import com.fastforward.cardsapi.repository.CardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepositoryImpl implements CardRepository {

  private final List<Card> cards = new ArrayList<>();

  @Override
  public Optional<Card> findByID(int id) {
    return cards.stream().filter(card -> card.getId() == id).findFirst();
  }

  @Override
  public Card save(Card card) {
    var cardFound = cards.stream()
        .filter(storedCard -> storedCard.getName().equals(card.getName()))
        .findFirst();

    if(cardFound.isPresent()){
      throw new InvalidEntityAttributeException("Name [" + card.getName() + "] already stored");
    }

    card.setId(cards.size()+1);
    cards.add(card);

    return card;
  }
}