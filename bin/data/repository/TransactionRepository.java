package com.assessment.casino.data.repository;

import com.assessment.casino.data.entity.Transaction;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends CrudRepository<Transaction, Long> {
  List<Transaction> findByDate(Date date);
}
