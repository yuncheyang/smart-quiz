package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectRadioService;
import com.yunche.infra.entity.SubjectRadio;
import com.yunche.infra.mapper.SubjectRadioMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectRadioServiceImpl extends ServiceImpl<SubjectRadioMapper, SubjectRadio> implements SubjectRadioService {
}
