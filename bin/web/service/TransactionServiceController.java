package com.assessment.casino.web.service;

import com.assessment.casino.business.domain.PlayerTransaction;
import com.assessment.casino.business.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TransactionServiceController {

  @Autowired
  private TransactionService transactionService;

  @RequestMapping(method = RequestMethod.GET, value = "/transactions/{date}")
  public List<PlayerTransaction> getAllTransactionsForDate(
    @PathVariable(value = "date") String dateString
  ) {
    return this.transactionService.getPlayerTransactionsForDate(dateString);
  }
}
