package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectMultiple;

import java.util.List;

public interface SubjectMultipleService extends IService<SubjectMultiple> {
    List<SubjectMultiple> queryByCondition(Integer subjectId);
}
