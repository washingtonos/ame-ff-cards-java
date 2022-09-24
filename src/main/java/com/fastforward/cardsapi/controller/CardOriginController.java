package com.fastforward.cardsapi.controller;

import com.fastforward.cardsapi.controller.request.CardOriginRequest;
import com.fastforward.cardsapi.model.CardOrigin;
import com.fastforward.cardsapi.service.CardOriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

  private static final Logger LOG = LoggerFactory.getLogger(CardOriginController.class);

  @Autowired
  private CardOriginService cardOriginService;

  @GetMapping("{id}")
  public CardOrigin findCardOriginById(@PathVariable("id") int id){
    LOG.info("Buscando card origin com id [{}]", id);
    return cardOriginService.findById(id);
  }

  @PostMapping
  public CardOrigin createCardOrigin(@RequestBody CardOriginRequest cardOriginRequest){
    return cardOriginService.createCardOrigin(cardOriginRequest);
  }

}
