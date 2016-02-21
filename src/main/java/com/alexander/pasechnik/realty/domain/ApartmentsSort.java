package com.alexander.pasechnik.realty.domain;

public class ApartmentsSort {
  private String field;
  private SortType sortType;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public SortType getSortType() {
    return sortType;
  }

  public void setSortType(SortType sortType) {
    this.sortType = sortType;
  }
}
