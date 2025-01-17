package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import com.yunche.domain.dto.SubjectDetailsDto;

public interface SubjectTypeHandler {

    SubjectTypeEnum getSubjectTypeEnum();

    SubjectDetailsDto query(Integer subjectId);
}
