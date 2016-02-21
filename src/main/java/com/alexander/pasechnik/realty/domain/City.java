package com.alexander.pasechnik.realty.domain;

public enum City {
  CHERKASY(1, "Черкаси");

  private Integer id;
  private String name;

  City(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static City getById(Integer id) {
    for(City city : values()) {
      if(city.getId().equals(id))
        return city;
    }
    return null;
  }

  public static City getByName(String name) {
    for(City city : values()) {
      if(city.getName().equals(name))
        return city;
    }
    return null;
  }
}
