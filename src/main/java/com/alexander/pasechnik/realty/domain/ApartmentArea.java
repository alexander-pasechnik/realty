package com.alexander.pasechnik.realty.domain;

import java.io.Serializable;

public class ApartmentArea implements Serializable {
  private Integer total;
  private Integer living;

  public ApartmentArea() {
  }

  public ApartmentArea(Integer total, Integer living) {
    this();
    this.total = total;
    this.living = living;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getLiving() {
    return living;
  }

  public void setLiving(Integer living) {
    this.living = living;
  }
}
