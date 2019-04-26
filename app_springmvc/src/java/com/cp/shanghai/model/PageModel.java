package com.cp.shanghai.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName PageModel
 * @Description TODO Page Model
 * @createdate 2018/10/5 星期五 09:36
 */
public class PageModel<T> {

    //默认页记录数
    public static final int DEFAULT_PAGE_SIZE = 10;
    //总页数
    protected int totalSize;
    //每页的记录数，实际记录要小于或等于他
    protected int pageSize = this.DEFAULT_PAGE_SIZE;
    //当前页第一条在数据库的位置(偏移量)
    protected int offset;
    //存放的记录容器
    protected List<T> data;

    /**
     * 构造方法，只构造空页
     */
    protected PageModel() {
    }

    /**
     * 默认构造方法
     *
     * @param offset    本页数据在数据库中的起始位置
     * @param totalSize 数据库中总记录条数
     * @param pageSize  本页容量
     * @param data      本页包含的数据
     */
    public PageModel(int offset, int pageSize, int totalSize, List<T> data) {
        this.pageSize = (pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize);
        this.offset = (offset < 0 ? 0 : offset);
        this.totalSize = (totalSize < 0 ? 0 : totalSize);
        this.data = data;
    }

    public int getTotalCount() {
        return totalSize;
    }

    public void setTotalCount(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public List<T> getDate() {
        return data;
    }

    public void setDate(List<T> data) {
        this.data = data;
    }

    /**
     * 根据指定的方式进行排序
     *
     * @param comparator
     * @return
     */
    public List<T> sortData(Comparator<T> comparator) {
        Collections.sort(this.data, comparator);
        return data;
    }

    /**
     * 返回默认排序结果集<br>
     * T 必须实现 Comparable 接口
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> sortData() {
        if (data == null || data.isEmpty() || !(data.get(0) instanceof Comparable)) {
            return data;
        }
        Collections.sort((List<Comparable<Object>>) data);
        return data;
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getCurrentPage() {
        return ((offset / pageSize) + 1);
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getPageCount() {
        int pageCount = (int) Math.ceil((totalSize / 1.0 / pageSize));
        return pageCount;
    }

}
