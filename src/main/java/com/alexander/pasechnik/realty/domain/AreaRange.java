package com.alexander.pasechnik.realty.domain;

public enum AreaRange {
  SIZE_LESS_THAN_20(1, 0, 20, "до 20"),
  SIZE_20_30(2, 20, 30, "20-30"),
  SIZE_30_40(3, 30, 40, "30-40"),
  SIZE_40_50(4, 40, 50, "40-50"),
  SIZE_50_60(5, 50, 60, "50-60"),
  SIZE_60_70(6, 60, 70, "60-70"),
  SIZE_70_80(7, 70, 80, "70-80"),
  SIZE_80_90(8, 80, 90, "80-90"),
  SIZE_90_100(9, 90, 100, "90-100"),
  SIZE_MORE_THAN_100(10, 100, Integer.MAX_VALUE, "більше 100");

  private Integer id;
  private Integer from;
  private Integer to;
  private String name;

  AreaRange(Integer id, Integer from, Integer to, String name) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public Integer getFrom() {
    return from;
  }

  public Integer getTo() {
    return to;
  }

  public String getName() {
    return name;
  }

  public static AreaRange getById(Integer id) {
    for(AreaRange areaRange : values()) {
      if(areaRange.getId().equals(id)) return areaRange;
    }
    return null;
  }
}
