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
}
