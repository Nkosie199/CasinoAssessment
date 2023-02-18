package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

  //GET /player/{playerId}/balance
  @RequestMapping(
    method = RequestMethod.GET,
    value = "/player/{playerId}/balance"
  )
  public int getPlayerBalance(@PathVariable("playerId") int playerId) {
    //System.out.println("getPlayerBalance | playerId: " + playerId);
    return this.casinoServices.retrievePlayerBalance(playerId);
  }

  //POST /player/{playerid}/balance/update
  @RequestMapping(
    method = RequestMethod.POST,
    value = "/player/{playerId}/balance/update"
  )
  public int setPlayerBalance(
    @PathVariable("playerId") int playerId,
    int balance
  ) {
    System.out.println("getPlayerBalance | balance: " + balance);
    return this.casinoServices.updatePlayerBalance(playerId, balance);
  }

  //POST /admin/{playerUsername}/transactions
  @RequestMapping(
    method = RequestMethod.GET,
    value = "/admin/{playerUsername}/transactions"
  )
  public List<Transaction> getPlayersLast10Transactions(
    @PathVariable("playerUsername") int playerUsername
  ) {
    return this.casinoServices.getPlayersLatestTransactions(playerUsername);
  }
}
