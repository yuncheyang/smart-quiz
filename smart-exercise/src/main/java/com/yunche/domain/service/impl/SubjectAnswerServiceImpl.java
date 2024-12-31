package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectAnswerService;
import com.yunche.infra.entity.SubjectAnswer;
import com.yunche.infra.mapper.SubjectAnswerMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectAnswerServiceImpl extends ServiceImpl<SubjectAnswerMapper, SubjectAnswer> implements SubjectAnswerService {
}
