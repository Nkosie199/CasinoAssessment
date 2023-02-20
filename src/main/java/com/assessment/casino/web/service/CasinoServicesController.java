package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.web.error.BadRequestException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/casino")
public class CasinoServicesController {

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

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/player/{playerId}/balance"
  )
  public int getPlayerBalance(@PathVariable("playerId") int playerId)
    throws BadRequestException {
    return this.casinoServices.retrievePlayerBalance(playerId);
  }

  @RequestMapping(
    method = RequestMethod.POST,
    value = "/player/{playerId}/balance/update"
  )
  public int setPlayerBalance(
    @PathVariable("playerId") int playerId,
    int amount
  ) throws Exception {
    return this.casinoServices.updatePlayerBalance(playerId, amount);
  }

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/admin/{playerUsername}/transactions"
  )
  public List<Transaction> getPlayersLast10Transactions(
    @PathVariable("playerUsername") String playerUsername
  ) throws BadRequestException {
    return this.casinoServices.getPlayersLatestTransactions(playerUsername);
  }

  @RequestMapping(
    method = RequestMethod.POST,
    value = "/player/{playId}/transaction/add"
  )
  public Transaction addPlayerTransaction(
    @PathVariable("playId") int playerId,
    Transaction transaction
  ) throws Exception {
    return this.casinoServices.addTransaction(playerId, transaction);
  }
}
