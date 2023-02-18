package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.data.repository.PlayerRepository;
import com.assessment.casino.data.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasinoServices {

  private PlayerRepository playerRepository;
  private TransactionRepository transactionRepository;

  @Autowired
  public CasinoServices(
    PlayerRepository playerRepository,
    TransactionRepository transactionRepository
  ) {
    this.playerRepository = playerRepository;
    this.transactionRepository = transactionRepository;
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
      //System.out.println("player name: " + p.getUsername());
    });
    List<Player> players = new ArrayList<>();
    for (Integer playerId : playerMap.keySet()) {
      players.add(playerMap.get(playerId));
    }
    return players;
  }

  public List<Transaction> getTransactions() {
    Iterable<Transaction> transactionsFromRepo =
      this.transactionRepository.findAll();
    Map<Integer, Transaction> transactionMap = new HashMap<>();
    transactionsFromRepo.forEach(t -> {
      Transaction transaction = new Transaction();
      transaction.setId(t.getId());
      transaction.setTransactionType(t.getTransactionType());
      transaction.setAmount(t.getAmount());
      transaction.setPlayerId(t.getPlayerId());
      transaction.setDateTime(t.getDateTime());
      transactionMap.put(transaction.getId(), transaction);
      //System.out.println("transaction id: " + t.getId());
    });
    List<Transaction> transactions = new ArrayList<>();
    for (Integer transactionId : transactionMap.keySet()) {
      transactions.add(transactionMap.get(transactionId));
    }
    return transactions;
  }
}
