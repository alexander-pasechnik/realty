package com.alexander.pasechnik.realty.util;

import com.alexander.pasechnik.realty.domain.*;
import com.alexander.pasechnik.realty.web.ApartmentsPage;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.*;

import java.util.List;
import java.util.stream.Collectors;

public class LuceneHelper {

  public static Query buildQueryByPage(ApartmentsPage apartmentsPage) {
    BooleanQuery.Builder booleanBuilder = new BooleanQuery.Builder();

    Query cityQuery = NumericRangeQuery.newIntRange(
        LuceneDocument.CITY, apartmentsPage.getCity().getId(), apartmentsPage.getCity().getId(), true, true
    );
    booleanBuilder.add(cityQuery, BooleanClause.Occur.MUST);

    if (apartmentsPage.getRoomsCount() != null) {
      Query roomsQuery = NumericRangeQuery.newIntRange(
          LuceneDocument.ROOMS, apartmentsPage.getRoomsCount().getCount(), apartmentsPage.getRoomsCount().getCount(), true, true
      );
      booleanBuilder.add(roomsQuery, BooleanClause.Occur.MUST);
    }

    if (apartmentsPage.getMaterialType() != null) {
      Query materialTypeQuery = NumericRangeQuery.newIntRange(
          LuceneDocument.MATERIAL_TYPE, apartmentsPage.getMaterialType().getId(), apartmentsPage.getMaterialType().getId(), true, true
      );
      booleanBuilder.add(materialTypeQuery, BooleanClause.Occur.MUST);
    }

    if (apartmentsPage.getTotalAreaRange() != null) {
      Query totalAreaQuery = NumericRangeQuery.newIntRange(
          LuceneDocument.TOTAL_AREA, apartmentsPage.getTotalAreaRange().getFrom(), apartmentsPage.getTotalAreaRange().getTo(), true, true
      );
      booleanBuilder.add(totalAreaQuery, BooleanClause.Occur.MUST);
    }

    if (apartmentsPage.getLivingAreaRange() != null) {
      Query livingAreaQuery = NumericRangeQuery.newIntRange(
          LuceneDocument.LIVING_AREA, apartmentsPage.getLivingAreaRange().getFrom(), apartmentsPage.getLivingAreaRange().getTo(), true, true
      );
      booleanBuilder.add(livingAreaQuery, BooleanClause.Occur.MUST);
    }

    return booleanBuilder.build();
  }

  public static Sort prepareSort(ApartmentsSort apartmentsSort) {
    if (apartmentsSort.getField() == null || apartmentsSort.getSortType() == null) {
      return Sort.INDEXORDER;
    }
    boolean reverse = apartmentsSort.getSortType().equals(SortType.DESC);
    SortField sortField = new SortField(apartmentsSort.getField(), SortField.Type.DOUBLE, reverse);

    return new Sort(SortField.FIELD_SCORE, sortField);
  }

  public static List<Document> convertApartmentsToDocuments(List<Apartment> apartments) {
    return apartments.stream().map(LuceneHelper::convertApartmentToDocument).collect(Collectors.toList());
  }

  public static Document convertApartmentToDocument(Apartment apartment) {
    Document doc = new Document();
    doc.add(new IntField(LuceneDocument.CITY, apartment.getCity().getId(), Field.Store.YES));
    if (apartment.getRooms() != null) {
      doc.add(new IntField(LuceneDocument.ROOMS, apartment.getRooms(), Field.Store.YES));
    }
    if (apartment.getStorey() != null) {
      doc.add(new IntField(LuceneDocument.STOREY, apartment.getStorey(), Field.Store.YES));
    }
    if (apartment.getBuildingMaterialType() != null) {
      doc.add(new IntField(LuceneDocument.MATERIAL_TYPE, apartment.getBuildingMaterialType().getId(), Field.Store.YES));
    }
    if (apartment.getApartmentArea() != null) {
      doc.add(new IntField(LuceneDocument.TOTAL_AREA, apartment.getApartmentArea().getTotal(), Field.Store.YES));
      doc.add(new IntField(LuceneDocument.LIVING_AREA, apartment.getApartmentArea().getLiving(), Field.Store.YES));
    }
    if (apartment.getApartmentCost() != null && apartment.getApartmentCost().getTotal() != null) {
      doc.add(new DoubleField(LuceneDocument.TOTAL_COST, apartment.getApartmentCost().getTotal(), LuceneDocument.DOUBLE_FIELD_TYPE_STORED_SORTED));
    }
    if (apartment.getApartmentCost() != null && apartment.getApartmentCost().getPerSquare() != null) {
      doc.add(new DoubleField(LuceneDocument.COST_PER_SQUARE, apartment.getApartmentCost().getPerSquare(), LuceneDocument.DOUBLE_FIELD_TYPE_STORED_SORTED));
    }
    doc.add(new TextField(LuceneDocument.ADVERT, apartment.getAdvert().getContent(), Field.Store.YES));

    return doc;
  }

  public static List<Apartment> convertDocumentsToApartments(List<Document> documents) {
    return documents.stream().map(LuceneHelper::convertDocumentToApartment).collect(Collectors.toList());
  }

  public static Apartment convertDocumentToApartment(Document document) {
    Apartment apartment = new Apartment();
    IndexableField cityField = document.getField(LuceneDocument.CITY);
    IndexableField roomsField = document.getField(LuceneDocument.ROOMS);
    IndexableField storeyField = document.getField(LuceneDocument.STOREY);
    IndexableField materialTypeField = document.getField(LuceneDocument.MATERIAL_TYPE);
    IndexableField totalAreaField = document.getField(LuceneDocument.TOTAL_AREA);
    IndexableField livingAreaField = document.getField(LuceneDocument.LIVING_AREA);
    IndexableField totalCostField = document.getField(LuceneDocument.TOTAL_COST);
    IndexableField costPerSquareField = document.getField(LuceneDocument.COST_PER_SQUARE);
    IndexableField advertField = document.getField(LuceneDocument.ADVERT);

    apartment.setCity(City.getById((Integer) cityField.numericValue()));
    if (roomsField != null) { apartment.setRooms((Integer) roomsField.numericValue()); }
    if (storeyField != null) { apartment.setStorey((Integer) storeyField.numericValue()); }
    if (materialTypeField != null) { apartment.setBuildingMaterialType(MaterialType.getById((Integer) materialTypeField.numericValue())); }

    ApartmentArea apartmentArea = new ApartmentArea();
    if (totalAreaField != null) { apartmentArea.setTotal((Integer) totalAreaField.numericValue());}
    if (livingAreaField != null) { apartmentArea.setLiving((Integer) livingAreaField.numericValue());}
    apartment.setApartmentArea(apartmentArea);

    ApartmentCost apartmentCost = new ApartmentCost();
    if (totalCostField != null) { apartmentCost.setTotal((Double) totalCostField.numericValue());}
    if (costPerSquareField != null) { apartmentCost.setPerSquare((Double) costPerSquareField.numericValue());}
    apartment.setApartmentCost(apartmentCost);

    if (advertField != null) { apartment.setAdvert(new Advert(advertField.stringValue())); }

    return apartment;
  }
}
