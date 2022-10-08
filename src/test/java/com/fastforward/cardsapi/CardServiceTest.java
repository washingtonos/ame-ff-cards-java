package com.fastforward.cardsapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fastforward.cardsapi.controller.request.CardRequest;
import com.fastforward.cardsapi.exception.EntityNotFoundException;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.repository.CardOriginRepository;
import com.fastforward.cardsapi.repository.CardRepository;
import com.fastforward.cardsapi.service.CardService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CardServiceTest {

  @Mock
  CardOriginRepository cardOriginRepository;

  @Mock
  CardRepository cardRepository;

  @InjectMocks
  CardService cardService;

  @BeforeEach
  void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Retornar erro ao criar card sem origin")
  void shouldReturnErrorOnCreateCardWhenOriginNotFound(){

    when(cardOriginRepository.findById(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> cardService.createCard(new CardRequest()));
    verify(cardOriginRepository, never()).save(any());
  }


  @Test
  @DisplayName("Deve criar o card")
  void shouldSaveCardOnCreate(){
    var origin = new CardOrigin();
    origin.setId(1L);

    when(cardOriginRepository.findById(any())).thenReturn(Optional.of(origin));
    when(cardRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    var cardRequest = new CardRequest();
    cardRequest.setName("Iron Man");
    cardRequest.setDescription("Tony Stark");

    var card = cardService.createCard(cardRequest);

    assertEquals(card.getName(), cardRequest.getName());
    assertEquals(card.getDescription(), cardRequest.getDescription());

  }
}
