package com.alexander.pasechnik.realty.domain;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;

public class LuceneDocument {
  public static final String CITY = "cityId";
  public static final String ROOMS = "rooms";
  public static final String STOREY = "storey";
  public static final String MATERIAL_TYPE = "materialTypeId";
  public static final String TOTAL_AREA = "totalArea";
  public static final String LIVING_AREA = "livingArea";
  public static final String TOTAL_COST = "totalCost";
  public static final String COST_PER_SQUARE = "costPerSquare";
  public static final String ADVERT = "advert";

  public static final FieldType DOUBLE_FIELD_TYPE_STORED_SORTED = new FieldType();
  static {
    DOUBLE_FIELD_TYPE_STORED_SORTED.setTokenized(true);
    DOUBLE_FIELD_TYPE_STORED_SORTED.setOmitNorms(true);
    DOUBLE_FIELD_TYPE_STORED_SORTED.setIndexOptions(IndexOptions.DOCS);
    DOUBLE_FIELD_TYPE_STORED_SORTED.setNumericType(FieldType.NumericType.DOUBLE);
    DOUBLE_FIELD_TYPE_STORED_SORTED.setStored(true);
    DOUBLE_FIELD_TYPE_STORED_SORTED.setDocValuesType(DocValuesType.NUMERIC);
    DOUBLE_FIELD_TYPE_STORED_SORTED.freeze();
  }
}
