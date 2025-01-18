package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectAnswer;

import java.util.Set;

public interface SubjectAnswerService extends IService<SubjectAnswer> {
    SubjectAnswer queryByCondition(Integer subjectId);

    Boolean updateBatch(Set<Integer> subjectId);

    Boolean updateBatchMultiple(java.util.Set<java.lang.Integer> subjectId);
}
