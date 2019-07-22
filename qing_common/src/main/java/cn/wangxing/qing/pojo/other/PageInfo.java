package cn.wangxing.qing.pojo.other;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {


    private static final long serialVersionUID = 123456789011L;

    // 当前页，页面大小
    private long    pageCurrent;
    private long    pageSize;

    // 排序
    private String  orderBy;
    private boolean orderDesc;

    // 搜索值
    private String  searchStr;
    private String  searchField;

    // 总记录数，结果集
    private long    itemCount;
    private List<T> rows;

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageCurrent=" + pageCurrent +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", orderDesc=" + orderDesc +
                ", searchStr='" + searchStr + '\'' +
                ", searchField='" + searchField + '\'' +
                ", itemCount=" + itemCount +
                ", rows=" + rows +
                '}';
    }

    public long getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(long pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(boolean orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
