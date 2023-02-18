package com.assessment.casino.web.application;

import com.assessment.casino.data.entity.Transaction;
import com.assessment.casino.web.service.CasinoServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/transactions")
public class TransactionController {

  @Autowired
  private CasinoServices casinoServices;

  @RequestMapping(method = RequestMethod.GET)
  public String getTransactions(Model model) {
    List<Transaction> transactionList = this.casinoServices.getTransactions();
    model.addAttribute("playerTransactions", transactionList);
    return "transactions";
  }
}
