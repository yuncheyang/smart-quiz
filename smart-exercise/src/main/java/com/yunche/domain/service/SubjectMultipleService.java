package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectMultiple;

import java.util.List;
import java.util.Set;

public interface SubjectMultipleService extends IService<SubjectMultiple> {
    List<SubjectMultiple> queryByCondition(Integer subjectId);

    Set<Integer> getSubjectId();

    List<SubjectMultiple> queryByIDS(List<Long> ids);
}
