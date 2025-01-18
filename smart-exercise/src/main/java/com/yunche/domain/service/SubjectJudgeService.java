package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectJudge;

import java.util.List;
import java.util.Set;

public interface SubjectJudgeService extends IService<SubjectJudge> {
    List<SubjectJudge> queryByCondition(Integer subjectId);

    Set<Integer> getSubjectId();
}
