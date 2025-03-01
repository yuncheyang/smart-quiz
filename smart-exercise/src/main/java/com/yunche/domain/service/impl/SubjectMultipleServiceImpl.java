package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectMultipleService;
import com.yunche.infra.entity.SubjectMultiple;
import com.yunche.infra.mapper.SubjectMultipleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectMultipleServiceImpl  extends ServiceImpl<SubjectMultipleMapper, SubjectMultiple> implements SubjectMultipleService {
    @Override
    public List<SubjectMultiple> queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectMultiple> queryWrapper = Wrappers.<SubjectMultiple>lambdaQuery()
                .eq(SubjectMultiple::getSubjectId, subjectId);
        return list(queryWrapper);
    }

    @Override
    public Set<Integer> getSubjectId() {
        LambdaQueryWrapper<SubjectMultiple> multiple = Wrappers.<SubjectMultiple>lambdaQuery()
                .select(SubjectMultiple::getSubjectId);
        List<SubjectMultiple> list = list(multiple);
        return list.stream().map(SubjectMultiple::getSubjectId).collect(Collectors.toSet());
    }

    @Override
    public List<SubjectMultiple> queryByIDS(List<Long> ids) {
        List<Integer> integerIds = ids.stream()
                .map(Long::intValue)
                .toList();
        LambdaQueryWrapper<SubjectMultiple> queryWrapper = Wrappers.<SubjectMultiple>lambdaQuery()
                .in(SubjectMultiple::getSubjectId, integerIds);
        return list(queryWrapper);
    }
}
