package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.domain.dto.SubjectLabelDto;
import com.yunche.infra.entity.SubjectLabel;

import java.util.List;

public interface SubjectLabelService extends IService<SubjectLabel> {

    void batchSave(List<SubjectLabel> labelList);

    long selectIdByName(String labelName);

    List<SubjectLabelDto> getAllLabel();
}
