package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/casino")
public class CasinoServiceController {

  @Autowired
  private CasinoServices casinoServices;

  @RequestMapping(method = RequestMethod.GET, value = "/players")
  public List<Player> getAllPlayers() {
    return this.casinoServices.getPlayers();
  }

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/admin/player/transactions"
  )
  public List<Transaction> getAllTransactions() {
    return this.casinoServices.getTransactions();
  }
}
