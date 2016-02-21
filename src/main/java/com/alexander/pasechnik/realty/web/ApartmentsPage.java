package com.alexander.pasechnik.realty.web;

import com.alexander.pasechnik.realty.domain.*;
import com.alexander.pasechnik.realty.domain.statistic.ApartmentStatistics;

import java.util.List;

public class ApartmentsPage {
  private City city;
  private RoomsCount roomsCount;
  private MaterialType materialType;
  private AreaRange totalAreaRange;
  private AreaRange livingAreaRange;

  private List<Apartment> apartments;
  private ApartmentStatistics apartmentStatistics;
  private ApartmentsSort apartmentsSort;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public RoomsCount getRoomsCount() {
    return roomsCount;
  }

  public void setRoomsCount(RoomsCount roomsCount) {
    this.roomsCount = roomsCount;
  }

  public MaterialType getMaterialType() {
    return materialType;
  }

  public void setMaterialType(MaterialType materialType) {
    this.materialType = materialType;
  }

  public AreaRange getTotalAreaRange() {
    return totalAreaRange;
  }

  public void setTotalAreaRange(AreaRange totalAreaRange) {
    this.totalAreaRange = totalAreaRange;
  }

  public AreaRange getLivingAreaRange() {
    return livingAreaRange;
  }

  public void setLivingAreaRange(AreaRange livingAreaRange) {
    this.livingAreaRange = livingAreaRange;
  }

  public List<Apartment> getApartments() {
    return apartments;
  }

  public void setApartments(List<Apartment> apartments) {
    this.apartments = apartments;
  }

  public ApartmentStatistics getApartmentStatistics() {
    return apartmentStatistics;
  }

  public void setApartmentStatistics(ApartmentStatistics apartmentStatistics) {
    this.apartmentStatistics = apartmentStatistics;
  }

  public ApartmentsSort getApartmentsSort() {
    return apartmentsSort;
  }

  public void setApartmentsSort(ApartmentsSort apartmentsSort) {
    this.apartmentsSort = apartmentsSort;
  }
}
