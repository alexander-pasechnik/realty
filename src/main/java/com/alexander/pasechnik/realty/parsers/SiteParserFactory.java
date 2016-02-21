package com.alexander.pasechnik.realty.parsers;

import com.alexander.pasechnik.realty.domain.City;

public class SiteParserFactory {

  public SiteParser getSiteParser(City city) {
    if (City.CHERKASY.equals(city)) {
      return new OtidoSiteParser();
    }

    return null;
  }
}
