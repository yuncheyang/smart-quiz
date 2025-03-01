package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.application.conreoller.po.PracticeDetailsPO;
import com.yunche.application.conreoller.po.SubjectDetailsPO;
import com.yunche.application.conreoller.po.SubjectInfoPO;
import com.yunche.common.entity.PageResult;
import com.yunche.domain.dto.PracticeDetailsDto;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.infra.entity.SubjectInfo;

import java.util.List;
import java.util.Set;

public interface SubjectInfoService extends IService<SubjectInfo> {

    void BatchList(Set<SubjectInfo> infoSet);

    List<SubjectInfoDto> getInfoId();

    PageResult<SubjectInfoDto> getSubjectByPage(SubjectInfoPO subjectInfoPO);

    Object getSubjectDetails(SubjectDetailsPO subjectDetailsPO);

    SubjectInfo queryByCondition(Integer subjectId);

    List<PracticeDetailsDto> getPracticeDetails(PracticeDetailsPO practiceDetailsPO);
}
