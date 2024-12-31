package com.yunche.domain.dto;

import lombok.Data;

@Data
public class SubjectInfoDto {

    private Long id;

    private String subjectName;

    private Integer subjectType;

    private String correctOption;
}
