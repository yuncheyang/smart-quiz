package com.yunche.domain.dto;

import com.yunche.common.entity.PageInfo;
import lombok.Data;

@Data
public class SubjectInfoDto extends PageInfo {

    private Long id;

    private String subjectName;

    private Integer subjectType;

    private Integer subjectDifficult;

}
