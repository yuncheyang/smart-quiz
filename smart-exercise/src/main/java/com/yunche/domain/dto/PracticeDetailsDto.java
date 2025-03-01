package com.yunche.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PracticeDetailsDto {

    private Map<String, String> subjectMap;

    private String isCorrect;

    private Integer subjectScore;

    private String subjectName;
}
