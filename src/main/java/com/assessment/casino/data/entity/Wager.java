package com.assessment.casino.data.entity;

public class Wager {

  private Integer amount;
  private float odds;
  private int playerId;

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public float getOdds() {
    return odds;
  }

  public void setOdds(float odds) {
    this.odds = odds;
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }
}
