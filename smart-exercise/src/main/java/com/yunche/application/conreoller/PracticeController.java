package com.yunche.application.conreoller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yunche.application.conreoller.po.PracticeDetailsPO;
import com.yunche.common.entity.Result;
import com.yunche.domain.dto.PracticeDetailsDto;
import com.yunche.domain.service.SubjectInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/practice")
@Slf4j
public class PracticeController {

    @Resource
    private SubjectInfoService subjectInfoService;

    @PostMapping("/getPracticeDetails")
    public Result<List<PracticeDetailsDto>> getPracticeDetails(@RequestBody PracticeDetailsPO practiceDetailsPO){
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeController.getPracticeDetails.po:{}", JSON.toJSONString(practiceDetailsPO));
            }
            Preconditions.checkNotNull(practiceDetailsPO.getLabelId(),"标签id不能为空");
            Preconditions.checkNotNull(practiceDetailsPO.getSubjectType(),"题目类型不能为空");
            return Result.ok(subjectInfoService.getPracticeDetails(practiceDetailsPO));
        } catch (Exception e) {
            log.error("PracticeController.getPracticeDetails.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
