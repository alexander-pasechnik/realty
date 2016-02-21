package com.alexander.pasechnik.realty.domain.statistic;

public class ApartmentCostStatistic {
  private Double min;
  private Double max;
  private Double middle;
  private Double bottomQuartile;
  private Double topQuartile;

  public Double getMin() {
    return min;
  }

  public void setMin(Double min) {
    this.min = min;
  }

  public Double getMax() {
    return max;
  }

  public void setMax(Double max) {
    this.max = max;
  }

  public Double getMiddle() {
    return middle;
  }

  public void setMiddle(Double middle) {
    this.middle = middle;
  }

  public Double getBottomQuartile() {
    return bottomQuartile;
  }

  public void setBottomQuartile(Double bottomQuartile) {
    this.bottomQuartile = bottomQuartile;
  }

  public Double getTopQuartile() {
    return topQuartile;
  }

  public void setTopQuartile(Double topQuartile) {
    this.topQuartile = topQuartile;
  }
}
