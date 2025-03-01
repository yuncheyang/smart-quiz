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
import java.util.stream.Collectors;

@Component
public class SubjectJudgeHandler implements SubjectTypeHandler {

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

    @Override
    public List<SubjectDetailsDto> queryByIDS(List<Long> ids) {
        List<SubjectJudge> subjectJudges = subjectJudgeService.queryByIDS(ids);
        Map<Integer, List<SubjectJudge>> subjectIdMap = subjectJudges
                .stream()
                .collect(Collectors.groupingBy(SubjectJudge::getSubjectId));
        return subjectIdMap.entrySet()
                .stream()
                .map(entry -> {
                    Integer subjectID = entry.getKey();
                    List<SubjectJudge> subjectJudgeList = entry.getValue();
                    //获取该id的正确答案
                    SubjectAnswer subjectAnswer = subjectAnswerService.queryByCondition(subjectID);
                    String correctOption = subjectAnswer.getCorrectOption();
                    Map<String, String> subjectMap = subjectJudgeList
                            .stream()
                            .collect(Collectors.toMap(SubjectJudge::getOptionKey, SubjectJudge::getOptionContent));
                    SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto();
                    subjectDetailsDto.setSubjectMap(subjectMap);
                    subjectDetailsDto.setIsCorrect(correctOption);
                    subjectDetailsDto.setSubjectId(subjectID);
                    return subjectDetailsDto;
                }).toList();
    }
}
