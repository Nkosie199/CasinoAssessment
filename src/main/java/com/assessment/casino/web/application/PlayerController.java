package com.assessment.casino.web.application;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.web.service.CasinoServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/players")
public class PlayerController {

  @Autowired
  private CasinoServices casinoServices;

  @RequestMapping(method = RequestMethod.GET)
  public String getPlayers(Model model) {
    List<Player> playerList = this.casinoServices.getPlayers();
    model.addAttribute("players", playerList);
    return "players";
  }
}
