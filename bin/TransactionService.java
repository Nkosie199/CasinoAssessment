package com.assessment.casino.web.service;

import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.data.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
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
}
