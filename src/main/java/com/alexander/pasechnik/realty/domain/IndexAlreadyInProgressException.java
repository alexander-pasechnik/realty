package com.alexander.pasechnik.realty.domain;

public class IndexAlreadyInProgressException extends RuntimeException {

  public IndexAlreadyInProgressException() {
    System.out.println("Index already in progress");
  }
}
