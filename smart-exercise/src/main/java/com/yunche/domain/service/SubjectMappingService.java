package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.SubjectMapping;

import java.util.List;

public interface SubjectMappingService extends IService<SubjectMapping> {

    List<SubjectMapping> selectSubjectIdByLabelId(Integer labelId);

    List<Integer> getSubjectId(Integer labelId);
}
