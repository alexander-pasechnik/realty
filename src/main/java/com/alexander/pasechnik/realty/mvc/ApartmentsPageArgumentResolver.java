package com.alexander.pasechnik.realty.mvc;

import com.alexander.pasechnik.realty.domain.*;
import com.alexander.pasechnik.realty.web.ApartmentsPage;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ApartmentsPageArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(ApartmentsPage.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    ApartmentsPage apartmentsPage = new ApartmentsPage();

    String city = webRequest.getParameter("city");
    String roomsCount = webRequest.getParameter("roomsCount");
    String materialType = webRequest.getParameter("materialType");
    String totalAreaRange = webRequest.getParameter("totalAreaRange");
    String livingAreaRange = webRequest.getParameter("livingAreaRange");
    String sortField = webRequest.getParameter("sortBy");
    String sortType = webRequest.getParameter("sortType");

    if (isDefined(city)) apartmentsPage.setCity(City.getById(Integer.valueOf(city)));
    if (isDefined(roomsCount)) apartmentsPage.setRoomsCount(RoomsCount.getByCount(Integer.valueOf(roomsCount)));
    if (isDefined(materialType)) apartmentsPage.setMaterialType(MaterialType.getById(Integer.valueOf(materialType)));
    if (isDefined(totalAreaRange)) apartmentsPage.setTotalAreaRange(AreaRange.getById(Integer.valueOf(totalAreaRange)));
    if (isDefined(livingAreaRange)) apartmentsPage.setLivingAreaRange(AreaRange.getById(Integer.valueOf(livingAreaRange)));

    ApartmentsSort apartmentsSort = new ApartmentsSort();
    if (isDefined(sortField)) apartmentsSort.setField(sortField);
    if (isDefined(sortType)) {
      apartmentsSort.setSortType(SortType.getById(Integer.valueOf(sortType)));
    } else {
      apartmentsSort.setSortType(SortType.DEFAULT);
    }
    apartmentsPage.setApartmentsSort(apartmentsSort);

    return apartmentsPage;
  }

  private boolean isDefined(String value) {
    return value != null && !value.isEmpty();
  }
}
