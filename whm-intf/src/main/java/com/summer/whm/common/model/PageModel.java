package com.summer.whm.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库分页组件 在service层构造PageModel,添加查询参数insertQuery 在sql中不用写limit语句
 * 
 * @author zhou
 * 
 */
public class PageModel<T> {
    private static final int PAGE_SIZE = 10;

    private int pageIndex;
    private int pageSize;
    private long totalCount;

    private List<T> content;
    // 查询参数
    private MapContainer query;
    private int totalPage;

    private boolean hasPreviousPage = true;
    private boolean hasNextPage = true;
    private int previousPage = 0;
    private int nextPage = 0;
    
    public PageModel(int pageIndex) {
        this(pageIndex, PAGE_SIZE);
    }

    public PageModel(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.query = new MapContainer();
        this.content = new ArrayList<T>();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        setTotalPage(0);
        nextPage = pageIndex + 1;
        previousPage = pageIndex - 1;

        if(pageIndex <= 1){
            hasPreviousPage =false;
        }

        if(pageIndex >= totalPage){
            hasNextPage = false;
        }

        
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void addContent(T mc) {
        content.add(mc);
    }
    
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public PageModel<T> insertQuery(String key, Object value) {
        query.put(key, value);
        return this;
    }

    public PageModel<T> insertQuerys(MapContainer map) {
        if (map != null)
            query.putAll(map);

        return this;
    }

    public void setTotalPage(int totalPage) {
        int pages = (int) (totalCount / pageSize);
        if (totalCount % pageSize != 0) {
            pages++;
        }
        this.totalPage = pages;
    }

    public MapContainer getQuery() {
        return query;
    }

    public Object removeQuery(String key) {
        return query.remove(key);
    }

    /**
     * 生成查询数量sql
     * 
     * @return
     */
    public String countSql(String sql) {
        // int index = query.getSql().indexOf(" from ");
        // String sql = "select count(*) " + query.getSql().substring(index);
        // index = sql.indexOf("order by");
        // sql = index == -1 ? sql : sql.substring(0, index);
        // /* 只要有group by就使用子查询 */
        // if(sql.indexOf("group by") != -1){
        // sql = "select count(*) from ( " + sql + " ) _temp_";
        // }

        return "select count(*) from ( " + sql + " ) _temp_";
    }

    /**
     * 生成分页sql
     * 
     * @return
     */
    public String pageSql(String sql) {
        return sql + " limit " + (getPageIndex() - 1) * getPageSize() + "," + getPageSize();
    }

}
