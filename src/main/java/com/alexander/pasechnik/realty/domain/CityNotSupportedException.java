package com.alexander.pasechnik.realty.domain;

public class CityNotSupportedException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "%s city is not found in supported cities";

  public CityNotSupportedException(String cityName) {
    System.out.println(String.format(MESSAGE_TEMPLATE, cityName));
  }
}
