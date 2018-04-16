package com.md.manage.dto;
import java.util.List;

/**
 * Created by Ng on 2017/4/12.
 */
public class Page <T>{

    //表记录查询起始位置
    private int offset;
    //当前页数
    private int pageNow;
    //每页数据条数
    private int pageSize=10;
    //数据库记录数
    private long count;
    //总页数
    private int totalPage;

    private List<T> list;

    public Page(int pageNow, long count) {
        this.pageNow = pageNow;
        this.count = count;
    }

    public Page(int pageNow, int pageSize, long count) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.count = count;
    }

    public void pagination(){
        // 计算总页数
        if (this.count % this.pageSize == 0)
            this.totalPage = new Long(this.count/this.pageSize).intValue();
        else
            this.totalPage = new Long(this.count/this.pageSize).intValue() + 1;
        // 排除错误页号
        if (this.pageNow < 1)
            this.pageNow = 1;
        if (this.pageNow > this.totalPage)
            this.pageNow = this.totalPage;
        // 计算起始行号
        this.offset = (this.pageNow - 1) * this.pageSize;

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", pageNow=" + pageNow +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}