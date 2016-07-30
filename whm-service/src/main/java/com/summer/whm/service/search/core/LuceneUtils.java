package com.summer.whm.service.search.core;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.FieldType.NumericType;
import org.apache.lucene.index.FieldInfo.IndexOptions;

public class LuceneUtils{

  private LuceneUtils(){
  }

  public static FieldType directType(){
    FieldType directType = new FieldType();
    directType.setIndexed(true);
    directType.setIndexOptions(IndexOptions.DOCS_ONLY);
    directType.setStored(true);
    directType.setOmitNorms(true);
    directType.freeze();
    return directType;
  }

  public static FieldType searchType(boolean isAnalyzer){
      FieldType searchType = new FieldType();
      searchType.setIndexed(true);
      searchType.setStored(true);
      searchType.setOmitNorms(true);
      searchType.freeze();
      if(isAnalyzer){
          /* 设置分词 */
          searchType.setTokenized(true);
      }
      
      return searchType;
    }
  
  public static FieldType searchType(){
    FieldType searchType = new FieldType();
    searchType.setIndexed(true);
    searchType.setStored(true);
    /* 设置分词 */
    searchType.setTokenized(true);
    searchType.setOmitNorms(true);
    searchType.freeze();
    
    return searchType;
  }

  public static FieldType storeType(){
    FieldType storeType = new FieldType();
    storeType.setStored(true);
    storeType.setIndexed(false);
    storeType.setTokenized(true);
    storeType.setOmitNorms(true);
    storeType.freeze();
    
    return storeType;
  }

  public static FieldType storeLongType(){
      FieldType storeType = new FieldType();
      storeType.setStored(true);
      storeType.setIndexed(false);
      storeType.setNumericType(NumericType.LONG);
      storeType.setOmitNorms(true);
      storeType.freeze();
      
      return storeType;
    }
  
  public static void closeQuietly(TokenStream stream){
    if(stream == null)
      return;

    try{
      stream.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }

}
