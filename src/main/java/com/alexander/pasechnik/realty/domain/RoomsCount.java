package com.alexander.pasechnik.realty.domain;

public enum RoomsCount {
  ONE(1, "1 кімн."),
  TWO(2, "2 кімн."),
  THREE(3, "3 кімн."),
  FOUR(4, "4 кімн.");

  private Integer count;
  private String name;

  RoomsCount(Integer count, String name) {
    this.count = count;
    this.name = name;
  }

  public Integer getCount() {
    return count;
  }

  public String getName() {
    return name;
  }

  public static RoomsCount getByCount(Integer count) {
    for (RoomsCount roomsCount : values()) {
      if (roomsCount.getCount().equals(count)) {
        return roomsCount;
      }
    }
    return null;
  }
}
