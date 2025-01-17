package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectMultipleService;
import com.yunche.infra.entity.SubjectMultiple;
import com.yunche.infra.mapper.SubjectMultipleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectMultipleServiceImpl  extends ServiceImpl<SubjectMultipleMapper, SubjectMultiple> implements SubjectMultipleService {
    @Override
    public List<SubjectMultiple> queryByCondition(Integer subjectId) {
        LambdaQueryWrapper<SubjectMultiple> queryWrapper = Wrappers.<SubjectMultiple>lambdaQuery()
                .eq(SubjectMultiple::getSubjectId, subjectId);
        return list(queryWrapper);
    }
}
