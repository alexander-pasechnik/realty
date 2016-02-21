package com.alexander.pasechnik.realty.service;

import com.alexander.pasechnik.realty.domain.Apartment;
import com.alexander.pasechnik.realty.domain.statistic.ApartmentStatistics;

import java.util.List;

public interface ApartmentStatisticsService {

  ApartmentStatistics gatherStatistic(List<Apartment> apartments);

}
