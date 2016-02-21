package com.alexander.pasechnik.realty.web;

import com.alexander.pasechnik.realty.domain.City;
import com.alexander.pasechnik.realty.domain.CityNotSupportedException;
import com.alexander.pasechnik.realty.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

  @Autowired
  private IndexService indexService;

  @RequestMapping("/index")
  public String index(Model model) {
    model.addAttribute("cities", City.values());
    return "index";
  }

  @RequestMapping(value = "/index", method = RequestMethod.POST)
  @ResponseBody
  public void doIndex(@RequestParam(required = true) String cityName) {
    indexService.checkIndexInProgress();

    City city = City.getByName(cityName);
    if (null == city) {
      throw new CityNotSupportedException(cityName);
    }

    indexService.indexCity(city);
  }

}
