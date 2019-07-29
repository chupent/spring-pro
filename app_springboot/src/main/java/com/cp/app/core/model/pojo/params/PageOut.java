package com.cp.app.core.model.pojo.params;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName PageOut
 * @Description TODO
 * @createdate 2019/7/25 星期四 14:25
 */
public class PageOut<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int offset;

    public PageOut() {}

    public PageOut(Page<T> page) {
        if(null!=page){
            this.content = page.getContent();
            this.totalPages=page.getTotalPages();//总页数
            this.totalElements=page.getTotalElements();//总条数
            this.pageSize = page.getSize();//页容量
            this.offset = page.getNumber();//当前页下标
        }
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
