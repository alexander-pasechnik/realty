package com.alexander.pasechnik.realty.parsers;

import com.alexander.pasechnik.realty.domain.Advert;
import com.alexander.pasechnik.realty.domain.Apartment;
import com.alexander.pasechnik.realty.domain.ApartmentArea;
import com.alexander.pasechnik.realty.domain.ApartmentCost;
import com.alexander.pasechnik.realty.domain.MaterialType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class OtidoSiteParser implements SiteParser {

  private static final String SITE_URL = "http://otido.ua";
  private static final String REALTY_RUBRIC_REGEX = "^112.*Черкасах$";
  private static final String APARTMENTS_CSS_SELECTOR = "body > table > tbody > tr > td.с5 > :nth-child(3) > table";
  private static final String APARTMENT_ROOMS_REGEX = "^(\\d)-(кімн)";
  private static final String APARTMENT_BUILDING_REGEX = "(\\d{1,2})/(\\d{1,2})/([П,Ц,М])";
  private static final String APARTMENT_AREA_REGEX = "(\\d{1,3})/(\\d{1,3})/(\\d{1,2})";
  private static final String APARTMENT_COST_REGEX = "(\\d+)\\s?грн\\.?,?(\\s|/м2)";

  @Override
  public List<Apartment> parse() {
    return getPagesUrls(SITE_URL).parallelStream()
        .map(this::getApartmentsFromPage)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  private List<String> getPagesUrls(String mainUrl) {
    checkNotNull(mainUrl);

    Document mainPage = createDocumentFromUrl(mainUrl);
    Elements menuLinks = mainPage.select(".navmenu-links a");

    Pattern p = Pattern.compile(REALTY_RUBRIC_REGEX, Pattern.CASE_INSENSITIVE);
    String firstPageUrl = menuLinks.stream()
        .filter(link -> p.matcher(link.text()).matches())
        .map(link -> link.attr("abs:href"))
        .findFirst()
        .get();

    return createDocumentFromUrl(firstPageUrl).getElementsByTag("a").stream()
        .map(link -> link.attr("abs:href"))
        .filter(url -> url.startsWith(firstPageUrl))
        .distinct()
        .collect(Collectors.toList());
  }

  private List<Apartment> getApartmentsFromPage(String pageUrl) {
    checkNotNull(pageUrl);

    return createDocumentFromUrl(pageUrl).select(APARTMENTS_CSS_SELECTOR).stream()
        .map(Element::text)
        .filter(advert -> !advert.trim().isEmpty())
        .map(this::parseAdvert)
        .collect(Collectors.toList());
  }

  private Document createDocumentFromUrl(String url) {
    Document document;
    try {
      document = Jsoup.connect(url).get();
    } catch (IOException e) {
      System.out.println(String.format("Can't create document from url: %s", url));
      throw new RuntimeException(String.format("Can't create document from url: %s", url));

    }
    return document;
  }

  private Apartment parseAdvert(String advertContent) {
    Apartment apartment = new Apartment();
    apartment.setAdvert(new Advert(advertContent));

    Pattern roomsPattern = Pattern.compile(APARTMENT_ROOMS_REGEX, Pattern.CASE_INSENSITIVE);
    Matcher roomsPatterMatcher = roomsPattern.matcher(advertContent);
    if (roomsPatterMatcher.find()) {
      apartment.setRooms(Integer.valueOf(roomsPatterMatcher.group(1)));
    }

    Pattern buildingPatten = Pattern.compile(APARTMENT_BUILDING_REGEX, Pattern.CASE_INSENSITIVE);
    Matcher buildingPattenMatcher = buildingPatten.matcher(advertContent);
    if (buildingPattenMatcher.find()) {
      apartment.setStorey(Integer.valueOf(buildingPattenMatcher.group(1)));

      MaterialType materialType = MaterialType.getByAbbr(buildingPattenMatcher.group(3));
      apartment.setBuildingMaterialType(materialType);
    }

    Pattern areaPattern = Pattern.compile(APARTMENT_AREA_REGEX, Pattern.CASE_INSENSITIVE);
    Matcher areaPatternMatcher = areaPattern.matcher(advertContent);
    if (areaPatternMatcher.find()) {
      Integer totalArea = Integer.valueOf(areaPatternMatcher.group(1));
      Integer livingArea = Integer.valueOf(areaPatternMatcher.group(2));
      apartment.setApartmentArea(new ApartmentArea(totalArea, livingArea));
    }

    Pattern costPattern = Pattern.compile(APARTMENT_COST_REGEX, Pattern.CASE_INSENSITIVE);
    Matcher costPatternMatcher = costPattern.matcher(advertContent);
    if (costPatternMatcher.find()) {
      Double totalCost = null;
      Double costPerSquare = null;
      if (costPatternMatcher.group(2) == null || costPatternMatcher.group(2).trim().isEmpty()) {
        totalCost = Double.valueOf(costPatternMatcher.group(1));
        if (apartment.getApartmentArea() != null && apartment.getApartmentArea().getTotal() != null) {
          costPerSquare = totalCost / apartment.getApartmentArea().getTotal();
        }
      }
      else {
        costPerSquare = Double.valueOf(costPatternMatcher.group(1));
        if (apartment.getApartmentArea() != null && apartment.getApartmentArea().getTotal() != null) {
          totalCost = costPerSquare * apartment.getApartmentArea().getTotal();
        }
      }
      apartment.setApartmentCost(new ApartmentCost(totalCost, costPerSquare));
    }

    return apartment;
  }
}
