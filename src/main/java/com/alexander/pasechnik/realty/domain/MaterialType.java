package com.alexander.pasechnik.realty.domain;

public enum MaterialType {
  BRICK(1, "Ц", "Цегляний"),
  PANEL(2, "П", "Панельний"),
  MONOLITHIC(3, "М", "Моноліт"),
  UNKNOWN(4, "", "Невідомо");

  private Integer id;
  private String abbr;
  private String name;

  MaterialType(Integer id, String abbr, String name) {
    this.id = id;
    this.abbr = abbr;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getAbbr() {
    return abbr;
  }

  public String getName() {
    return name;
  }

  public static MaterialType getById(Integer id) {
    for (MaterialType materialType : values()) {
      if (materialType.getId().equals(id)) {
        return materialType;
      }
    }
    return MaterialType.UNKNOWN;
  }

  public static MaterialType getByAbbr(String abbr) {
    for (MaterialType materialType : values()) {
      if (materialType.getAbbr().equals(abbr)) {
        return materialType;
      }
    }
    return MaterialType.UNKNOWN;
  }
}
