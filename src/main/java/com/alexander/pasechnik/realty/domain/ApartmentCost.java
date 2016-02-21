package com.alexander.pasechnik.realty.domain;

import java.io.Serializable;

public class ApartmentCost implements Serializable {
  private Double total;
  private Double perSquare;

  public ApartmentCost() {
  }

  public ApartmentCost(Double total, Double perSquare) {
    this();
    this.total = total;
    this.perSquare = perSquare;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Double getPerSquare() {
    return perSquare;
  }

  public void setPerSquare(Double perSquare) {
    this.perSquare = perSquare;
  }
}
