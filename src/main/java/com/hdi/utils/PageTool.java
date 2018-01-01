package com.hdi.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author 王慧东
 * @date 2017-12-27
 * @version 1.0
 */
public class PageTool<T extends Serializable> implements Serializable  {
    private static final long serialVersionUID = -660593215151789696L;
    private List<T> list;
    private Integer pageSize = 20;//每页显示行数
    private Integer pageNumber=1;//当前页，默认为第1页
    private Integer total;//总行数
    /**
     * 除列表外的其他数据
     */
    private Object data;

    public PageTool(){

    }

    public PageTool(Integer total,Integer pageSize,Integer pageNumber) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    /**
     * 取得总行数
     * @return 总行数
     */
    public Integer getTotal() {
        return total;
    }
    /**
     * 设定总行数
     * @param total 总行数
     */
    public void setTotal(Integer total) {
        this.total = total;
//        pageCount = (rowCount % pageSize == 0) ? (rowCount/pageSize) : (rowCount/pageSize +1);
//        if(pageNumber>pageCount){
//            pageNumber = pageCount;
//        }
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /*==================== helpers ======================*/
    public void setList(List<T> records) {
        this.list = records;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     *
     * @param pageSize 每页显示行数
     */
    public void setPageSize(Integer pageSize) {
        if(0!=pageSize) {
            this.pageSize = pageSize;
        }
    }
    public Integer getPageNumber() {
        return pageNumber;
    }
    public List<T> getList() {
        return list;
    }
}
