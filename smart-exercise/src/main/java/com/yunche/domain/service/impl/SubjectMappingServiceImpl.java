package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectMappingService;
import com.yunche.infra.entity.SubjectMapping;
import com.yunche.infra.mapper.SubjectMappingMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectMappingServiceImpl extends ServiceImpl<SubjectMappingMapper, SubjectMapping> implements SubjectMappingService {
}
