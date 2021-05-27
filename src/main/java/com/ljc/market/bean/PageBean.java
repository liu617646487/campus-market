package com.ljc.market.bean;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页信息封装类
 */
@Component
public class PageBean<T> {

    private Integer currentPage = 1;//当前页码，默认第一页

    private Integer pageSize = 10;//每页显示数量，默认十条

    private long total = 0;//总数量

    private Integer totalPage;//总页数

    private Integer offSet = 0;//数据库游标

    private List<T> content;

    private Integer showPageSize = 5;//显示在页面可快速跳转得页码个数

    private List<Integer> currentShowPage = new ArrayList<Integer>();//当前显示在页面快速跳转的页码

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getShowPageSize() {
        return showPageSize;
    }

    public void setShowPageSize(Integer showPageSize) {
        this.showPageSize = showPageSize;
    }

    public List<Integer> getCurrentShowPage() {
        //首先当前页往前显示currentShowPage
        for(int i=currentPage-1;i>0;i--){
            currentShowPage.add(i);
            if (i<=(currentPage-showPageSize)){
                break;
            }
        }
        //首先当前页往后显示currentShowPage
        for(int i=currentPage;i<=totalPage;i++){
            currentShowPage.add(i);
            if (i>=totalPage){
                break;
            }
            if (i>=(currentPage+showPageSize)){
                break;
            }
        }
        Collections.sort(currentShowPage);
        return currentShowPage;
    }

    public void setCurrentShowPage(List<Integer> currentShowPage) {
        this.currentShowPage = currentShowPage;
    }

    public Integer getOffSet() {
        offSet = (currentPage - 1) * pageSize;
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}
