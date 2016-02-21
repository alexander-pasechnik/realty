package com.alexander.pasechnik.realty.service;

import com.alexander.pasechnik.realty.domain.Apartment;
import com.alexander.pasechnik.realty.domain.City;
import com.alexander.pasechnik.realty.domain.IndexAlreadyInProgressException;
import com.alexander.pasechnik.realty.parsers.SiteParser;
import com.alexander.pasechnik.realty.parsers.SiteParserFactory;
import com.alexander.pasechnik.realty.util.LuceneHelper;
import com.alexander.pasechnik.realty.web.ApartmentsPage;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class IndexServiceImpl implements IndexService {

  private StandardAnalyzer analyzer = new StandardAnalyzer();
  private Directory directory = new RAMDirectory();
  private volatile boolean indexInProgress = Boolean.FALSE;

  @Override
  public void indexCity(City city) {
    checkNotNull(city);

    this.indexInProgress = Boolean.TRUE;
    try {
      SiteParserFactory siteParserFactory = new SiteParserFactory();
      SiteParser siteParser = siteParserFactory.getSiteParser(city);
      List<Apartment> apartments = siteParser.parse();

      IndexWriterConfig config = new IndexWriterConfig(analyzer);

      try (IndexWriter indexWriter = new IndexWriter(directory, config)) {
        apartments.forEach(apartment -> apartment.setCity(city));
        List<Document> documents = LuceneHelper.convertApartmentsToDocuments(apartments);
        for (Document doc : documents) {
          indexWriter.addDocument(doc);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } finally {
      this.indexInProgress = Boolean.FALSE;
    }
  }

  public List<Apartment> search(ApartmentsPage apartmentsPage) {
    checkNotNull(apartmentsPage);

    Query query = LuceneHelper.buildQueryByPage(apartmentsPage);

    List<Document> documents = new ArrayList<>();
    try (IndexReader reader = DirectoryReader.open(directory)) {
      if (directory.listAll().length == 0) {
        return new ArrayList<>();
      }

      Sort sort = LuceneHelper.prepareSort(apartmentsPage.getApartmentsSort());

      IndexSearcher searcher = new IndexSearcher(reader);
      TopDocs results = searcher.search(query, Integer.MAX_VALUE, sort);
      for (ScoreDoc scoreDoc : results.scoreDocs) {
        documents.add(searcher.doc(scoreDoc.doc));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return LuceneHelper.convertDocumentsToApartments(documents);
  }

  @Override
  public boolean isIndexInProgress() {
    return this.indexInProgress;
  }

  @Override
  public void checkIndexInProgress() {
    if (isIndexInProgress()) {
      throw new IndexAlreadyInProgressException();
    }
  }
}
