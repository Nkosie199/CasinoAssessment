package com.assessment.casino.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/players")
public class PlayerController {

  @RequestMapping(method = RequestMethod.GET)
  public String getPlayers() {
    return "players";
  }
}
