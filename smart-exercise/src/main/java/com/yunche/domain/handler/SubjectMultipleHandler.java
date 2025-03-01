package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import com.yunche.domain.dto.SubjectDetailsDto;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.domain.service.SubjectMultipleService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.entity.SubjectMultiple;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SubjectMultipleHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Resource
    private SubjectAnswerService subjectAnswerService;


    @Override
    public SubjectTypeEnum getSubjectTypeEnum() {
        return SubjectTypeEnum.MULTIPLE;
    }

    @Override
    public SubjectDetailsDto query(Integer subjectId) {
        Map<String, String> stringMap = new HashMap<>();
        List<SubjectMultiple> subjectMultiples = subjectMultipleService.queryByCondition(subjectId);
        SubjectAnswer subjectAnswer = subjectAnswerService.queryByCondition(subjectId);
        for (SubjectMultiple subjectMultiple : subjectMultiples) {
            stringMap.put(subjectMultiple.getOptionKey(), subjectMultiple.getOptionContent());
        }
        SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto();
        subjectDetailsDto.setSubjectMap(stringMap);
        subjectDetailsDto.setIsCorrect(subjectAnswer.getCorrectOption());
        return subjectDetailsDto;
    }

    @Override
    public List<SubjectDetailsDto> queryByIDS(List<Long> ids) {
        List<SubjectMultiple> subjectMultiples = subjectMultipleService.queryByIDS(ids);
        Map<Integer, List<SubjectMultiple>> subjectIdMap = subjectMultiples
                .stream()
                .collect(Collectors.groupingBy(SubjectMultiple::getSubjectId));
        return subjectIdMap.entrySet()
                .stream()
                .map(entry -> {
                    Integer subjectID = entry.getKey();
                    List<SubjectMultiple> subjectMultipleList = entry.getValue();
                    //获取该id的正确答案
                    SubjectAnswer subjectAnswer = subjectAnswerService.queryByCondition(subjectID);
                    String correctOption = subjectAnswer.getCorrectOption();
                    Map<String, String> subjectMap = subjectMultipleList
                            .stream()
                            .collect(Collectors.toMap(SubjectMultiple::getOptionKey, SubjectMultiple::getOptionContent));
                    SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto();
                    subjectDetailsDto.setSubjectMap(subjectMap);
                    subjectDetailsDto.setIsCorrect(correctOption);
                    subjectDetailsDto.setSubjectId(subjectID);
                    return subjectDetailsDto;
                }).toList();
    }
}
