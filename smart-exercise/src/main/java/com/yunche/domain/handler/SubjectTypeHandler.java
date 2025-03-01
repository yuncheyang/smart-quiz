package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import com.yunche.domain.dto.SubjectDetailsDto;

import java.util.List;

public interface SubjectTypeHandler {

    SubjectTypeEnum getSubjectTypeEnum();

    SubjectDetailsDto query(Integer subjectId);

    List<SubjectDetailsDto> queryByIDS(List<Long> ids);
}
