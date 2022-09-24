package com.fastforward.cardsapi.repository;

import com.fastforward.cardsapi.model.Card;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository {

  Optional<Card> findByID(int id);

  Card save(Card card);
}
