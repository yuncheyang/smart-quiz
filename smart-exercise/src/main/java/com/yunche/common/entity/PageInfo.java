package com.yunche.common.entity;

import lombok.Data;

/**
 * 分页请求类实体
 */
@Data
public class PageInfo {

    /**
     * 分页的页数
     */
    private Integer pageNo = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = 5;

    public Integer getPageNo(){
        if (pageNo == null || pageNo < 1){
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize(){
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE){
            return 20;
        }
        return pageSize;
    }
}
