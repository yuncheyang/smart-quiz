package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.domain.dto.SubjectInfoDto;
import com.yunche.infra.entity.SubjectInfo;

import java.util.List;
import java.util.Set;

public interface SubjectInfoService extends IService<SubjectInfo> {

    void BatchList(Set<SubjectInfo> infoSet);

    List<SubjectInfoDto> getInfoId();
}
