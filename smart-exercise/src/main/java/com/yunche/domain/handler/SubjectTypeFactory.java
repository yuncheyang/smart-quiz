package com.yunche.domain.handler;

import com.yunche.common.enums.SubjectTypeEnum;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectTypeFactory {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    Map<SubjectTypeEnum, SubjectTypeHandler> map = new HashMap<>();

    @PostConstruct
    public void addTypeInstance() {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            map.put(subjectTypeHandler.getSubjectTypeEnum(), subjectTypeHandler);
        }
    }

    public SubjectTypeHandler getSubjectTypeHandler(int subjectType) {
        return map.get(SubjectTypeEnum.getByCode(subjectType));
    }
}
