package com.assessment.casino.business.domain;

import java.sql.Time;

public class PlayerTransaction {

  private Integer playerId;
  private String playerUsername;
  private String playerBalance;
  private Time dateTime;

  public Integer getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Integer playerId) {
    this.playerId = playerId;
  }

  public String getPlayerUsername() {
    return playerUsername;
  }

  public void setPlayerUsername(String playerUsername) {
    this.playerUsername = playerUsername;
  }

  public String getPlayerBalance() {
    return playerBalance;
  }

  public void setPlayerBalance(String playerBalance) {
    this.playerBalance = playerBalance;
  }

  public Time getDateTime() {
    return dateTime;
  }

  public void setDate(Time dateTime) {
    this.dateTime = dateTime;
  }
}
