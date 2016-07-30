package com.summer.whm.service.search.core.distance;

import java.io.IOException;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.queries.CustomScoreProvider;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.search.Query;

public class DistanceScoreQuery extends CustomScoreQuery {

    private String[] searchKey;
    
    private String field;
    
    public DistanceScoreQuery(Query subQuery, String[] searchKey, String field) {
        super(subQuery);
        this.searchKey = searchKey;
        this.field = field;
    }

    @Override
    protected CustomScoreProvider getCustomScoreProvider(AtomicReaderContext context) throws IOException {
        //默认情况下：原有的评分*评分域的评分=最终评分
        //我们将在下面的方法中改变这个规则
        return new DistanceScoreProvider(context, searchKey, field);
    }
    
}
