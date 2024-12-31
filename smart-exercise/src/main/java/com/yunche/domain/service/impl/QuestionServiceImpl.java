package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.dto.questionDto;
import com.yunche.domain.service.QuestionService;
import com.yunche.infra.entity.Question;
import com.yunche.infra.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {


    @Override
    public List<questionDto> GetDataBase() {
        LambdaQueryWrapper<Question> queryWrapper = Wrappers.<Question>lambdaQuery();
        List<Question> list = list(queryWrapper);

        Set<String> seenContent = new HashSet<>(); // 用于记录已存在的内容

        return list.stream()
                .filter(question -> "single_answer".equals(question.getType()) ||
                        "true_false".equals(question.getType()) ||
                        "multi_answer".equals(question.getType()))
                .filter(question -> seenContent.add(question.getContent())) // 如果已存在，则跳过
                .map(question -> {
                    questionDto dto = new questionDto();
                    dto.setId(question.getId());
                    dto.setType(question.getType());
                    dto.setContent(question.getContent());
                    dto.setAnswer(question.getAnswer());
                    dto.setDifficulty(question.getDifficulty());
                    dto.setOptions(question.getOptions());
                    dto.setSource(question.getSource());
                    dto.setKnowledge(question.getKnowledge());
                    return dto;
                })
                .toList();
    }
}
