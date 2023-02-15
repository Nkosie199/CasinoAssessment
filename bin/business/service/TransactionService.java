package com.assessment.casino.business.service;

import com.assessment.casino.business.domain.PlayerTransaction;
import com.assessment.casino.data.entity.Player;
import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.data.repository.PlayerRepository;
import com.assessment.casino.data.repository.TransactionRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private PlayerRepository playerRepository;
  private TransactionRepository transactionRepository;

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
    "yyyy-MM-dd"
  );

  @Autowired
  public TransactionService(
    PlayerRepository playerRepository,
    TransactionRepository transactionRepository
  ) {
    this.playerRepository = playerRepository;
    this.transactionRepository = transactionRepository;
  }

  public List<PlayerTransaction> getPlayerTransactionsForDate(
    String dateString
  ) {
    Iterable<Player> players = this.playerRepository.findAll();
    Map<Integer, PlayerTransaction> playerTransactionMap = new HashMap<>();
    players.forEach(player -> {
      PlayerTransaction playerTransaction = new PlayerTransaction();
      playerTransaction.setPlayerId(player.getId());
      playerTransaction.setPlayerUsername(player.getUsername());
      playerTransaction.setPlayerBalance(dateString);
      playerTransactionMap.put(player.getId(), playerTransaction);
    });
    List<PlayerTransaction> playerTransactions = new ArrayList<>();
    for (Integer playerId : playerTransactionMap.keySet()) {
      playerTransactions.add(playerTransactionMap.get(playerId));
    }
    return playerTransactions;
  }

  private Date createDateFromDateString(String dateString) {
    Date date = null;
    if (null != dateString) {
      try {
        date = DATE_FORMAT.parse(dateString);
      } catch (ParseException pe) {
        date = new Date();
      }
    } else {
      date = new Date();
    }
    return date;
  }
}
