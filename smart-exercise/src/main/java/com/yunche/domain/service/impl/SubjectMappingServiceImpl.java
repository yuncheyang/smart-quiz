package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectMappingService;
import com.yunche.infra.entity.SubjectMapping;
import com.yunche.infra.mapper.SubjectMappingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectMappingServiceImpl extends ServiceImpl<SubjectMappingMapper, SubjectMapping> implements SubjectMappingService {

    @Override
    public List<SubjectMapping> selectSubjectIdByLabelId(Integer labelId) {
        LambdaQueryWrapper<SubjectMapping> queryWrapper = Wrappers.<SubjectMapping>lambdaQuery()
                .eq(SubjectMapping::getLabelId, labelId)
                .select(SubjectMapping::getSubjectId);
        return list(queryWrapper);
    }

}
