package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectMultipleService;
import com.yunche.infra.entity.SubjectMultiple;
import com.yunche.infra.mapper.SubjectMultipleMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectMultipleServiceImpl  extends ServiceImpl<SubjectMultipleMapper, SubjectMultiple> implements SubjectMultipleService {
}
