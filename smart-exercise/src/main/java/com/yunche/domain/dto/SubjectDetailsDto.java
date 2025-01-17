package com.yunche.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubjectDetailsDto {

    /**
     * 题目，选项集合
     */
    private Map<String, String> subjectMap;

    /**
     * 正确答案
     */
    private String isCorrect;

    private String subjectName;
}
