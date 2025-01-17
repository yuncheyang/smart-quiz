package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import com.yunche.domain.dto.SubjectDetailsDto;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.domain.service.SubjectRadioService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.entity.SubjectRadio;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectRadioHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Resource
    private SubjectAnswerService subjectAnswerService;

    @Override
    public SubjectTypeEnum getSubjectTypeEnum() {
        return SubjectTypeEnum.RADIO;
    }

    @Override
    public SubjectDetailsDto query(Integer subjectId) {
        Map<String, String> stringMap = new HashMap<>();
        List<SubjectRadio> subjectRadios = subjectRadioService.queryByCondition(subjectId);
        SubjectAnswer subjectAnswer = subjectAnswerService.queryByCondition(subjectId);
        for (SubjectRadio subjectRadio : subjectRadios) {
            stringMap.put(subjectRadio.getOptionKey(), subjectRadio.getOptionContent());
        }
        SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto();
        subjectDetailsDto.setSubjectMap(stringMap);
        subjectDetailsDto.setIsCorrect(subjectAnswer.getCorrectOption());
        return subjectDetailsDto;
    }
}