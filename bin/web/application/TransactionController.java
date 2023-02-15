package com.assessment.casino.web.application;

import com.assessment.casino.business.domain.PlayerTransaction;
import com.assessment.casino.business.service.TransactionService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @RequestMapping(method = RequestMethod.GET)
  public String getTransactions(
    @RequestParam(value = "date", required = false) String dateString,
    Model model
  ) {
    List<PlayerTransaction> playerTransactionList =
      this.transactionService.getPlayerTransactionsForDate(dateString);
    model.addAttribute("playerTransactions", playerTransactionList);
    return "transactions";
  }
}
