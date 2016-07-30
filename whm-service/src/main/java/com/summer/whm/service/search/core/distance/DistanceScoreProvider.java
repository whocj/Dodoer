package com.summer.whm.service.search.core.distance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.AtomicReader;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.DocsAndPositionsEnum;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queries.CustomScoreProvider;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.FieldCache.Ints;
import org.apache.lucene.util.Bits;

public class DistanceScoreProvider extends CustomScoreProvider {

    private String[] queryTerms;
    
    private String field;
    
    private static final float SIZE = 1000f;

    public DistanceScoreProvider(AtomicReaderContext context) {
        super(context);
    }

    public DistanceScoreProvider(AtomicReaderContext context, String[] searchKey, String field) {
        super(context);
        this.queryTerms = searchKey;
        this.field = field;
    }

    @Override
    public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {

        final AtomicReader reader = this.context.reader();

        // 距离等于匹配到的最后一个term的startOffset减去匹配到的第一个term的startOffset
     // 等于第一个term的startOffset
//        int start = -1; 
     // 等于最后一个term的startOffset
//        int end = -1; 

        float distance = 0f;
        int termNum = queryTerms.length;
        int count = 0;
//        Bits liveDocs = reader.getLiveDocs();
        Bits liveDocs = null;
        DocsAndPositionsEnum docsAndPositions = null;
        int sort = 0;
        int[] dis = new int[termNum];
        // catentdesc域中匹配用户关键词中的每一个term,计算距离
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String str : queryTerms){
            map.put(str, 0);
        }
        try {
            Terms terms = reader.getTermVector(doc, field);
            if(terms != null){
                TermsEnum it = terms.iterator(null);
                while (it.next() != null) {
                    String term = it.term().utf8ToString();
                    for (String qt : queryTerms) {
                        if (qt.equals(term)) {
                            
                            docsAndPositions = it.docsAndPositions(liveDocs, docsAndPositions);
                            if (docsAndPositions == null) {
                                continue;
                            }
                            docsAndPositions.nextDoc();
                            docsAndPositions.nextPosition();
                            int startOffset = docsAndPositions.startOffset();
                            
                            map.put(qt, startOffset);
                            count++;
                            if(count == termNum){
                                break;
                            }
                            //docsAndPositions = null;
                        }
                    }
                }
                
                if(count == termNum){
                    int index = 0;
                    for(String str : queryTerms){
                        dis[index++] = map.get(str);
                    }
                    
                    int temp = 0;
                    int tempSize = 0;
                    for(int i = 0; i< dis.length - 1; i++){
                        temp = dis[i + 1] - dis[0] - i;
                        if(temp < 0){
                            tempSize += 10;
                        }
                        distance += Math.abs(temp);
                    }
                    distance *= tempSize;
                    Ints ints = FieldCache.DEFAULT.getInts(context.reader(), "sort", false);
                    sort = ints.get(doc);
                    return (1 - distance / SIZE) * 100000000 + sort;
                }else{
                    return 0;
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return distance;
    }

}
