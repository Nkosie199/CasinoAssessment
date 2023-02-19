package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.repository.PlayerRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private PlayerRepository playerRepository;

  @Autowired
  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public List<Player> getPlayers() {
    Iterable<Player> playersFromRepo = this.playerRepository.findAll();
    Map<Integer, Player> playerMap = new HashMap<>();
    playersFromRepo.forEach(p -> {
      Player player = new Player();
      player.setId(p.getId());
      player.setUsername(p.getUsername());
      player.setBalance(p.getBalance());
      playerMap.put(player.getId(), player);
    });
    List<Player> players = new ArrayList<>();
    for (Integer playerId : playerMap.keySet()) {
      players.add(playerMap.get(playerId));
    }
    return players;
  }
}
