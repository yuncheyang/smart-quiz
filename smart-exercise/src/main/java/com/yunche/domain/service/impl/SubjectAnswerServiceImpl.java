package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.mapper.SubjectAnswerMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectAnswerServiceImpl extends ServiceImpl<SubjectAnswerMapper, SubjectAnswer> implements SubjectAnswerService {
    @Override
    public SubjectAnswer queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectAnswer> queryWrapper = Wrappers.<SubjectAnswer>lambdaQuery()
                .eq(SubjectAnswer::getSubjectId, subjectId);
        return getOne(queryWrapper);
    }
}
