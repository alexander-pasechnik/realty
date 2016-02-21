package com.alexander.pasechnik.realty.service;

import com.alexander.pasechnik.realty.domain.Apartment;
import com.alexander.pasechnik.realty.domain.ApartmentCost;
import com.alexander.pasechnik.realty.domain.statistic.ApartmentCostStatistic;
import com.alexander.pasechnik.realty.domain.statistic.ApartmentStatistics;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ApartmentStatisticsServiceImpl implements ApartmentStatisticsService {
  
  @Override
  public ApartmentStatistics gatherStatistic(List<Apartment> apartments) {
    checkNotNull(apartments);

    ApartmentStatistics apartmentStatistics = new ApartmentStatistics();

    List<ApartmentCost> apartmentCosts = apartments.stream()
        .map(Apartment::getApartmentCost)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    if (apartmentCosts.size() == 0) {
      return apartmentStatistics;
    }

    Double[] totalCosts = apartmentCosts.stream().map(ApartmentCost::getTotal).filter(Objects::nonNull).sorted().toArray(Double[]::new);
    Double[] perSquareCosts = apartmentCosts.stream().map(ApartmentCost::getPerSquare).filter(Objects::nonNull).sorted().toArray(Double[]::new);

    apartmentStatistics.setTotalCostStatistic(gatherCostStatistics(totalCosts));
    apartmentStatistics.setCostPerSquareStatistic(gatherCostStatistics(perSquareCosts));

    return apartmentStatistics;
  }
  
  private ApartmentCostStatistic gatherCostStatistics(Double[] costs) {
    ApartmentCostStatistic apartmentCostStatistic = new ApartmentCostStatistic();
    apartmentCostStatistic.setMin(costs[0]);
    apartmentCostStatistic.setMax(costs[costs.length - 1]);

    Percentile percentile = new Percentile();
    percentile.setData(ArrayUtils.toPrimitive(costs));

    apartmentCostStatistic.setMiddle(percentile.evaluate(50.0d));
    apartmentCostStatistic.setBottomQuartile(percentile.evaluate(25.0d));
    apartmentCostStatistic.setTopQuartile(percentile.evaluate(75.0d));

    return apartmentCostStatistic;
  }
}
