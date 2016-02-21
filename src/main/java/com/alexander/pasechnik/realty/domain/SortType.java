package com.alexander.pasechnik.realty.domain;

public enum  SortType {
  ASC(1, "По зростанню"),
  DESC(2, "По спаданню");

  public static SortType DEFAULT = SortType.ASC;

  private Integer id;
  private String name;

  SortType(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static SortType getById(Integer id) {
    for (SortType sortType : values()) {
      if (sortType.getId().equals(id))
        return sortType;
    }
    return null;
  }
}
