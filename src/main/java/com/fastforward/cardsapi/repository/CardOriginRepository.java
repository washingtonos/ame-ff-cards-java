package com.fastforward.cardsapi.repository;

import com.fastforward.cardsapi.model.CardOrigin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardOriginRepository extends JpaRepository<CardOrigin, Long> {

}
