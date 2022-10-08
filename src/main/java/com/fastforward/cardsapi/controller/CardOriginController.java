package com.fastforward.cardsapi.controller;

import com.fastforward.cardsapi.controller.request.CardOriginRequest;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.service.CardOriginService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

  private static final Logger LOG = LoggerFactory.getLogger(CardOriginController.class);

  @Autowired
  private CardOriginService cardOriginService;

  @GetMapping("{id}")
  public CardOrigin findCardOriginById(@PathVariable("id") long id){
    LOG.info("Buscando card origin com id [{}]", id);
    return cardOriginService.findById(id);
  }

  @PostMapping
  public CardOrigin createCardOrigin(@RequestBody CardOriginRequest cardOriginRequest){
    return cardOriginService.createCardOrigin(cardOriginRequest);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping()
  public List<CardOrigin> findAllCardOrigins(){
    LOG.info("Buscando todas as origens");
    return cardOriginService.listAll();
  }

  @PutMapping("{id}")
  public CardOrigin updateCardOrigin(@PathVariable("id") long id,
                                     @RequestBody CardOriginRequest cardOriginRequest){
    LOG.info("Atualizando card origin com id {}", id);
    return cardOriginService.update(id, cardOriginRequest);
  }

  @DeleteMapping("{id}")
  public void deleteCardOrigin(@PathVariable("id") long id){
    LOG.info("Deletando cardOrigin com id [{}]", id);
    cardOriginService.delete(id);
  }
}
