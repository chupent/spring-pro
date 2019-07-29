package com.cp.app.core.model.pojo.params;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName PageParm
 * @Description TODO
 * @createdate 2019/7/25 星期四 13:22
 */
public abstract class PageIn {
    private int page = 0; //默认查询第一页
    private int size = 10;//默认查询10条数据
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
