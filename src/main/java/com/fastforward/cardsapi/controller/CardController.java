package com.fastforward.cardsapi.controller;

import com.fastforward.cardsapi.controller.request.CardRequest;
import com.fastforward.cardsapi.model.Card;
import com.fastforward.cardsapi.service.CardService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
public class CardController {

  private final CardService cardService;

  private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

  @Autowired
  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  @GetMapping("{id}")
  public Card findCardById(@PathVariable("id") long id){
    LOG.info("Buscando card com id {}", id);
    return cardService.findById(id);
  }

  @GetMapping
  public List<Card> listCards(){
  LOG.info("Buscando todas as cartas");
  return cardService.listAll();
  }

  @PostMapping
  public Card createCard(@RequestBody CardRequest cardRequest){
    LOG.info("Criando card: [{}]", cardRequest);
    return cardService.createCard(cardRequest);
  }

  @PutMapping("{id}")
  public Card updateCard(@PathVariable("id") long id, @RequestBody CardRequest cardRequest){
    LOG.info("Atualizando carta com id [{}]", id);
    return cardService.update(id, cardRequest);
  }

  @DeleteMapping("{id}")
  public void deleteCard(@PathVariable("id") long id){
    LOG.info("Deletando carta com id [{}]", id);
    cardService.delete(id);
  }
}