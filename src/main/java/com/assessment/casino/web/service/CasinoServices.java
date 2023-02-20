package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.data.repository.PlayersRepository;
import com.assessment.casino.data.repository.TransactionsRepository;
import com.assessment.casino.web.error.BadRequestException;
import com.assessment.casino.web.error.TeapotException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasinoServices {

  private PlayersRepository playerRepository;
  private TransactionsRepository transactionRepository;

  @Autowired
  public CasinoServices(
    PlayersRepository playerRepository,
    TransactionsRepository transactionRepository
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
    });
    List<Player> players = new ArrayList<>();
    for (Integer playerId : playerMap.keySet()) {
      players.add(playerMap.get(playerId));
    }
    return players;
  }

  public Player retrievePlayer(int playerId) throws BadRequestException {
    Player playerFromRepo = this.playerRepository.findById(playerId);
    if (playerFromRepo != null) {
      return playerFromRepo;
    } else {
      throw new BadRequestException("User not found");
    }
  }

  public int retrievePlayerBalance(int playerId) throws BadRequestException {
    Player playerFromRepo = retrievePlayer(playerId);
    if (playerFromRepo != null) {
      return playerFromRepo.getBalance();
    } else {
      throw new BadRequestException("User not found");
    }
  }

  public int updatePlayerBalance(int playerId, int amount)
    throws BadRequestException, TeapotException {
    Player playerFromRepo = retrievePlayer(playerId);
    if (playerFromRepo != null) {
      int balance = retrievePlayerBalance(playerFromRepo.getId());
      if (balance + amount < 0) {
        throw new TeapotException("Insufficient funds");
      } else {
        playerFromRepo.setBalance(balance + amount);
        Player updatedPlayer = this.playerRepository.save(playerFromRepo);
        return updatedPlayer.getBalance();
      }
    } else {
      throw new BadRequestException("User not found");
    }
  }

  public Transaction addTransaction(int playerId, Transaction transaction)
    throws BadRequestException {
    Player playerFromRepo = retrievePlayer(playerId);
    if (playerFromRepo != null) {
      return this.transactionRepository.save(transaction);
    } else {
      throw new BadRequestException("User not found");
    }
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
    });
    List<Transaction> transactions = new ArrayList<>();
    for (Integer transactionId : transactionMap.keySet()) {
      transactions.add(transactionMap.get(transactionId));
    }
    return transactions;
  }

  public List<Transaction> getPlayersTransactions(String playerUsername)
    throws BadRequestException {
    List<Player> playersFromRepo =
      this.playerRepository.findByUsername(playerUsername);
    if (playersFromRepo.isEmpty()) {
      throw new BadRequestException("No users found with that username");
    } else if (playersFromRepo.size() > 1) {
      throw new BadRequestException(
        "Too many users found with the same username"
      );
    } else {
      Player playerFromRepo = playersFromRepo.get(0);
      Iterable<Transaction> transactionsFromRepo =
        this.transactionRepository.findByPlayerId(playerFromRepo.getId());
      Map<Integer, Transaction> transactionMap = new HashMap<>();
      transactionsFromRepo.forEach(t -> {
        Transaction transaction = new Transaction();
        transaction.setId(t.getId());
        transaction.setTransactionType(t.getTransactionType());
        transaction.setAmount(t.getAmount());
        transaction.setPlayerId(t.getPlayerId());
        transaction.setDateTime(t.getDateTime());
        transactionMap.put(transaction.getId(), transaction);
      });
      List<Transaction> transactions = new ArrayList<>();
      for (Integer transactionId : transactionMap.keySet()) {
        transactions.add(transactionMap.get(transactionId));
      }
      return transactions;
    }
  }

  public List<Transaction> getPlayersLatestTransactions(String playerUsername)
    throws BadRequestException {
    List<Transaction> allTransactions = getPlayersTransactions(playerUsername);
    List<Transaction> transactions = new ArrayList<>();
    for (Transaction t : allTransactions) {
      if (transactions.size() < 10) {
        transactions.add(t);
      }
    }
    return transactions;
  }
}
