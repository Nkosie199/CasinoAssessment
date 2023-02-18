package com.assessment.casino.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class Player {

  @Id
  @Column(name = "PLAYER_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "BALANCE")
  private int balance;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
