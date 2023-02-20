package com.assessment.casino.web.application;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.web.error.BadRequestException;
import com.assessment.casino.web.service.CasinoServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/player/{playerId}")
public class PlayerController {

  @Autowired
  private CasinoServices casinoServices;

  @RequestMapping(method = RequestMethod.GET)
  public String getPlayer(@PathVariable("playerId") int playerId, Model model)
    throws BadRequestException {
    Player player = this.casinoServices.retrievePlayer(playerId);
    model.addAttribute("player", player);
    List<Transaction> transactions =
      this.casinoServices.getPlayersTransactions(player.getUsername());
    model.addAttribute("transactions", transactions);
    return "player";
  }
}
