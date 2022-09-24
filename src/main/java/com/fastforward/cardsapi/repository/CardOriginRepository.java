package com.fastforward.cardsapi.repository;

import com.fastforward.cardsapi.model.CardOrigin;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CardOriginRepository {

  Optional<CardOrigin> findById(int id);

  CardOrigin save(CardOrigin cardOrigin);
}
