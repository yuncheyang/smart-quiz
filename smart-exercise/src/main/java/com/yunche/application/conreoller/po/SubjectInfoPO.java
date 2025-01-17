package com.yunche.application.conreoller.po;

import com.yunche.common.entity.PageInfo;
import lombok.Data;

@Data
public class SubjectInfoPO extends PageInfo {

    private Integer labelId = 1;

    public Integer getLabelId() {
        if (labelId == null || labelId < 1){
            return 1;
        }
        return labelId;
    }
}
