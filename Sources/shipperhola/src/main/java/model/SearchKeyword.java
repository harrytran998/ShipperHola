/*
 * Copyright © 2017 XVideos Team
 */
package model;

/**
 *
 * @author Le Cao Nguyen
 */
public class SearchKeyword {
    private String keyword;
    private long searchCount;

    public SearchKeyword() {
    }

    public SearchKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchKeyword(String keyword, long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(long searchCount) {
        this.searchCount = searchCount;
    }

    @Override
    public String toString() {
        return "SearchKeyword{" + "keyword=" + keyword + ", searchCount=" + searchCount + '}';
    }
    
}
