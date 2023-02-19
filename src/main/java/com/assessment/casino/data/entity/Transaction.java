package com.assessment.casino.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

  public enum TransactionType {
    WIN,
    WAGER,
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TRANSACTION_ID")
  private int id;

  @Column(name = "TRANSACTION_TYPE")
  private String transactionType;

  @Column(name = "AMOUNT")
  private int amount;

  @Column(name = "PLAYER_ID")
  private int playerId;

  @Column(name = "DATE_TIME")
  private String dateTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }
}
