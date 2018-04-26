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
    private long total;
    //总页数
    private int totalPage;

    private List<T> list;

    public Page(int pageNow, long total) {
        this.pageNow = pageNow;
        this.total = total;
    }

    public Page(int pageNow, int pageSize, long total) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.total = total;
    }

    public void pagination(){
        // 计算总页数
        if (this.total % this.pageSize == 0)
            this.totalPage = new Long(this.total/this.pageSize).intValue();
        else
            this.totalPage = new Long(this.total/this.pageSize).intValue() + 1;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}