package com.alexander.pasechnik.realty.domain;

public class Apartment {
  private City city;
  private Integer rooms;
  private Integer storey;
  private MaterialType buildingMaterialType;
  private ApartmentArea apartmentArea;
  private ApartmentCost apartmentCost;
  private Advert advert;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Integer getRooms() {
    return rooms;
  }

  public void setRooms(Integer rooms) {
    this.rooms = rooms;
  }

  public Integer getStorey() {
    return storey;
  }

  public void setStorey(Integer storey) {
    this.storey = storey;
  }

  public MaterialType getBuildingMaterialType() {
    return buildingMaterialType;
  }

  public void setBuildingMaterialType(MaterialType buildingMaterialType) {
    this.buildingMaterialType = buildingMaterialType;
  }

  public ApartmentArea getApartmentArea() {
    return apartmentArea;
  }

  public void setApartmentArea(ApartmentArea apartmentArea) {
    this.apartmentArea = apartmentArea;
  }

  public ApartmentCost getApartmentCost() {
    return apartmentCost;
  }

  public void setApartmentCost(ApartmentCost apartmentCost) {
    this.apartmentCost = apartmentCost;
  }

  public Advert getAdvert() {
    return advert;
  }

  public void setAdvert(Advert advert) {
    this.advert = advert;
  }
}
