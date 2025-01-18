package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectJudgeService;
import com.yunche.infra.entity.SubjectJudge;
import com.yunche.infra.mapper.SubjectJudgeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectJudgeServiceImpl extends ServiceImpl<SubjectJudgeMapper, SubjectJudge> implements SubjectJudgeService {
    @Override
    public List<SubjectJudge> queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectJudge> queryWrapper = Wrappers.<SubjectJudge>lambdaQuery()
                .eq(SubjectJudge::getSubjectId, subjectId);
        return list(queryWrapper);
    }

    @Override
    public Set<Integer> getSubjectId() {
        LambdaQueryWrapper<SubjectJudge> radioQuery = Wrappers.<SubjectJudge>lambdaQuery()
                .select(SubjectJudge::getSubjectId);
        List<SubjectJudge> list = list(radioQuery);
        return list.stream().map(SubjectJudge::getSubjectId).collect(Collectors.toSet());
    }
}
