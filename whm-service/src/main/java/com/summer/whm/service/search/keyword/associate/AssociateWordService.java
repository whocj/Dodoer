package com.summer.whm.service.search.keyword.associate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.utils.IOUtil;
import com.summer.whm.service.search.core.distance.DistanceScoreQuery;
import com.summer.whm.service.search.model.DocType;
import com.summer.whm.service.search.utils.StringComparator;

@Service
public class AssociateWordService {

    private Logger log = LoggerFactory.getLogger(AssociateWordService.class);

    public final static String DEFAULT_CORE = "Default";

    private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
    private IndexSearcher searcher = null;
    private LogMergePolicy mergePolicy = null;

    private StringComparator stringComparator = new StringComparator();
    private static AssociateWordService associateWordService = null;

    private Map<String, List<AssociateWord>> wordCache = new HashMap<String, List<AssociateWord>>();
    
    /*
     * 数据初始化 new HashMap<String, List<AppAssociateWord>>();
     */
    synchronized public void initial() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    
                    File file = new File(getIndexPath());
                    if (file != null && file.isDirectory()) {
                        loadIndex();
                        
                        reCreateIndex();
                    } else {
                        FSDirectory fsDirectory = saveDiskIndex();
                        searcher = new IndexSearcher(DirectoryReader.open(new RAMDirectory(fsDirectory, IOContext.READ)));
                    }
                    
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }).start();
    }

    public void loadIndex(){
        FSDirectory fsDirectory = null;
        try {
            fsDirectory = FSDirectory.open(new File(getIndexPath()));
            searcher = new IndexSearcher(DirectoryReader.open(new RAMDirectory(fsDirectory, IOContext.READ)));
            initialCache();
        } catch (Exception e) {
            log.error("索引加载失败！" + e);
        }
    }
    
    //持久化索引文件
    public FSDirectory saveDiskIndex(){
        File file = new File(getIndexPath());
        if (file != null && file.isDirectory()) {
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                log.error("删除索引文件失败！" + e);
            }
        }
        
        FSDirectory fsDirectory = null;
        try {
            fsDirectory = FSDirectory.open(new File(getIndexPath()));
            log.info("<-----联想词索引创建start----->");
            initialIndex(fsDirectory, getAssociateWordFile());
            log.info("<-----联想词索引创建end----->");
        } catch (IOException e) {
            log.error("创建索引失败！" + e);
        }
        
        return fsDirectory;
    }
    
    public void initialIndex(Directory directory, String fileName) throws IOException {
        try {
            createIndex(directory, fileName);
        } catch (Exception e) {
            log.error("初始化索引异常！", e);
        }

    }

    /**
     * 
     * 处理输入的搜索词为单个字母时，初始化搜索词对应的搜索结果。 <br>
     * 〈功能详细描述〉
     * 
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     */
    public void initialCache() throws IOException, InvalidTokenOffsetsException {
        log.info("开始初始化搜索词为单字母时应该返回的结果。");
        String[] words = GlobalConfigHolder.ASSOCIATE_WORDS_CACHE.split(",");
        wordCache.clear();
        for (String str : words) {
            wordCache.put(str, search(str, DEFAULT_CORE));
        }
        log.info("结束初始化搜索词为单字母时应该返回的结果。");
    }

    /**
     * 
     * 功能描述: <br>
     * 创建索引
     * 
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void createIndex(Directory directory, String fileName) throws IOException {
        mergePolicy = new LogByteSizeMergePolicy();
        mergePolicy.setMergeFactor(4);// 合并因子 10以下 查询快 索引慢
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47, analyzer);
        conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
        conf.setMergePolicy(mergePolicy);
        IndexWriter writer = new IndexWriter(directory, conf);

        BufferedReader br = null;
        br = IOUtil.getReader(new FileInputStream(fileName), "UTF-8");
        String temp = null;
        String[] strs = null;
        Document doc = null;
        int sort = 0;

        String keyword = null;

        // 保存词向量信息
        FieldType customType = new FieldType(TextField.TYPE_STORED);
        customType.setStoreTermVectors(true);
        customType.setStoreTermVectorPositions(true);
        customType.setStoreTermVectorOffsets(true);

        while ((temp = br.readLine()) != null) {

            strs = temp.split(GlobalConfigHolder.SPLIT_DEFAULT_DELIMITER, -1);
            if (strs.length > 4) {
                doc = new Document();
                keyword = strs[2];
                // 9位数据不进索引
                if (keyword.matches("[0-9]{9}")) {
                    continue;
                }
                String docId = keyword + "@" + strs[3];
                keyword = keyword.toLowerCase();
                keyword = keyword.replaceAll(" ", "");
                doc.add(new Field(DocType.FIELD_DOCID, docId, StringField.TYPE_STORED));
                doc.add(new Field(DocType.FIELD_KEYWORD, keyword, StringField.TYPE_STORED));
                doc.add(new Field(DocType.FIELD_PIN, strs[1], StringField.TYPE_NOT_STORED));
                doc.add(new Field(DocType.FIELD_SPIN, strs[0], StringField.TYPE_NOT_STORED));
                
//                doc.add(new Field(DocType.FIELD_CORE, strs[3], StringField.TYPE_NOT_STORED));
                
                doc.add(new Field(DocType.FIELD_CORE, DEFAULT_CORE, StringField.TYPE_NOT_STORED));
                doc.add(new Field(DocType.FIELD_IKWORD, keyword, customType));
                try {
                    sort = Integer.parseInt(strs[4]);
                    if (strs.length > 5 && StringUtils.isNotEmpty(strs[5])) {
                        doc.add(new IntField(DocType.FIELD_MANUAL, Integer.parseInt(strs[5]), IntField.TYPE_NOT_STORED));
                    }
                } catch (Exception e) {
                    sort = 0;
                }
                doc.add(new IntField(DocType.FIELD_SORT, sort, IntField.TYPE_STORED));
                doc.add(new Field(DocType.FIELD_UNIQUE_IKWORD, getIKAnalyzerWordForSortList(keyword).toString(),
                        StringField.TYPE_STORED));

                // 默认主站创建索引时，该字段为0，表示不统计结果
                int count = 0;

                doc.add(new IntField(DocType.FIELD_COUNT, count, IntField.TYPE_STORED));
                writer.updateDocument(new Term(DocType.FIELD_DOCID, docId), doc);
            }
        }
        writer.commit();
        writer.close();
        br.close();
    }

    /**
     * 
     * 功能描述: <br>
     * 更新索引
     * 
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void reCreateIndex() throws IOException, InvalidTokenOffsetsException {
        RAMDirectory ramDirectory = new RAMDirectory();
        // 加载主站联想词
        initialIndex(ramDirectory, getAssociateWordFile());
        searcher = new IndexSearcher(DirectoryReader.open(new RAMDirectory(ramDirectory, IOContext.READ)));
        initialCache();
        
        saveDiskIndex();
    }

    /**
     * 
     * 功能描述: <br>
     * 关键词搜索
     * 
     * @param keyword
     * @return
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<AssociateWord> search(String keyword, String... core) throws IOException, InvalidTokenOffsetsException {
        if (core.length < 1) {
            core = new String[] { DEFAULT_CORE };
        }
        List<AssociateWord> appAssociateWordList = new ArrayList<AssociateWord>();
        appAssociateWordList.addAll(searchPrefix(keyword, core));

        if (appAssociateWordList.size() < GlobalConfigHolder.ASSOCIATE_WORD_SIZE) {
            appAssociateWordList.addAll(searchFuzzy(keyword, core));
        }
        appAssociateWordList = removeRepeat(appAssociateWordList);
        return appAssociateWordList;
    }

    public Query getQuery(String keyword) {
        BooleanQuery query = new BooleanQuery();
        PrefixQuery prefixQuery = new PrefixQuery(new Term(DocType.FIELD_PIN, keyword));
        prefixQuery.setBoost(DocType.BOOST_FIELD_PIN);
        query.add(prefixQuery, BooleanClause.Occur.SHOULD);

        prefixQuery = new PrefixQuery(new Term(DocType.FIELD_SPIN, keyword));
        prefixQuery.setBoost(DocType.BOOST_FIELD_SPIN);
        query.add(prefixQuery, BooleanClause.Occur.SHOULD);

        prefixQuery = new PrefixQuery(new Term(DocType.FIELD_KEYWORD, keyword));
        prefixQuery.setBoost(DocType.BOOST_FIELD_KEYWORD);
        query.add(prefixQuery, BooleanClause.Occur.SHOULD);

        return query;
    }

    // 前辍匹配
    public List<AssociateWord> searchPrefix(String keyword, String... core) throws IOException,
            InvalidTokenOffsetsException {
        if (wordCache.get(keyword + core[0]) != null) {
            return wordCache.get(keyword + core[0]);
        }

        List<AssociateWord> appAssociateWordList = new ArrayList<AssociateWord>();

        BooleanQuery queryPan = new BooleanQuery();
        queryPan.add(getQuery(keyword), BooleanClause.Occur.MUST);
        queryPan.add(new TermQuery(new Term("core", core[0])), BooleanClause.Occur.MUST);

        TopDocs topDocs = searcher.search(queryPan, GlobalConfigHolder.ASSOCIATE_WORD_SIZE, getSort());
        ScoreDoc[] hits = topDocs.scoreDocs;
        int len = Math.min(GlobalConfigHolder.ASSOCIATE_WORD_SIZE, topDocs.totalHits);

        if (len > 0) {
            String view = null;
            int sort = 0;
            for (int i = 0; i < len; i++) {
                Document doc = searcher.doc(hits[i].doc);
                view = doc.get(DocType.FIELD_KEYWORD).replaceAll(keyword, "<b>" + keyword + "</b>");
                sort = Integer.parseInt(doc.get(DocType.FIELD_SORT));
                AssociateWord assWord = new AssociateWord(doc.get(DocType.FIELD_KEYWORD), view,
                        doc.get(DocType.FIELD_UNIQUE_IKWORD), sort);
                assWord.setCount(Integer.parseInt(doc.get(DocType.FIELD_COUNT)));
                appAssociateWordList.add(assWord);
            }
        }
        return appAssociateWordList;
    }

    // 模糊匹配
    public List<AssociateWord> searchFuzzy(String keyword, String... core) throws IOException,
            InvalidTokenOffsetsException {
        List<AssociateWord> appAssociateWordList = new ArrayList<AssociateWord>();
        String[] keywords = getIKAnalyzerWord(keyword);
        BooleanQuery queryPan = new BooleanQuery();

        // 分词匹配，权重为1
        BooleanQuery queryIk = new BooleanQuery();
        for (String str : keywords) {
            queryIk.add(new TermQuery(new Term(DocType.FIELD_IKWORD, str)), BooleanClause.Occur.MUST);
        }

        queryPan.add(queryIk, BooleanClause.Occur.MUST);
        queryPan.add(new TermQuery(new Term(DocType.FIELD_CORE, core[0])), BooleanClause.Occur.MUST);
        TopDocs topDocs = null;
        DistanceScoreQuery query = new DistanceScoreQuery(queryPan, keywords, DocType.FIELD_IKWORD);
        topDocs = searcher.search(query, GlobalConfigHolder.ASSOCIATE_WORD_SIZE);

        ScoreDoc[] hits = topDocs.scoreDocs;

        int len = Math.min(GlobalConfigHolder.ASSOCIATE_WORD_SIZE, topDocs.totalHits);
        SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<b>", "</b>");// 设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀
        Highlighter highlighter = new Highlighter(simpleHtmlFormatter, new QueryScorer(queryPan));

        if (len > 0) {
            String view = null;
            int sort = 0;
            for (int i = 0; i < len; i++) {
                Document doc = searcher.doc(hits[i].doc);
                view = highlighter.getBestFragment(analyzer, "", doc.get(DocType.FIELD_KEYWORD));
                sort = Integer.parseInt(doc.get(DocType.FIELD_SORT));

                AssociateWord assWord = new AssociateWord(doc.get(DocType.FIELD_KEYWORD),
                        view == null ? doc.get(DocType.FIELD_KEYWORD) : view, doc.get(DocType.FIELD_UNIQUE_IKWORD),
                        sort);
                assWord.setCount(Integer.parseInt(doc.get(DocType.FIELD_COUNT)));
                appAssociateWordList.add(assWord);
            }
        }
        return appAssociateWordList;
    }

    // 分词
    public String[] getStandardAnalyzerWord(String keyword) throws IOException {
        StringReader reader = new StringReader(keyword);
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        TokenStream ts = analyzer.tokenStream(keyword, reader);
        List<String> strList = new ArrayList<String>();
        // 获取词元文本属性
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        // 迭代获取分词结果
        while (ts.incrementToken()) {
            strList.add(term.toString());
        }
        // 关闭TokenStream（关闭StringReader）
        ts.end(); // Perform end-of-stream operations, e.g. set the final
                  // offset.
        String[] strs = new String[strList.size()];
        return strList.toArray(strs);
    }

    // 分词
    public List<String> getIKAnalyzerWordForList(String keyword) throws IOException {
        StringReader reader = new StringReader(keyword);
        TokenStream ts = analyzer.tokenStream(keyword, reader);
        List<String> strList = new ArrayList<String>();
        // 获取词元文本属性
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        // 迭代获取分词结果
        while (ts.incrementToken()) {
            strList.add(term.toString());
        }
        // 关闭TokenStream（关闭StringReader）
        ts.reset();
        ts.end(); // Perform end-of-stream operations, e.g. set the final
        return strList;
    }

    // 分词已排序
    public List<String> getIKAnalyzerWordForSortList(String keyword) throws IOException {
        List<String> strList = getIKAnalyzerWordForList(keyword);
        Collections.sort(strList, stringComparator);
        return strList;
    }

    // 分词
    public String[] getIKAnalyzerWord(String keyword) {
        List<String> strList = null;
        String[] strs = null;
        try {
            strList = getIKAnalyzerWordForList(keyword);
            strs = new String[strList.size()];
        } catch (IOException e) {
            log.error("分词失败", e);
        }

        return strList.toArray(strs);
    }

    public Sort getSort() {
        return new Sort(new SortField[] { new SortField(DocType.FIELD_MANUAL, SortField.Type.INT, true),
                SortField.FIELD_SCORE, new SortField("sort", SortField.Type.INT, true) });
    }

    // 混拼搜索
    public List<AssociateWord> search(Keyword keyword, String... core) throws IOException, InvalidTokenOffsetsException {
        if (core.length < 1) {
            core = new String[] { DEFAULT_CORE };
        }
        
        List<AssociateWord> appAssociateWordList = new ArrayList<AssociateWord>();
        appAssociateWordList.addAll(searchPrefix(keyword.getSrckeyword(), core));

        if (appAssociateWordList.size() == GlobalConfigHolder.ASSOCIATE_WORD_SIZE) {
            return appAssociateWordList;
        }

        QueryWrapperFilter queryWrapperFilter = new QueryWrapperFilter(getQuery(keyword, core[0]));
        CachingWrapperFilter cachingWrapperFilter = new CachingWrapperFilter(queryWrapperFilter);

        TopDocs topDocs = searcher.search(getDistanceScoreQuery(keyword, core[0]), cachingWrapperFilter, GlobalConfigHolder.ASSOCIATE_WORD_SIZE);

        ScoreDoc[] hits = topDocs.scoreDocs;
        int len = Math.min(GlobalConfigHolder.ASSOCIATE_WORD_SIZE, topDocs.totalHits);

        if (len > 0) {
            String view = null;
            String temp = null;
            int sort = 0;
            for (int i = 0; i < len; i++) {
                Document doc = searcher.doc(hits[i].doc);
                // System.out.println(hits[i].score + "  " + doc);
                temp = doc.get(DocType.FIELD_KEYWORD);
                view = temp.replace(keyword.getSrckeyword(), "<b>" + keyword.getSrckeyword() + "</b>");
                if (view.equals(temp)) {
                    int index = 0;
                    for (String type : keyword.getType()) {
                        if (Keyword.ZH.equals(type)) {
                            view = view.replace(keyword.getName()[index], "<b>" + keyword.getName()[index] + "</b>");
                        }
                        index++;
                    }
                }

                sort = Integer.parseInt(doc.get(DocType.FIELD_SORT));
                AssociateWord assWord = new AssociateWord(temp, view == null ? temp : view,
                        doc.get(DocType.FIELD_UNIQUE_IKWORD), sort);
                assWord.setCount(Integer.parseInt(doc.get(DocType.FIELD_COUNT)));
                appAssociateWordList.add(assWord);
            }
        }

        if (appAssociateWordList.size() < GlobalConfigHolder.ASSOCIATE_WORD_SIZE) {
            appAssociateWordList.addAll(searchFuzzy(keyword.getKeyword(), core));
        }
        appAssociateWordList = removeRepeat(appAssociateWordList);
        return appAssociateWordList;
    }

    // 删除重复项
    public List<AssociateWord> removeRepeat(List<AssociateWord> associateWordList) {
        List<AssociateWord> retList = new ArrayList<AssociateWord>();
        Set<String> assSet = new HashSet<String>();
        if (associateWordList != null && associateWordList.size() > 0) {
            for (AssociateWord associateWord : associateWordList) {
                if (!assSet.contains(associateWord.getUniqueKeyword())) {
                    assSet.add(associateWord.getUniqueKeyword());
                    retList.add(associateWord);
                }
            }
        }

        return retList;
    }

    // 通配符查询
    public Query getWildcardQuery(Keyword keyword, String core) {
        BooleanQuery query = new BooleanQuery();
        BooleanQuery query_prefix = new BooleanQuery();
        query_prefix.add(new PrefixQuery(new Term(DocType.FIELD_PIN, keyword.pin())), BooleanClause.Occur.SHOULD);
        query_prefix.add(new PrefixQuery(new Term(DocType.FIELD_SPIN, keyword.spin())), BooleanClause.Occur.SHOULD);

        query.add(query_prefix, BooleanClause.Occur.MUST);
        query.add(new WildcardQuery(new Term(DocType.FIELD_KEYWORD, keyword.getWildcardQuery())),
                BooleanClause.Occur.MUST);
        query.add(new TermQuery(new Term(DocType.FIELD_CORE, core)), BooleanClause.Occur.MUST);
        return query;
    }

    // 词间距查询
    public Query getDistanceScoreQuery(Keyword keyword, String core) {
        BooleanQuery query = new BooleanQuery();

        TermQuery termQuery = new TermQuery(new Term(DocType.FIELD_CORE, core));
        query.add(
                new DistanceScoreQuery(termQuery, getIKAnalyzerWord(keyword.getWildcardQuery()), DocType.FIELD_IKWORD),
                BooleanClause.Occur.MUST);
        return query;
    }

    public Query getQuery(Keyword keyword, String core) {
        BooleanQuery queryPan = new BooleanQuery();
        queryPan.add(getQuery(keyword.getName()[0]), BooleanClause.Occur.MUST);

        BooleanQuery query_prefix = new BooleanQuery();
        query_prefix.add(new PrefixQuery(new Term(DocType.FIELD_PIN, keyword.pin())), BooleanClause.Occur.SHOULD);
        query_prefix.add(new PrefixQuery(new Term(DocType.FIELD_SPIN, keyword.spin())), BooleanClause.Occur.SHOULD);
        queryPan.add(query_prefix, BooleanClause.Occur.MUST);

        BooleanQuery queryIk = new BooleanQuery();
        for (String str : getIKAnalyzerWord(keyword.getWildcardQuery())) {
            queryIk.add(new TermQuery(new Term(DocType.FIELD_IKWORD, str)), BooleanClause.Occur.MUST);
        }
        queryPan.add(queryIk, BooleanClause.Occur.MUST);
        queryPan.add(new TermQuery(new Term(DocType.FIELD_CORE, core)), BooleanClause.Occur.MUST);
        return queryPan;
    }

    public static AssociateWordService getInstance() {
        if (associateWordService == null) {
            associateWordService = new AssociateWordService();
            associateWordService.initial();
        }

        return associateWordService;
    }

    public static String getIndexPath(){
        return GlobalConfigHolder.BASE_PATH + "opt/whm/index/associate";
    }
    
    public static String getAssociateWordFile(){
        return  GlobalConfigHolder.BASE_PATH + "opt/whm/txt/associateWords.txt";
    }
    
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws IOException, InvalidTokenOffsetsException {
        AssociateWordService associateWordService = AssociateWordService.getInstance();
        List<AssociateWord> appAssociateWordList = associateWordService.search("xm");
        for (AssociateWord appAssociateWord : appAssociateWordList) {
            System.out.println(appAssociateWord.getKeyword());
        }
        associateWordService.reCreateIndex();
        appAssociateWordList = associateWordService.search("xm");
        for (AssociateWord appAssociateWord : appAssociateWordList) {
            System.out.println(appAssociateWord.getKeyword());
        }

        // AssociateWordService associateWordService = AssociateWordService.getInstance();
        // System.out.println(Arrays.toString(associateWordService.getIKAnalyzerWord("*三星*手机")));
        // System.out.println(Arrays.toString(associateWordService.getIKAnalyzerWord("(joyang)九阳 df-d90d")));
    }

}