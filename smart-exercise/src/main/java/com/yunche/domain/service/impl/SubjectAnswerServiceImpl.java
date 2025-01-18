package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.mapper.SubjectAnswerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SubjectAnswerServiceImpl extends ServiceImpl<SubjectAnswerMapper, SubjectAnswer> implements SubjectAnswerService {
    @Override
    public SubjectAnswer queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectAnswer> queryWrapper = Wrappers.<SubjectAnswer>lambdaQuery()
                .eq(SubjectAnswer::getSubjectId, subjectId);
        return getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBatch(Set<Integer> subjectIds) {
        // 批量查询记录
        LambdaQueryWrapper<SubjectAnswer> queryWrapper = Wrappers.<SubjectAnswer>lambdaQuery()
                .in(SubjectAnswer::getSubjectId, subjectIds);
        List<SubjectAnswer> answerList = list(queryWrapper);

        for (SubjectAnswer subjectAnswer : answerList) {
            if ("".equals(subjectAnswer.getCorrectOption())) {
                subjectAnswer.setCorrectOption("B"); // 更新为 B
            }
        }
        // 批量更新
        boolean updated = updateBatchById(answerList);
        if (!updated) {
            throw new RuntimeException("Batch update failed!");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBatchMultiple(Set<Integer> subjectIds) {
        // 批量查询记录
        LambdaQueryWrapper<SubjectAnswer> queryWrapper = Wrappers.<SubjectAnswer>lambdaQuery()
                .in(SubjectAnswer::getSubjectId, subjectIds);
        List<SubjectAnswer> answerList = list(queryWrapper);

        for (SubjectAnswer subjectAnswer : answerList) {
            if ("".equals(subjectAnswer.getCorrectOption())) {
                subjectAnswer.setCorrectOption("AB"); // 更新为 B
            }
        }
        // 批量更新
        boolean updated = updateBatchById(answerList);
        if (!updated) {
            throw new RuntimeException("Batch update failed!");
        }
        return true;
    }
}
