package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.SubjectJudgeService;
import com.yunche.infra.entity.SubjectJudge;
import com.yunche.infra.mapper.SubjectJudgeMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectJudgeServiceImpl extends ServiceImpl<SubjectJudgeMapper, SubjectJudge> implements SubjectJudgeService {
}
