package com.md.manage.dto;
import java.util.List;

/**
 * Created by Ng on 2017/4/12.
 */
public class Page <T>{

    //表记录查询起始位置
    private int offset;
    //当前页数
    private int pageNum;
    //每页数据条数
    private int pageSize=10;
    //数据库记录数
    private long total;
    //总页数
    private int totalPage;

    private List<T> list;

    public Page(int pageNum, long total) {
        this.pageNum = pageNum;
        this.total = total;
    }

    public Page(int pageNum, int pageSize, long total) {
        this.pageNum = pageNum;
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
        if (this.pageNum < 1)
            this.pageNum = 1;
        if (this.pageNum > this.totalPage)
            this.pageNum = this.totalPage;
        // 计算起始行号
        this.offset = (this.pageNum - 1) * this.pageSize;

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}