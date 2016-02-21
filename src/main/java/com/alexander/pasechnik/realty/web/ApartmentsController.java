package com.alexander.pasechnik.realty.web;

import com.alexander.pasechnik.realty.domain.*;
import com.alexander.pasechnik.realty.service.ApartmentStatisticsService;
import com.alexander.pasechnik.realty.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ApartmentsController {

  @Autowired
  private IndexService indexService;

  @Autowired
  private ApartmentStatisticsService apartmentStatisticsService;

  @RequestMapping("/apartments")
  public String search(ApartmentsPage apartmentsPage, Model model) {
    model.addAttribute("cities", City.values());
    model.addAttribute("roomsCounts", RoomsCount.values());
    model.addAttribute("materialTypes", MaterialType.values());
    model.addAttribute("totalAreaRanges", AreaRange.values());
    model.addAttribute("livingAreaRanges", AreaRange.values());
    model.addAttribute("sortTypes", SortType.values());

    if (apartmentsPage.getCity() != null) {
      apartmentsPage.setApartments(indexService.search(apartmentsPage));
    } else {
      apartmentsPage.setApartments(new ArrayList<>());
    }
    apartmentsPage.setApartmentStatistics(apartmentStatisticsService.gatherStatistic(apartmentsPage.getApartments()));
    model.addAttribute("apartmentsPage", apartmentsPage);

    return "apartments";
  }
}
