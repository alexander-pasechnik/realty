package com.alexander.pasechnik.realty.parsers;

import com.alexander.pasechnik.realty.domain.Apartment;

import java.util.List;

public interface SiteParser {

  List<Apartment> parse();

}
