package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectRadioService;
import com.yunche.infra.entity.SubjectRadio;
import com.yunche.infra.mapper.SubjectRadioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectRadioServiceImpl extends ServiceImpl<SubjectRadioMapper, SubjectRadio> implements SubjectRadioService {
    @Override
    public List<SubjectRadio> queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectRadio> queryWrapper = Wrappers.<SubjectRadio>lambdaQuery()
                .eq(SubjectRadio::getSubjectId, subjectId);
        return list(queryWrapper);
    }

    @Override
    public Set<Integer> getSubjectId() {
        LambdaQueryWrapper<SubjectRadio> radioQuery = Wrappers.<SubjectRadio>lambdaQuery()
                .select(SubjectRadio::getSubjectId);
        List<SubjectRadio> list = list(radioQuery);
        return list.stream().map(SubjectRadio::getSubjectId).collect(Collectors.toSet());
    }

    @Override
    public List<SubjectRadio> queryByIDS(List<Long> ids) {
        List<Integer> integerIds = ids.stream()
                .map(Long::intValue)
                .toList();
        LambdaQueryWrapper<SubjectRadio> queryWrapper = Wrappers.<SubjectRadio>lambdaQuery()
                .in(SubjectRadio::getSubjectId, integerIds);
        return list(queryWrapper);
    }
}
