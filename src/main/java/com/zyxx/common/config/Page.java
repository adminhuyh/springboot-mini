package com.zyxx.common.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize = 10;
    private int startNum;
    private int pageTotal;
    private int itemTotal;
    private List<T> list;

    public Page() {
    }

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum >= 1 ? pageNum : 1;
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }

        this.startNum = (pageNum - 1) * pageSize;
        this.list = new ArrayList();
        this.pageTotal = 0;
        this.itemTotal = 0;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }

    }

    public int getPageTotal() {
        if (this.pageSize <= 0) {
            this.pageTotal = 1;
            return this.pageTotal;
        } else {
            this.pageTotal = (this.itemTotal + this.pageSize - 1) / this.pageSize;
            return this.pageTotal >= 0 ? this.pageTotal : 1;
        }
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getItemTotal() {
        return this.itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal >= 0 ? itemTotal : 0;
    }

    public int getStartNum() {
        return this.startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
}