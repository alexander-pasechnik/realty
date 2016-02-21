package com.alexander.pasechnik.realty.service;

import com.alexander.pasechnik.realty.domain.Apartment;
import com.alexander.pasechnik.realty.domain.City;
import com.alexander.pasechnik.realty.web.ApartmentsPage;

import java.util.List;

public interface IndexService {

  void indexCity(City city);

  List<Apartment> search(ApartmentsPage searchQuery);

  boolean isIndexInProgress();

  void checkIndexInProgress();
}
