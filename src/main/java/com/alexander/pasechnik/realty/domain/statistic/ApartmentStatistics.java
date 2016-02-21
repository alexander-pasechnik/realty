package com.alexander.pasechnik.realty.domain.statistic;

public class ApartmentStatistics {
  private ApartmentCostStatistic totalCostStatistic;
  private ApartmentCostStatistic costPerSquareStatistic;

  public ApartmentCostStatistic getTotalCostStatistic() {
    return totalCostStatistic;
  }

  public void setTotalCostStatistic(ApartmentCostStatistic totalCostStatistic) {
    this.totalCostStatistic = totalCostStatistic;
  }

  public ApartmentCostStatistic getCostPerSquareStatistic() {
    return costPerSquareStatistic;
  }

  public void setCostPerSquareStatistic(ApartmentCostStatistic costPerSquareStatistic) {
    this.costPerSquareStatistic = costPerSquareStatistic;
  }
}
