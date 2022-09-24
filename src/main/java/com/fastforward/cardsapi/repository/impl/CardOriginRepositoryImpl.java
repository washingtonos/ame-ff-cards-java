package com.fastforward.cardsapi.repository.impl;

import com.fastforward.cardsapi.exception.InvalidEntityAttributeException;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.repository.CardOriginRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CardOriginRepositoryImpl implements CardOriginRepository {

  private final List<CardOrigin> cardOrigins = new ArrayList<>();

  @Override
  public Optional<CardOrigin> findById(int id) {
    return cardOrigins.stream()
        .filter(cardOrigin -> cardOrigin.getId() == id)
        .findFirst();
  }

  @Override
  public CardOrigin save(CardOrigin cardOrigin) {
    var cardOriginFound = cardOrigins.stream()
        .filter(storedOrigin -> storedOrigin.getName().equals(cardOrigin.getName()))
        .findFirst();

    if(cardOriginFound.isPresent()){
      throw new InvalidEntityAttributeException("Card Origin name [" + cardOrigin.getName() +"] already stored");
    }

    cardOrigin.setId(cardOrigins.size()+1);
    cardOrigins.add(cardOrigin);

    return cardOrigin;
  }
}
