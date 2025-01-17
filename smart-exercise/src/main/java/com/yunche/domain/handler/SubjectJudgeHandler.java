package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import com.yunche.domain.dto.SubjectDetailsDto;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.domain.service.SubjectJudgeService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.entity.SubjectJudge;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectJudgeHandler implements  SubjectTypeHandler{

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Resource
    private SubjectAnswerService subjectAnswerService;


    @Override
    public SubjectTypeEnum getSubjectTypeEnum() {
        return SubjectTypeEnum.JUDGE;
    }

    @Override
    public SubjectDetailsDto query(Integer subjectId) {
        Map<String, String> stringMap = new HashMap<>();
        List<SubjectJudge> subjectJudges = subjectJudgeService.queryByCondition(subjectId);
        SubjectAnswer subjectAnswer = subjectAnswerService.queryByCondition(subjectId);
        for (SubjectJudge subjectJudge : subjectJudges) {
            stringMap.put(subjectJudge.getOptionKey(), subjectJudge.getOptionContent());
        }
        SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto();
        subjectDetailsDto.setSubjectMap(stringMap);
        subjectDetailsDto.setIsCorrect(subjectAnswer.getCorrectOption());

        return subjectDetailsDto;
    }
}
