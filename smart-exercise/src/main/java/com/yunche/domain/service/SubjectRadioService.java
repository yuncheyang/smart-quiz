package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectRadio;

import java.util.List;

public interface SubjectRadioService extends IService<SubjectRadio> {
    List<SubjectRadio> queryByCondition(Integer subjectId);
}
